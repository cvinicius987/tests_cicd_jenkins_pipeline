package br.ce.wcaquino.taskbackend.utils;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

public class DateUtilsTest {

	@Test
	public void beforeTest() {
		
		LocalDate date = LocalDate.of(2015,01,01);
		
		boolean result = DateUtils.isEqualOrFutureDate(date);
		
		Assert.assertFalse(result);
	}
	
	@Test
	public void presentTest() {
		
		LocalDate date = LocalDate.now();
		
		boolean result = DateUtils.isEqualOrFutureDate(date);
		
		Assert.assertTrue(result);
	}
	
	@Test
	public void futureTest() {
	
		LocalDate date = LocalDate.of(2030,01,01);
		
		boolean result = DateUtils.isEqualOrFutureDate(date);
		
		Assert.assertTrue(result);
	}
}