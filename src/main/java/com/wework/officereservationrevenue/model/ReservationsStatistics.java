package com.wework.officereservationrevenue.model;

public class ReservationsStatistics {

    private double revenue;
    private int unreservedOfficesCapacity;


    public ReservationsStatistics(double revenue, int unreservedOfficesCapacity) {
        this.revenue = revenue;
        this.unreservedOfficesCapacity = unreservedOfficesCapacity;
    }

    public double getRevenue() {
        return revenue;
    }


    public void incrementRevenue(double revenue) {
        this.revenue += revenue;
    }

    public int getUnreservedOfficesCapacity() {
        return unreservedOfficesCapacity;
    }

    public void incrementUnreservedOfficesCapacity(int unreservedOfficesCapacity) {
        this.unreservedOfficesCapacity += unreservedOfficesCapacity;
    }
}
