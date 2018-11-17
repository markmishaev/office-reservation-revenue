package com.wework.officereservationrevenue.dao;

import com.wework.officereservationrevenue.model.Reservation;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ReservationRepository {

    private final CsvDataLoader csvDataLoader = new CsvDataLoader();

    public ReservationRepository() {

    }

    public List<Reservation> getReservations() {

        String RESERVATIONS_FILE = "data.csv";
        List<Reservation> reservations = csvDataLoader.loadObjectList(Reservation.class, RESERVATIONS_FILE);

        fillNullEndDaysWithMaxDateValue(reservations);

        return reservations;
    }

    private void fillNullEndDaysWithMaxDateValue(List<Reservation> reservations) {
       reservations.forEach(reservation -> {
           if(reservation.getEndDay() == null)
           {
               reservation.setEndDay(new Date(Long.MAX_VALUE));
           }
       });
    }
}
