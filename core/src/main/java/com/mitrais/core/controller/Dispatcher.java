package com.mitrais.core.controller;

import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import com.mitrais.core.helper.ReadPropertiesFiles;

public class Dispatcher {
	
	private static Dispatcher dispatcher;
	
	private static String PREFIX_URL;
	private static String SUFFIX_URL;
	
	static {
		Properties properties = ReadPropertiesFiles.getInstance("application.properties");
		
		PREFIX_URL = properties.getProperty("url.prefix");
		SUFFIX_URL = properties.getProperty("url.suffix");
	}

	public static Dispatcher getDispatcher() {
		if (dispatcher == null) {
			dispatcher = new Dispatcher();
		}
		return dispatcher;
	}
	
	public RequestDispatcher location(HttpServletRequest request, String file) {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(PREFIX_URL + file + SUFFIX_URL);
		return requestDispatcher;		
	}
	
	
}
