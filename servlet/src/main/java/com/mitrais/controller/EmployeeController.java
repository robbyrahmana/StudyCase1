package com.mitrais.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.mitrais.bean.Employee;
import com.mitrais.core.container.DataContainer;
import com.mitrais.core.controller.CRUDController;
import com.mitrais.service.EmployeeService;
import com.mitrais.service.impl.EmployeeServiceImpl;

@SuppressWarnings("serial")
@WebServlet("/employee")
public class EmployeeController extends CRUDController {
	
	EmployeeService service = EmployeeServiceImpl.getInstance();

	@Override
	public void getSingleAction() throws ServletException, IOException {
		String id = request.getParameter("id");
		
		DataContainer<Employee> dataContainer = service.getID(Integer.parseInt(id));

		request.setAttribute("status", dataContainer.isStatus());
		request.setAttribute("message", dataContainer.getMessage());
		request.setAttribute("employees", dataContainer.getData());
		
		forwardingAction(dataContainer);
	}

	@Override
	public void getListAction() throws ServletException, IOException {
		DataContainer<List<Employee>> dataContainer = service.findAll();

		request.setAttribute("status", dataContainer.isStatus());
		request.setAttribute("message", dataContainer.getMessage());
		request.setAttribute("employees", dataContainer.getData());

		forwardingAction(dataContainer);
	}

	@Override
	public void insertAction() throws ServletException, IOException {
		String name = request.getParameter("name");
		String address = request.getParameter("address"); 
		
		DataContainer<Employee> dataContainer = service.save(new Employee(name, address));

		request.setAttribute("status", dataContainer.isStatus());
		request.setAttribute("message", dataContainer.getMessage());
		request.setAttribute("employees", dataContainer.getData());

		forwardingAction(dataContainer);
	}

	@Override
	public void updateAction() throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String address = request.getParameter("address"); 
		
		DataContainer<Employee> dataContainer = service.update(new Employee(Integer.parseInt(id), name, address));

		request.setAttribute("status", dataContainer.isStatus());
		request.setAttribute("message", dataContainer.getMessage());
		request.setAttribute("employees", dataContainer.getData());

		forwardingAction(dataContainer);
	}

	@Override
	public void deleteAction() throws ServletException, IOException {
		String id = request.getParameter("id");
		
		DataContainer<Employee> dataContainer = service.delete(Integer.parseInt(id));
		
		request.setAttribute("status", dataContainer.isStatus());
		request.setAttribute("message", dataContainer.getMessage());
		request.setAttribute("employees", dataContainer.getData());

		forwardingAction(dataContainer);
	}
}
