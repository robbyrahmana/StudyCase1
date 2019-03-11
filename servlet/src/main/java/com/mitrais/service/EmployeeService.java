package com.mitrais.service;

import com.mitrais.bean.Employee;
import com.mitrais.core.dao.DAO;

public interface EmployeeService extends DAO<Employee, Integer>{
	boolean deleteAll();
}
