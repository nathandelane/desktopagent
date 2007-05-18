package org.lane.desktopagent.gui;

/*
 * DektopAgentGUI class encompasses the Dektop Agent GUI.
 * The GUI should be somewhat skinnable, but simplistic in design.
 * 
 * @author Nathan Lane
 * @date 5/17/2007
 */

import javax.swing.*;
import java.awt.*;
// Local packages
import org.lane.desktopagent.*;
import org.lane.desktopagent.gui.events.*;

public class DesktopAgentGUI extends JFrame {
	
	public static final long serialVersionUID = 1;
	
	public DesktopAgentGUI() {
		super("DT-Agent");
		
		try {
			setProperties();
			createUI();
			addHandlers();
			setVisibilityOptions();
		} catch(IllegalAccessException iae) {
			Main.logger.severe(Main.DEFAULT, iae.getMessage());
		} catch(InstantiationException ie) {
			Main.logger.severe(Main.DEFAULT, ie.getMessage());
		} catch(ClassNotFoundException cnfe) {
			Main.logger.severe(Main.DEFAULT, cnfe.getMessage());
		} catch(UnsupportedLookAndFeelException ulafe) {
			Main.logger.severe(Main.DEFAULT, ulafe.getMessage());
		}
	}
	
	private void setProperties() {
		setLayout(new BorderLayout());
		setSize(Main.properties.getInt("desktop_agent_width"), Main.properties.getInt("desktop_agent_height"));
		setLocation(Main.properties.getInt("desktop_agent_left"), Main.properties.getInt("desktop_agent_top"));
	}
	
	private void createUI() throws IllegalAccessException, InstantiationException, ClassNotFoundException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	}
	
	private void addHandlers() {
		addWindowListener(new DesktopAgentWindowHandler());
	}
	
	private void setVisibilityOptions() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}
	
}
