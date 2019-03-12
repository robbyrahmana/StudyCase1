package com.mitrais.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mitrais.bean.Employee;
import com.mitrais.core.datasource.DatabaseHandler;
import com.mitrais.dao.EmployeeDAO;

public class EmployeeDAOImpl implements EmployeeDAO {

	private static EmployeeDAO instance;

	private EmployeeDAOImpl() {
	}

	public static EmployeeDAO getInstance() {
		if (instance == null) {
			instance = new EmployeeDAOImpl();
		}
		return instance;
	}

	@Override
	public List<Employee> findAll() {
		List<Employee> data = new ArrayList<>();
		try (
				DatabaseHandler handler = getDataHandler();
			){
			PreparedStatement statement = handler.prepare("select * from employee");
			
			ResultSet result = statement.executeQuery();
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
		try (
				DatabaseHandler handler = getDataHandler();
			){
			PreparedStatement statement = handler.prepare("select * from employee where id=?");
			
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

		try (
				DatabaseHandler handler = getDataHandler();
			){
			PreparedStatement statement = getDataHandler().prepare("insert into employee (name, address) values (?, ?)");
			
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

		try (
				DatabaseHandler handler = getDataHandler();
			){
			PreparedStatement statement = getDataHandler().prepare("update employee set name=?, address=? where id=?");
			
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

		try (
				DatabaseHandler handler = getDataHandler();
			){
			PreparedStatement statement = getDataHandler().prepare("delete FROM employee where id=?");
			
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

		try (
				DatabaseHandler handler = getDataHandler();
			){
			PreparedStatement statement = getDataHandler().prepare("TRUNCATE employee");
			
			statement.executeUpdate();
			status = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;
	}

}
