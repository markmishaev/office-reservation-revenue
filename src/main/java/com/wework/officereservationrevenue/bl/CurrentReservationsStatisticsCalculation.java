package com.wework.officereservationrevenue.bl;

import com.wework.officereservationrevenue.model.MonthRange;
import com.wework.officereservationrevenue.model.Reservation;
import com.wework.officereservationrevenue.model.ReservationsStatistics;
import com.wework.officereservationrevenue.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.*;

import static java.util.stream.Collectors.toCollection;

public class CurrentReservationsStatisticsCalculation implements IReservationsStatisticsCalculationStrategy {

    private final Logger logger = LoggerFactory.getLogger(CurrentReservationsStatisticsCalculation.class);

    @Override
    public ReservationsStatistics calculateReservationsStatistics(String yearAndMonth, List<Reservation> reservations) {

        ReservationsStatistics statistics = new ReservationsStatistics(0, 0);

        try {

            MonthRange monthRange = DateUtils.convertYearAndMonthToMonthRange(yearAndMonth);

            List<Reservation> relevantReservations = getReservationsAfterPreProcessing(monthRange, reservations);
            statistics = calculateReservationStatistics(relevantReservations);

        } catch (ParseException e) {
            String errorMessage = "Invalid month format. Should has YYYY-mm pattern.";
            logger.error(errorMessage);
        }

        return statistics;
    }

    private ReservationsStatistics calculateReservationStatistics(List<Reservation> partialMonthReservations) {

        ReservationsStatistics statisticsForMonth = new ReservationsStatistics(0, 0);

        partialMonthReservations.forEach(reservation -> {
            if (reservation.getMonthlyPrice() > 0) {
                int numberOfDaysInMonth = DateUtils.getNumberOfDaysInMonth(reservation.getStartDay());
                long numberOfDaysInDayOfInterest = DateUtils.getNumberOfDaysBetweenTwoDates(reservation.getStartDay(), reservation.getEndDay()) + 1;

                double ratio = (double) numberOfDaysInDayOfInterest / numberOfDaysInMonth;
                statisticsForMonth.incrementRevenue(Math.round(reservation.getMonthlyPrice() * ratio));
            } else {
                statisticsForMonth.incrementUnreservedOfficesCapacity(reservation.getCapacity());
            }
        });

        return statisticsForMonth;
    }


    private List<Reservation> getReservationsAfterPreProcessing(MonthRange monthRange, List<Reservation> reservations) {

        ArrayList<Reservation> preprocessedReservations = reservations.stream().map(Reservation::new).collect(toCollection(ArrayList::new));

        preprocessedReservations.forEach(reservation -> {

            if (reservation.getStartDay().after(monthRange.getEndDate()) ||
                    reservation.getEndDay().before(monthRange.getStartDate())) {
                reservation.setMonthlyPrice(0);
            } else {
                if (reservation.getStartDay().before(monthRange.getStartDate())) {
                    reservation.setStartDay(monthRange.getStartDate());
                }
                if (reservation.getEndDay().after(monthRange.getEndDate())) {
                    reservation.setEndDay(monthRange.getEndDate());
                }
            }


        });

        return preprocessedReservations;
    }
}
