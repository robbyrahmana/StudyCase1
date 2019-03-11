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
import com.mitrais.service.EmployeeService;
import com.mitrais.service.impl.EmployeeServiceImpl;

@RunWith(Parameterized.class)
public class SaveTest {
	
	private static EmployeeService service;
	
	@Parameter
	public Employee data;
	
	@Parameter(1)
	public boolean expected;
	
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
			{new Employee(null, "Robby", "Pekanbaru"), true},
			{new Employee(null, "Robby2", "Pekanbaru3"), true},
			{new Employee(null, "Robby3", "Pekanbaru3"), true},
			{new Employee(null, "Robby4", "Pekanbaru4"), true},
		});
	}
	
	@BeforeClass
	public static void prepareService() {
		service = EmployeeServiceImpl.getInstance();
	}
	
	@Test
	public void saveData() {
		assertEquals(expected, service.save(data));
	}
	
	
	
}
