package com.mitrais.core.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mitrais.core.helper.ReadPropertiesFiles;

public class DatabaseConnection {

	private static Connection instance;
	private static Properties properties;

	private DatabaseConnection() {}
	
	static {
		properties = ReadPropertiesFiles.getInstance("database.properties");	
		
		System.out.printf("Database Properties ========%n %-20s -> %s%n %-20s -> %s%n %-20s -> %s%n %-20s -> %s%n============================%n",
				"database.driver", properties.getProperty("database.driver"),
				"database.url", properties.getProperty("database.url"),
				"database.user", properties.getProperty("database.user"),
				"database.password", properties.getProperty("database.password"));
		
		try {
			Class.forName(properties.getProperty("database.driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
	}

	public static Connection getInstance() {
		try {
			if (instance == null || instance.isClosed()) {
				dataConnection();
			} 
		} catch (SQLException e) {
			System.out.println("Connection is not close");
		}
		return instance;
	}

	private static void dataConnection() {
		System.out.println("Establish new connection");		
		try {
			instance = DriverManager.getConnection(
					properties.getProperty("database.url"),
					properties.getProperty("database.user"), 
					properties.getProperty("database.password"));
		} catch (SQLException e) {
			System.out.println("Unable to create connection " + e.getMessage());
		}
	}

}
