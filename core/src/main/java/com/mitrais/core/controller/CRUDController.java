package com.mitrais.core.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mitrais.core.container.DataContainer;

abstract public class CRUDController extends HttpServlet implements ActionInterface {

	private static final long serialVersionUID = -7907451592134064895L;

	public HttpServletRequest request;
	public HttpServletResponse response;

	public void forwardingAction(DataContainer<?> dataContainer) throws ServletException, IOException {
		String rm = dataContainer.getRedirectAction();
		
		if (rm != null) {
			if (Action.LIST.getName().equals(rm)) {
				getListAction();
			}
		} else {
			RequestDispatcher dispatcher = Dispatcher.getDispatcher().location(request, dataContainer.getRedirectPage());
			dispatcher.forward(request, response);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;

		String id = request.getParameter("id");

		if (id != null && !id.equals("")) {
			getSingleAction();
		} else {
			getListAction();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;

		String action = request.getParameter("action");
		System.out.println("POST Action");
		if (Action.ADD.getName().equals(action) || Action.SAVE.getName().equals(action)) {
			insertAction();
		} else if (Action.LIST.getName().equals(action)) {
			getListAction();
		} else if (Action.GETID.getName().equals(action)) {
			doGet(request, response);
		} else if (Action.UPDATE.getName().equals(action)) {
			doPut(request, response);
		} else if (Action.DELETE.getName().equals(action)) {
			doDelete(request, response);
		}

	}

	public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;

		updateAction();
	}

	public void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.request = request;
		this.response = response;

		deleteAction();
	}
}
