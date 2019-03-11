package com.mitrais.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mitrais.bean.Employee;
import com.mitrais.core.controller.CRUDController;
import com.mitrais.core.controller.Dispatcher;
import com.mitrais.service.EmployeeService;
import com.mitrais.service.impl.EmployeeServiceImpl;

@SuppressWarnings("serial")
@WebServlet("/employee")
public class EmployeeController extends CRUDController {
	
	private List<Employee> employees;
	private Employee employee;
	private boolean status;
	private String message;

	EmployeeService service = EmployeeServiceImpl.getInstance();

	@Override
	public void getSingleAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		employee = service.getID(Integer.parseInt(request.getParameter("id")));
		status = true;

		request.setAttribute("status", status);
		request.setAttribute("employee", employee);

		RequestDispatcher dispatcher = Dispatcher.getDispatcher().location(request, "/employee/modify");
		dispatcher.forward(request, response);
	}

	@Override
	public void getListAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		status = true;
		employees = service.findAll();
		
		request.setAttribute("status", status);
		request.setAttribute("employees", employees);

		RequestDispatcher dispatcher = Dispatcher.getDispatcher().location(request, "/employee/list");
		dispatcher.forward(request, response);
	}

	@Override
	public void insertAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		status = true;

		request.setAttribute("status", status);

		String name = request.getParameter("name");
		String address = request.getParameter("address");

		if (name == null || address == null) {
			RequestDispatcher dispatcher = Dispatcher.getDispatcher().location(request, "/employee/add");
			dispatcher.forward(request, response);
		} else if (name.equals("") || address.equals("")) {
			message = "Record is not complete!";
			status = false;
			request.setAttribute("status", status);
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = Dispatcher.getDispatcher().location(request, "/employee/add");
			dispatcher.forward(request, response);
		} else {
			Employee data = new Employee();
			data.setName(name);
			data.setAddress(address);
			boolean result = service.save(data);
			if (result) {
				getListAction(request, response);
			} else {
				message = "Unable to insert record";
				status = false;
				request.setAttribute("status", status);
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = Dispatcher.getDispatcher().location(request, "/employee/add");
				dispatcher.forward(request, response);
			}
		}

	}

	@Override
	public void updateAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		status = true;

		request.setAttribute("status", status);
		
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		
		Employee data = new Employee();
		data.setId(id);
		data.setName(name);
		data.setAddress(address);
		
		request.setAttribute("employee", data);

		if (name.equals("") || address.equals("")) {
			message = "Record is not complete!";
			status = false;
			request.setAttribute("status", status);
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = Dispatcher.getDispatcher().location(request, "/employee/modify");
			dispatcher.forward(request, response);
		} else {
			boolean result = service.update(data);
			if (result) {
				getListAction(request, response);
			} else {
				message = "Unable to update record";
				status = false;
				request.setAttribute("status", status);
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = Dispatcher.getDispatcher().location(request, "/employee/modify");
				dispatcher.forward(request, response);
			}
		}

	}

	@Override
	public void deleteAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		status = true;
		int id = Integer.parseInt(request.getParameter("id"));
		
		status = service.delete(id);
		
		if (status) {
			getListAction(request, response);
		} else {
			status = false;
			request.setAttribute("status", status);
			request.setAttribute("message", "Unable to delete record");
			getListAction(request, response);
		}
		
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
