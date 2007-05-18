package org.lane.desktopagent.reporting;

/*
 * DesktopAgentLogger is a Logger component to log information pertaining
 * to usage of the DesktopAgent
 * 
 * @author Nathan Lane
 * @date 5/17/2007
 */

import java.util.logging.*;
import java.util.*;
import java.io.*;
//Local packages
import org.lane.desktopagent.*;

public class DesktopAgentLogger {
	
	private Hashtable<String, Logger> loggers;
	private String logFilename;
	
	public DesktopAgentLogger() {
		initLogger();
	}
	
	public DesktopAgentLogger(String loggerName) {
		initLogger();
		loggers.put(loggerName, Logger.getLogger(loggerName));
	}
	
	public void add(String loggerName) {
		loggers.put(loggerName, Logger.getLogger(loggerName));
		addFileHandler(loggerName);
	}
	
	public void info(String loggerName, String message) {
		Logger tempLogger = getLogger(loggerName);
		
		tempLogger.info(message);
	}
	
	public void severe(String loggerName, String message) {
		Logger tempLogger = getLogger(loggerName);
		
		tempLogger.severe(message);
	}
	
	public void warning(String loggerName, String message) {
		Logger tempLogger = getLogger(loggerName);
		
		tempLogger.warning(message);
	}
	
	public void throwing(String loggerName, String className, String methodName, Throwable exception) {
		Logger tempLogger = getLogger(loggerName);
		
		tempLogger.throwing(className, methodName, exception);
	}
	
	public void fine(String loggerName, String message) {
		Logger tempLogger = getLogger(loggerName);
		
		tempLogger.fine(message);
	}
	
	public void log(String loggerName, Level level, String message) {
		Logger tempLogger = getLogger(loggerName);
		
		tempLogger.log(level, message);
	}
	
	public void config(String loggerName, String message) {
		Logger tempLogger = getLogger(loggerName);
		
		tempLogger.config(message);
	}
	
	private Logger getLogger(String loggerName) {
		Logger tempLogger = null;
		
		if(loggers.containsKey(loggerName)) {
			tempLogger = loggers.get(loggerName);
		} else {
			tempLogger = loggers.get(Main.DEFAULT);
		}

		return tempLogger;
	}
	
	private void initLogger() {
		loggers = new Hashtable<String, Logger>();
		loggers.put(Main.DEFAULT, Logger.getAnonymousLogger());
		logFilename = Main.DEFAULT + ".log"; //"_" + Calendar.getInstance().getTimeInMillis() + ".log";
		addFileHandler(Main.DEFAULT);
	}
	
	private void addFileHandler(String loggerName) {
		try {
			loggers.get(Main.DEFAULT).addHandler(new FileHandler(logFilename));
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
}
