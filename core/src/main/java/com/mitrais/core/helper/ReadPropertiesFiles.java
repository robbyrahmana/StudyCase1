package com.mitrais.core.helper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Properties;

public class ReadPropertiesFiles {
	
	private static Properties properties;
	
	private ReadPropertiesFiles() {}

	public static Properties getInstance(String filename) {
			
		LocalTime startTime = LocalTime.now();
		
		System.out.printf("Start loading %s...%n", filename);
		
		properties = new Properties();
		
		try {
			InputStream input = ReadPropertiesFiles.class.getClassLoader().getResourceAsStream(filename);
			properties.load(input);
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find file " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Unable to load properties file, " + e.getMessage());
		}
		
		LocalTime endTime = LocalTime.now();
		
		System.out.printf("%s is loaded within %s s%n", filename, Duration.between(startTime, endTime));
		
		return properties;
	}
	
}
