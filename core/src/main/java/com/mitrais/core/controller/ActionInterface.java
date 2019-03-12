package com.mitrais.core.controller;

import java.io.IOException;

import javax.servlet.ServletException;

public interface ActionInterface {
	void getSingleAction() throws ServletException, IOException;

	void getListAction() throws ServletException, IOException;

	void insertAction() throws ServletException, IOException;

	void updateAction() throws ServletException, IOException;

	void deleteAction() throws ServletException, IOException;
}
