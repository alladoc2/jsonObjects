package com.gfs.projects.mrgames.init;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class InitServlet extends HttpServlet {
	
	private static Logger logger = Logger.getLogger(InitServlet.class);

	public void init() throws ServletException {
		System.out.println("Initializing MGS Application... ");
		
		// Initialize the Logging
		String log4jPropertiesFile = this.getClass().getClassLoader().getResource("log4jRuntime.xml").getFile();
		initializeLogging(log4jPropertiesFile);
		logger.info(".init Done initializing logging.");
	}
	
	private void initializeLogging(String log4jPropertiesFile) {
		DOMConfigurator.configureAndWatch(log4jPropertiesFile, 10000);
	}
}
