package com.mitrais.core.datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHandler implements AutoCloseable {
	
	private static DatabaseHandler instance;
	private Connection connection;
	private PreparedStatement preparedStatement;
	
	private DatabaseHandler() {
		connection = DatabaseConnection.getInstance();
	}

	public static DatabaseHandler getInstance() {
		if (instance == null || instance.isClose()) {
			instance = new DatabaseHandler();
		}
		return instance;
	}
	
	public PreparedStatement prepare(String query) {
		System.out.printf("Starting execute query [%s]%n", query);
		
		try {
			preparedStatement = connection.prepareStatement(query);
		} catch (SQLException e) {
			System.out.println("Unable to execute query " + e.getMessage());
		}
		return preparedStatement;
	}

	@Override
	public void close() {
		try {
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("Unable to close connection");
		}
		System.out.println("Close connection");
	}	
	
	private boolean isClose() {
		boolean status = false;
		try {
			if (connection.isClosed()) {
				status = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	
}
