package com.mitrais.dao;

import com.mitrais.bean.Employee;
import com.mitrais.core.dao.DAO;

public interface EmployeeDAO extends DAO<Employee, Integer>{
	boolean deleteAll();
}
