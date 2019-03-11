package com.mitrais.core.datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHandler {
	
	private static DatabaseHandler instance;
	private static Connection connection;
	
	static {
		connection = DatabaseConnection.getInstance("database.properties");
	}
	
	
	private DatabaseHandler() {}

	public static DatabaseHandler getInstance() {
		if (instance == null) {
			instance = new DatabaseHandler();
		}
		return instance;
	}
	
	public PreparedStatement prepare(String query) {
		System.out.printf("Starting execute query [%s]%n", query);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(query);
		} catch (SQLException e) {
			System.out.println("Unable to execute query " + e.getMessage());
		}
		return preparedStatement;
	}
	
}
