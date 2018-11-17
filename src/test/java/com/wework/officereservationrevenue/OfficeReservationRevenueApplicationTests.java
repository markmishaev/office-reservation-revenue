package com.wework.officereservationrevenue;

import com.wework.officereservationrevenue.bl.CurrentRevenueCalculation;
import com.wework.officereservationrevenue.dao.ReservationRepository;
import com.wework.officereservationrevenue.model.Reservation;
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
	public void testRevenueCalculation() {

		ReservationRepository da = new ReservationRepository();
		List<Reservation> reservations = da.getReservations();

		CurrentRevenueCalculation currentRevenueCalculation = new CurrentRevenueCalculation();

		System.out.println("************testRevenueCalculation******************");

		double revenue1 = currentRevenueCalculation.calculateRevenueForMonth("2018-01", reservations);
		Assert.assertEquals (75500, revenue1, 0.0);
		System.out.println("Offices revenue for the month 2018-01 is: " + revenue1);

		double revenue2 = currentRevenueCalculation.calculateRevenueForMonth("2000-01", reservations);
		Assert.assertEquals (0, revenue2, 0.0);
		System.out.println("Offices revenue for the month 2000-01 is: " + revenue2);
	}

	@Test
	public void testCapacityCalculation() {

		ReservationRepository da = new ReservationRepository();
		List<Reservation> reservations = da.getReservations();

		CurrentRevenueCalculation currentRevenueCalculation = new CurrentRevenueCalculation();

		System.out.println("************testCapacityCalculation******************");

		int capacity1 = currentRevenueCalculation.calculateUnreservedCapacityForMonth("2018-01", reservations);
		Assert.assertEquals (137, capacity1);
		System.out.println("Total capacity for unreserved offices for the month 2018-01 is: " + capacity1);


		int capacity2 = currentRevenueCalculation.calculateUnreservedCapacityForMonth("2000-01", reservations);
		Assert.assertEquals (266, capacity2);
		System.out.println("Total capacity for unreserved offices for the month 2000-01 is: " + capacity2);
	}

	@Test
	public void testRevenueCalculationForMoreDates() {

		ReservationRepository da = new ReservationRepository();
		List<Reservation> reservations = da.getReservations();

		CurrentRevenueCalculation currentRevenueCalculation = new CurrentRevenueCalculation();

		System.out.println("************testRevenueCalculationForMoreDates******************");

		double revenue1 = currentRevenueCalculation.calculateRevenueForMonth("2013-01", reservations);
		Assert.assertEquals (8100, revenue1, 0.0);
		System.out.println("Offices revenue for the month 2013-01 is: " + revenue1);

		double revenue2 = currentRevenueCalculation.calculateRevenueForMonth("2013-06", reservations);
		Assert.assertEquals (17650, revenue2, 0.0);
		System.out.println("Offices revenue for the month 2013-06 is: " + revenue2);

		double revenue3 = currentRevenueCalculation.calculateRevenueForMonth("2014-03", reservations);
		Assert.assertEquals (37915, revenue3, 0.0);
		System.out.println("Offices revenue for the month 2014-03 is: " + revenue3);

		double revenue4 = currentRevenueCalculation.calculateRevenueForMonth("2014-09", reservations);
		Assert.assertEquals (86557, revenue4, 0.0);
		System.out.println("Offices revenue for the month 2014-09 is: " + revenue4);

		double revenue5 = currentRevenueCalculation.calculateRevenueForMonth("2015-07", reservations);
		Assert.assertEquals (76177, revenue5, 0.0);
		System.out.println("Offices revenue for the month 2015-07 is: " + revenue5);
	}

	@Test
	public void testCapacityCalculationForMoreDates() {

		ReservationRepository da = new ReservationRepository();
		List<Reservation> reservations = da.getReservations();

		CurrentRevenueCalculation currentRevenueCalculation = new CurrentRevenueCalculation();

		System.out.println("************testCapacityCalculationForMoreDates******************");

		int capacity1 = currentRevenueCalculation.calculateUnreservedCapacityForMonth("2013-01", reservations);
		Assert.assertEquals (254, capacity1);
		System.out.println("Total capacity for unreserved offices for the month 2013-01 is: " + capacity1);

		int capacity2 = currentRevenueCalculation.calculateUnreservedCapacityForMonth("2013-06", reservations);
		Assert.assertEquals (241, capacity2);
		System.out.println("Total capacity for unreserved offices for the month 2013-06 is: " + capacity2);

		int capacity3 = currentRevenueCalculation.calculateUnreservedCapacityForMonth("2014-03", reservations);
		Assert.assertEquals (207, capacity3);
		System.out.println("Total capacity for unreserved offices for the month 2014-03 is: " + capacity3);

		int capacity4 = currentRevenueCalculation.calculateUnreservedCapacityForMonth("2014-09", reservations);
		Assert.assertEquals (120, capacity4);
		System.out.println("Total capacity for unreserved offices for the month 2014-09 is: " + capacity4);

		int capacity5 = currentRevenueCalculation.calculateUnreservedCapacityForMonth("2015-07", reservations);
		Assert.assertEquals (135, capacity5);
		System.out.println("Total capacity for unreserved offices for the month 2015-07 is: " + capacity5);
	}
}
