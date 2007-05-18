package org.lane.desktopagent;

/*
 * The Desktop Agent is a concept that is by far not original, but I haven't seen many
 * good free implementations written in Java, so this is my attempt.  Basically, the
 * idea is to create something that allows you to create sub-agents or bots that collect
 * information for you.  You can make the agent persist on the desktop or hide somwhere,
 * and you can customize it in many ways.  The Desktop Agent will act as a sort of framework
 * used to create these sub-agents and will control them and launch them.
 * 
 * For now the desktop agent will use a non-plugin architecture, or a static architecture.
 * 
 * Two sub agent types that I want to create are
 * 1. Web Scraper - which makes it possible to go out onto the Internet and constantly watch
 * a certain attribute, phrase, or whatever.
 * 2. Filesystem Notifier - which makes it possible to watch a certain directory or file to 
 * see if it changes or if something new appears there.
 * 
 * @author Nathan Lane
 * @date 5/17/2007
 */

// Local packages
import org.lane.desktopagent.gui.*;
import org.lane.desktopagent.reporting.*;

import org.lane.desktopagent.agents.*;

public class Main {
	
	public static DesktopAgentLogger logger;
	public static DesktopAgentProperties properties;
	private static DesktopAgentGUI desktopAgentGUI;
	public static final String DEFAULT = "Main";
	
	public Main() {
		logger = new DesktopAgentLogger();
		logger.info(Main.DEFAULT, "Loading properties");
		properties = new DesktopAgentProperties(false);
		logger.info(Main.DEFAULT, "Loading user interface");
		desktopAgentGUI = new DesktopAgentGUI();
	}
	
	public int runDesktopAgent() {
		int result = 0;
		
		WebScraperAgent wsa = new WebScraperAgent("http://www.vehix.com");
		
		return result;
	}
	
	public static void main(String args[]) {
		Main main = new Main();
		int result = 0;
		
		if((result = main.runDesktopAgent()) != 0) {
			Main.logger.severe(Main.DEFAULT, "Run Desktop returned error code " + result);
		}
	}
	
	public static DesktopAgentGUI getDAGUI() {
		return desktopAgentGUI;
	}

}
