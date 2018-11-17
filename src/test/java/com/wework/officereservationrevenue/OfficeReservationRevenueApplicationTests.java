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

		double revenue1 = currentRevenueCalculation.calculateRevenueForMonth("2018-01", reservations);
		Assert.assertEquals (75500, revenue1, 0.0);

		double revenue2 = currentRevenueCalculation.calculateRevenueForMonth("2000-01", reservations);
		Assert.assertEquals (0, revenue2, 0.0);
	}

	@Test
	public void testCapacityCalculation() {

		ReservationRepository da = new ReservationRepository();
		List<Reservation> reservations = da.getReservations();

		CurrentRevenueCalculation currentRevenueCalculation = new CurrentRevenueCalculation();

		int capacity1 = currentRevenueCalculation.calculateUnreservedCapacityForMonth("2018-01", reservations);
		Assert.assertEquals (137, capacity1);

		int capacity2 = currentRevenueCalculation.calculateUnreservedCapacityForMonth("2000-01", reservations);
		Assert.assertEquals (266, capacity2);
	}

}
