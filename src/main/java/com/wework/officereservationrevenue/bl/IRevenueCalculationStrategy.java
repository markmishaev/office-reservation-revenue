package com.wework.officereservationrevenue.bl;

import com.wework.officereservationrevenue.model.Reservation;

import java.util.List;

public interface IRevenueCalculationStrategy {
    double calculateRevenueForMonth(String yearAndMonth, List<Reservation> reservations);
    int calculateUnreservedCapacityForMonth(String yearAndMonth, List<Reservation> reservations);
}
