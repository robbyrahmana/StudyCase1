package com.mitrais.service.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mitrais.bean.Employee;
import com.mitrais.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

	private static EmployeeService instance;

	private EmployeeServiceImpl() {
	}

	public static EmployeeService getInstance() {
		if (instance == null) {
			instance = new EmployeeServiceImpl();
		}
		return instance;
	}

	@Override
	public List<Employee> findAll() {
		List<Employee> data = new ArrayList<>();

		PreparedStatement statement = getDataHandler().prepare("select * from employee");

		try (ResultSet result = statement.executeQuery()) {
			while (result.next()) {
				Employee employee = new Employee();
				employee.setId(result.getInt(1));
				employee.setName(result.getString(2));
				employee.setAddress(result.getString(3));
				data.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return data;
	}

	@Override
	public Employee getID(Integer id) {
		Employee data = new Employee();
		PreparedStatement statement = getDataHandler().prepare("select * from employee where id=?");
		try {
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				data.setId(result.getInt(1));
				data.setName(result.getString(2));
				data.setAddress(result.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public boolean save(Employee data) {
		boolean status = false;

		PreparedStatement statement = getDataHandler().prepare("insert into employee (name, address) values (?, ?)");
		try {
			statement.setString(1, data.getName());
			statement.setString(2, data.getAddress());
			int result = statement.executeUpdate();
			if (result > 0) {
				status = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;
	}

	@Override
	public boolean update(Employee data) {
		boolean status = false;

		PreparedStatement statement = getDataHandler().prepare("update employee set name=?, address=? where id=?");
		try {
			statement.setString(1, data.getName());
			statement.setString(2, data.getAddress());
			statement.setInt(3, data.getId());
			int result = statement.executeUpdate();
			if (result > 0) {
				status = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;
	}

	@Override
	public boolean delete(Integer id) {
		boolean status = false;

		PreparedStatement statement = getDataHandler().prepare("delete FROM employee where id=?");
		try {
			statement.setInt(1, id);
			int result = statement.executeUpdate();
			if (result > 0) {
				status = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;
	}
	
	@Override
	public boolean deleteAll() {
		boolean status = false;

		PreparedStatement statement = getDataHandler().prepare("TRUNCATE employee");
		try {
			int result = statement.executeUpdate();
			status = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;
	}

}
