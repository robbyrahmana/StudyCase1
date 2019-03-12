package com.mitrais.service.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.mitrais.bean.Employee;
import com.mitrais.dao.EmployeeDAO;
import com.mitrais.dao.impl.EmployeeDAOImpl;

@RunWith(Parameterized.class)
public class UpdateTest {
	
	private static EmployeeDAO service;
	
	@BeforeClass
	public static void getService() {
		service = EmployeeDAOImpl.getInstance();
	}
	
	@Parameter
	public Employee data;
	
	@Parameter(1)
	public boolean expected;
	
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
			{new Employee(2, "Robby22222", "Pekanbaru2222"), true},
			{new Employee(100, "Robby2", "Pekanbaru3"), true}
		});
	}
	
	@Test
	public void updateTest() {
		assertEquals(expected, service.update(data));
	}
	
}
