package com.wework.officereservationrevenue;

import com.wework.officereservationrevenue.bl.CurrentReservationsStatisticsCalculation;
import com.wework.officereservationrevenue.dao.ReservationRepository;
import com.wework.officereservationrevenue.model.Reservation;
import com.wework.officereservationrevenue.model.ReservationsStatistics;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration

public class OfficeReservationRevenueApplicationTests {

    @Test
    public void testReservationStatisticsCalculation() {

        ReservationRepository da = new ReservationRepository();
        List<Reservation> reservations = da.getReservations();

        CurrentReservationsStatisticsCalculation currentReservationsStatisticsCalculation = new CurrentReservationsStatisticsCalculation();

        System.out.println("************testReservationStatisticsCalculation******************");

        String monthOfInterest = "2018-01";

        ReservationsStatistics statistics = currentReservationsStatisticsCalculation.calculateReservationsStatistics(monthOfInterest, reservations);
        Assert.assertEquals(75500, statistics.getRevenue(), 0.0);
        System.out.println("Offices revenue for the month " + monthOfInterest + " is: " + statistics.getRevenue());
        Assert.assertEquals(137, statistics.getUnreservedOfficesCapacity());
        System.out.println("Total capacity for unreserved offices for the month " + monthOfInterest + " is: " + statistics.getUnreservedOfficesCapacity());

        monthOfInterest = "2000-01";

        statistics = currentReservationsStatisticsCalculation.calculateReservationsStatistics(monthOfInterest, reservations);
        Assert.assertEquals(0, statistics.getRevenue(), 0.0);
        System.out.println("Offices revenue for the month " + monthOfInterest + " is: " + statistics.getRevenue());
        Assert.assertEquals(266, statistics.getUnreservedOfficesCapacity());
        System.out.println("Total capacity for unreserved offices for the month " + monthOfInterest + " is: " + statistics.getUnreservedOfficesCapacity());
    }

    @Test
    public void testReservationStatisticsCalculationForMoreDates() {

        ReservationRepository da = new ReservationRepository();
        List<Reservation> reservations = da.getReservations();

        CurrentReservationsStatisticsCalculation currentReservationsStatisticsCalculation = new CurrentReservationsStatisticsCalculation();

        System.out.println("************testReservationInsightsCalculationForMoreDates******************");

        String monthOfInterest = "2013-01";

        ReservationsStatistics statistics = currentReservationsStatisticsCalculation.calculateReservationsStatistics(monthOfInterest, reservations);
        Assert.assertEquals(8100, statistics.getRevenue(), 0.0);
        System.out.println("Offices revenue for the month " + monthOfInterest + " is: " + statistics.getRevenue());
        Assert.assertEquals(254, statistics.getUnreservedOfficesCapacity());
        System.out.println("Total capacity for unreserved offices for the month " + monthOfInterest + " is: " + statistics.getUnreservedOfficesCapacity());

        monthOfInterest = "2013-06";

        statistics = currentReservationsStatisticsCalculation.calculateReservationsStatistics(monthOfInterest, reservations);
        Assert.assertEquals(15150, statistics.getRevenue(), 0.0);
        System.out.println("Offices revenue for the month " + monthOfInterest + " is: " + statistics.getRevenue());
        Assert.assertEquals(241, statistics.getUnreservedOfficesCapacity());
        System.out.println("Total capacity for unreserved offices for the month " + monthOfInterest + " is: " + statistics.getUnreservedOfficesCapacity());

        monthOfInterest = "2014-03";

        statistics = currentReservationsStatisticsCalculation.calculateReservationsStatistics(monthOfInterest, reservations);
        Assert.assertEquals(37215, statistics.getRevenue(), 0.0);
        System.out.println("Offices revenue for the month " + monthOfInterest + " is: " + statistics.getRevenue());
        Assert.assertEquals(203, statistics.getUnreservedOfficesCapacity());
        System.out.println("Total capacity for unreserved offices for the month " + monthOfInterest + " is: " + statistics.getUnreservedOfficesCapacity());


        monthOfInterest = "2014-09";

        statistics = currentReservationsStatisticsCalculation.calculateReservationsStatistics(monthOfInterest, reservations);
        Assert.assertEquals(86700, statistics.getRevenue(), 0.0);
        System.out.println("Offices revenue for the month " + monthOfInterest + " is: " + statistics.getRevenue());
        Assert.assertEquals(120, statistics.getUnreservedOfficesCapacity());
        System.out.println("Total capacity for unreserved offices for the month " + monthOfInterest + " is: " + statistics.getUnreservedOfficesCapacity());

        monthOfInterest = "2015-07";

        statistics = currentReservationsStatisticsCalculation.calculateReservationsStatistics(monthOfInterest, reservations);
        Assert.assertEquals(76226, statistics.getRevenue(), 0.0);
        System.out.println("Offices revenue for the month " + monthOfInterest + " is: " + statistics.getRevenue());
        Assert.assertEquals(135, statistics.getUnreservedOfficesCapacity());
        System.out.println("Total capacity for unreserved offices for the month " + monthOfInterest + " is: " + statistics.getUnreservedOfficesCapacity());
    }
}
