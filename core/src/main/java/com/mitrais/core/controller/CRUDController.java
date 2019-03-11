package com.mitrais.core.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

abstract public class CRUDController extends HttpServlet implements ActionController {

	private static final long serialVersionUID = -7907451592134064895L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");

		if (id != null && !id.equals("")) {
			getSingleAction(request, response);
		} else {
			getListAction(request, response);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("POST Action");
		if (action.equals("add") || action.equals("save")) {
			insertAction(request, response);
		} else if (action.equals("list")) {
			getListAction(request, response);
		} else if (action.equals("getId")) {
			doGet(request, response);
		} else if (action.equals("update")) {
			doPut(request, response);
		} else if (action.equals("delete")) {
			deleteAction(request, response);
		} 

	}

	public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		updateAction(request, response);
	}

	public void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		deleteAction(request, response);
	}
}
