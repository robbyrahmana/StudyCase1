package com.mitrais.service.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.mitrais.service.EmployeeService;
import com.mitrais.service.impl.EmployeeServiceImpl;

@RunWith(Parameterized.class)
public class DeleteTest {
	
	private static EmployeeService service;
	
	@Parameter
	public int dataId;
	
	@Parameter(1)
	public boolean expected;
	
	@Parameters
	public static Collection<Object[]> getData() {
		return Arrays.asList(new Object[][] {
			{ 1, true},
			{200, false }
		});
	}
	
	@BeforeClass
	public static void loadService() {
		service = EmployeeServiceImpl.getInstance();
	}
	
	@Test
	public void deleteByID() {
		assertEquals(expected, service.delete(dataId));
	}
	
	@AfterClass
	public static void deleteAll() {
		assertEquals(true, service.deleteAll());
	}
}
