package com.mitrais.core.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mitrais.core.helper.ReadPropertiesFiles;

public class DatabaseConnection {

	private static Connection instance;

	private DatabaseConnection() {
	}

	public static Connection getInstance(String propertiesFile) {
		if (instance == null) {
			instance = dataConnection(propertiesFile);
		}
		return instance;
	}

	private static Connection dataConnection(String propertiesFile) {
		Properties properties = ReadPropertiesFiles.getInstance(propertiesFile);
		
		System.out.printf("Database Properties ========%n %-20s -> %s%n %-20s -> %s%n %-20s -> %s%n %-20s -> %s%n============================%n",
				"database.driver", properties.getProperty("database.driver"),
				"database.url", properties.getProperty("database.url"),
				"database.user", properties.getProperty("database.user"),
				"database.password", properties.getProperty("database.password"));
		
		Connection connection = null;
		try {
			Class.forName(properties.getProperty("database.driver"));
			connection = DriverManager.getConnection(
					properties.getProperty("database.url"),
					properties.getProperty("database.user"), 
					properties.getProperty("database.password"));
		} catch (ClassNotFoundException e) {
			System.out.println("Unable to find driver " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Unable to create connection " + e.getMessage());
		}
		return connection;
	}

}
