package com.mitrais.service.impl;

import java.util.List;

import com.mitrais.bean.Employee;
import com.mitrais.core.container.DataContainer;
import com.mitrais.core.controller.Action;
import com.mitrais.dao.EmployeeDAO;
import com.mitrais.dao.impl.EmployeeDAOImpl;
import com.mitrais.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {
	
	private final String pageList = "/employee/list";
	private final String pageAdd = "/employee/add";
	private final String pageModify = "/employee/modify";
	
	private static EmployeeServiceImpl instance;
	
	EmployeeDAO service = EmployeeDAOImpl.getInstance();
	
	private EmployeeServiceImpl() {}

	public static EmployeeServiceImpl getInstance() {
		if (instance == null) {
			instance = new EmployeeServiceImpl();
		}
		return instance;
	}

	public DataContainer<Employee> getID(Integer id) {
		DataContainer<Employee> dataContainer = new DataContainer<Employee>();
		dataContainer.setData(service.getID(id));
		dataContainer.setRedirectPage(pageModify);
		return dataContainer;
	}

	public DataContainer<List<Employee>> findAll() {
		DataContainer<List<Employee>> dataContainer = new DataContainer<List<Employee>>();
		dataContainer.setData(service.findAll());
		dataContainer.setRedirectPage(pageList);
		return dataContainer;
	}

	public DataContainer<Employee> save(Employee data) {
		System.out.println(data);
		
		DataContainer<Employee> dataContainer = new DataContainer<Employee>();
		
		if (data.getName() == null || data.getAddress() == null) {
			dataContainer.setRedirectPage(pageAdd);
		} else if (data.getName().equals("") || data.getAddress().equals("")) {
			dataContainer.setMessage("Record is not complete!");
			dataContainer.setData(data);
			dataContainer.setStatus(false);
			dataContainer.setRedirectPage(pageAdd);
		} else {
			boolean result = service.save(data);
			if (result) {
				dataContainer.setRedirectAction(Action.LIST.getName());
			} else {
				dataContainer.setMessage("Unable to insert record");
				dataContainer.setData(data);
				dataContainer.setStatus(false);
				dataContainer.setRedirectPage(pageAdd);
			}
		}
		
		return dataContainer;
	}
	
	public DataContainer<Employee> update(Employee data) {
		System.out.println(data);
		
		DataContainer<Employee> dataContainer = new DataContainer<Employee>();
		
		if (data.getName().equals("") || data.getAddress().equals("")) {
			dataContainer.setMessage("Record is not complete!");
			dataContainer.setStatus(false);
			dataContainer.setData(data);
			dataContainer.setRedirectPage(pageModify);
		} else {
			boolean result = service.update(data);
			if (result) {
				dataContainer.setRedirectAction(Action.LIST.getName());
			} else {
				dataContainer.setMessage("Unable to update record");
				dataContainer.setStatus(false);
				dataContainer.setData(data);
				dataContainer.setRedirectPage(pageList);
			}
		}
		
		return dataContainer;
	}
	
	public DataContainer<Employee> delete(Integer id) {
		DataContainer<Employee> dataContainer = new DataContainer<Employee>();
		
		boolean status = service.delete(id);
		if (status) {
			dataContainer.setRedirectAction(Action.LIST.getName());
		} else {
			dataContainer.setMessage("Unable to delete record");
			dataContainer.setStatus(false);
			dataContainer.setRedirectAction(Action.LIST.getName());
		}
		
		return dataContainer;
	}
		
}
