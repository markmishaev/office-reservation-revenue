package com.wework.officereservationrevenue.model;

import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.Date;

/*
Reservation POJO
 */
public class Reservation {
    @JsonProperty("Capacity")
    private int Capacity;
    @JsonProperty("Monthly Price")
    private double MonthlyPrice;
    @JsonProperty("Start Day")
    private Date StartDay;
    @JsonProperty("End Day")
    private  Date EndDay;

    public int getCapacity() {
        return Capacity;
    }

    public double getMonthlyPrice() {
        return MonthlyPrice;
    }

    public void setMonthlyPrice(double monthlyPrice) {
        MonthlyPrice = monthlyPrice;
    }

    public Date getStartDay() {
        return StartDay;
    }

    public void setStartDay(Date startDay) {
        StartDay = startDay;
    }

    public Date getEndDay() {
        return EndDay;
    }

    public void setEndDay(Date endDay) {
        EndDay = endDay;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Reservation)) {
            return false;
        }
        Reservation reservation = (Reservation) o;
        return Capacity == reservation.getCapacity() &&
                MonthlyPrice == reservation.getMonthlyPrice() &&
                StartDay.getTime() == reservation.getStartDay().getTime() &&
                EndDay.getTime() == reservation.getEndDay().getTime();
    }
}
