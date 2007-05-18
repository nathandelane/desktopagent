package org.lane.desktopagent;

/*
 * The DesktopAgentProperties class takes care of loading the XML properties
 * file and creating a default XML properties file if one is not present 
 * already.
 * 
 * @author Nathan Lane
 * @date 5/17/2007
 */

import java.io.*;
import java.util.*;
import java.awt.*;

public class DesktopAgentProperties {
	
	private Properties properties;
	private File propertiesFile;
	
	private final String propertiesFileName = "DestopAgentProperties.properties"; 
	
	public DesktopAgentProperties(boolean createDefaultPropertiesEverytime) {
		properties = new Properties();
		propertiesFile = new File(propertiesFileName);
		
		try {
			if(!propertiesFile.exists() || createDefaultPropertiesEverytime) {
				Main.logger.warning(Main.DEFAULT, "Properties file does not exist");
				Main.logger.info(Main.DEFAULT, "Creating default properties file");
				propertiesFile = createDesktopAgentPropertiesFile(propertiesFileName);
				Main.logger.info(Main.DEFAULT, "Writing default properties file");
				properties = writeDefaultDesktopAgentPropertiesFile(propertiesFile);
			} else {
				properties.loadFromXML(new FileInputStream(propertiesFile));
				Main.logger.info(Main.DEFAULT, "Successfully loaded properties file");
			}
		} catch(InvalidPropertiesFormatException ipfe) {
			Main.logger.severe(Main.DEFAULT, ipfe.getMessage());
		} catch(IOException ioe) {
			Main.logger.severe(Main.DEFAULT, ioe.getMessage());
		}
	}
	
	public int getInt(String key) {
		Integer tempInt = Integer.parseInt((String)properties.get(key));
		int i = tempInt.intValue();
		
		return i;
	}
	
	public Color getColor(String key) {
		Color tempColor = null;
		Integer tempInt = null;
		
		String hexTriplet = (String)properties.get(key);
		// Convert to char arrays
		char[] hexChars = hexTriplet.toCharArray();
		char[] red = { hexChars[0], hexChars[1] };
		char[] green = { hexChars[2], hexChars[3] };
		char[] blue = { hexChars[4], hexChars[5] };
		// Convert to Strings
		String r = new String(red);
		String g = new String(green);
		String b = new String(blue);
		// Convert to ints
		tempInt = Integer.parseInt(r);
		int rValue = tempInt.intValue();
		tempInt = Integer.parseInt(g);
		int gValue = tempInt.intValue();
		tempInt = Integer.parseInt(b);
		int bValue = tempInt.intValue();
		
		tempColor = new Color(rValue, gValue, bValue);
		
		return tempColor;
	}
	
	public String getString(String key) {
		return (String)properties.get(key);
	}
	
	public void set(String key, String value) {
		properties.put(key, value);
	}
	
	public void writeProperties() throws InvalidPropertiesFormatException, IOException {
		FileOutputStream fos = new FileOutputStream(propertiesFile);
		properties.storeToXML(fos, "Desktop Agent Properties File", "UTF-8");
	}
	
	private File createDesktopAgentPropertiesFile(String propertiesFileName) {
		File tempFile = null;
		
		tempFile = new File(propertiesFileName);
		
		try {
			if(tempFile.createNewFile()) {
				
			}
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		return tempFile;
	}
	
	private Properties writeDefaultDesktopAgentPropertiesFile(File objPropertiesFile) throws InvalidPropertiesFormatException, IOException {
		Properties tempProps = new Properties();
		FileOutputStream fos = new FileOutputStream(objPropertiesFile);
		
		tempProps.put("desktop_agent_left", "0");
		tempProps.put("desktop_agent_top", "0");
		tempProps.put("desktop_agent_width", "300");
		tempProps.put("desktop_agent_height", "450");
		tempProps.put("desktop_agent_use_custom_window_controls", "false");
		tempProps.put("desktop_agent_background_color", "808080");
		tempProps.put("desktop_agent_buttons_background_color", "808080");
		
		tempProps.put("agents_directory", "Desktop Agent Agents");
		tempProps.put("number_of_agents", "1");
		tempProps.put("agent_files", "GreetingAgent.agent");
		
		tempProps.storeToXML(fos, "Desktop Agent Properties File", "UTF-8");
		
		return tempProps;
	}

}
