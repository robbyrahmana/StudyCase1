package com.mitrais.core.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ActionController {
	void getSingleAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	void getListAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	void insertAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	void updateAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	void deleteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
