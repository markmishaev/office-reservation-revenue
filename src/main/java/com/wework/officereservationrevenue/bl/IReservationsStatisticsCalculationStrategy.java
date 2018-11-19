package com.wework.officereservationrevenue.bl;

import com.wework.officereservationrevenue.model.Reservation;
import com.wework.officereservationrevenue.model.ReservationsStatistics;

import java.util.List;

public interface IReservationsStatisticsCalculationStrategy {
    ReservationsStatistics calculateReservationsStatistics(String yearAndMonth, List<Reservation> reservations);
}
