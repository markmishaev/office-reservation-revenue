package com.wework.officereservationrevenue.bl;

import com.wework.officereservationrevenue.model.Reservation;
import com.wework.officereservationrevenue.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.*;

public class CurrentRevenueCalculation implements RevenueCalculationStrategy {

    private final Logger logger = LoggerFactory.getLogger(CurrentRevenueCalculation.class);

    @Override
    public double calculateRevenueForMonth(String yearAndMonth, List<Reservation> reservations) {

        yearAndMonth += "-01";
        double revenue = -1;

        try {

            Date calculateForDate = DateUtils.convertYearAndMonthToDate(yearAndMonth);

            List<Reservation> fullMonthReservations = getFullMonthReservations(calculateForDate, reservations);
            revenue = calculateRevenueForMonths(fullMonthReservations);

            List<Reservation> partialMonthReservations = getPartialMonthReservations(calculateForDate, reservations);
            revenue += calculateRevenueForPartialMonths(partialMonthReservations);

        } catch (ParseException e) {
            String errorMessage = "Invalid month format. Should has YYYY-mm pattern.";
            logger.error(errorMessage);
        }

        return Math.round(revenue);
    }

    private double calculateRevenueForPartialMonths(List<Reservation> partialMonthReservations) {

        final double[] revenue = {0};

        partialMonthReservations.forEach(reservation -> {

            int numberOfDaysInMonth = DateUtils.getNumberOfDaysInMonth(reservation.getStartDay());
            long numberOfDaysInDayOfInterest = DateUtils.getNumberOfDaysBetweenTwoDates(reservation.getStartDay(), reservation.getEndDay());

            double ratio = (double) numberOfDaysInDayOfInterest / numberOfDaysInMonth;
            revenue[0] += reservation.getMonthlyPrice() * ratio;
        });

        return revenue[0];
    }


    private List<Reservation> getPartialMonthReservations(Date calculateForDate, List<Reservation> reservations) {

        List<Reservation> partialMonthReservations = new ArrayList<>();

        reservations.forEach(reservation -> {

            Calendar cal = Calendar.getInstance();
            cal.setTime(calculateForDate);
            int calculateForDateMonth = 12 * cal.get(Calendar.YEAR) + (cal.get(Calendar.MONTH) + 1);

            cal.setTime(reservation.getStartDay());
            int reservationStartMonth = 12 * cal.get(Calendar.YEAR) + (cal.get(Calendar.MONTH) + 1);

            cal.setTime(reservation.getEndDay());
            int reservationEndMonth = 12 * cal.get(Calendar.YEAR) + (cal.get(Calendar.MONTH) + 1);


            if (calculateForDateMonth == reservationStartMonth && calculateForDateMonth == reservationEndMonth) {
                partialMonthReservations.add(reservation);
            } else if (calculateForDateMonth == reservationStartMonth && calculateForDateMonth < reservationEndMonth) {
                reservation.setEndDay(DateUtils.getFirstDateOfNextMonth(reservation.getStartDay()));
                partialMonthReservations.add(reservation);
            } else if (calculateForDateMonth == reservationEndMonth) {
                reservation.setStartDay(DateUtils.getFirstDateOfMonth(reservation.getEndDay()));
                partialMonthReservations.add(reservation);
            }
        });

        return partialMonthReservations;
    }


    private double calculateRevenueForMonths(List<Reservation> fullMonthReservations) {

        return fullMonthReservations.stream().mapToDouble(Reservation::getMonthlyPrice).sum();
    }

    private List<Reservation> getFullMonthReservations(Date calculateForDate, List<Reservation> reservations) {

        List<Reservation> fullMonthReservations = new ArrayList<>();

        reservations.forEach(reservation -> {
            Calendar cal = Calendar.getInstance();
            cal.setTime(calculateForDate);

            int calculateForDateMonth = 12 * cal.get(Calendar.YEAR) + (cal.get(Calendar.MONTH) + 1);

            cal.setTime(reservation.getStartDay());
            int reservationStartMonth = 12 * cal.get(Calendar.YEAR) + (cal.get(Calendar.MONTH) + 1);


            cal.setTime(reservation.getEndDay());
            int reservationEndMonth = 12 * cal.get(Calendar.YEAR) + (cal.get(Calendar.MONTH) + 1);

            if (reservationEndMonth < 0) {
                reservationEndMonth = Integer.MAX_VALUE;
            }


            if (calculateForDateMonth >= reservationStartMonth && calculateForDateMonth < reservationEndMonth) {
                fullMonthReservations.add(reservation);
            }
        });

        return fullMonthReservations;
    }

    @Override
    public int calculateUnreservedCapacityForMonth(String yearAndMonth, List<Reservation> reservations) {

        yearAndMonth += "-01";
        int capacity = -1;

        Date calculateForDate;
        try {
            calculateForDate = DateUtils.convertYearAndMonthToDate(yearAndMonth);

            List<Reservation> fullMonthReservations = getFullMonthReservations(calculateForDate, reservations);
            List<Reservation> partialMonthReservations = getPartialMonthReservations(calculateForDate, reservations);

            List<Reservation> unreservedOffices = new ArrayList<>();

            reservations.forEach(reservation -> {
                if(!fullMonthReservations.contains(reservation) && !partialMonthReservations.contains(reservation))
                {
                    unreservedOffices.add(reservation);
                }
            });

            return unreservedOffices.stream().mapToInt(Reservation::getCapacity).sum();


        } catch (ParseException e) {
            String errorMessage = "Invalid month format. Should has YYYY-mm pattern.";
            logger.error(errorMessage);
        }

        return capacity;
    }
}
