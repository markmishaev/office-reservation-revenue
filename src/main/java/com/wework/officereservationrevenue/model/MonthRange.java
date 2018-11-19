package com.wework.officereservationrevenue.model;

import java.util.Date;

public class MonthRange {

    private Date startDate;
    private Date endDate;

    public MonthRange(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
