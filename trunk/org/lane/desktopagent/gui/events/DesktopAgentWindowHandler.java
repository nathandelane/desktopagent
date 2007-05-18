package org.lane.desktopagent.gui.events;

/*
 * This Window Handler will be use by the desktop agent to handle window events.
 * 
 * @author Nathan Lane
 * @date 5/17/2007
 */

import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import javax.swing.*;
import java.io.*;
import java.util.InvalidPropertiesFormatException;
//Local packages
import org.lane.desktopagent.*;

public class DesktopAgentWindowHandler implements WindowListener {
	
	public DesktopAgentWindowHandler() {
		// Default constructor - do nothing!?
	}
	
	public void windowActivated(WindowEvent we) {}
	
	public void windowClosed(WindowEvent we) {}
	
	public void windowClosing(WindowEvent we) {
		int response = JOptionPane.showConfirmDialog(Main.getDAGUI(), "Really Exit Desktop Agent?", "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);

		if(response == JOptionPane.CANCEL_OPTION) {
			return;
		} else {
			try {
				Main.properties.set("desktop_agent_left", "" + Main.getDAGUI().getLocation().x);
				Main.properties.set("desktop_agent_top", "" + Main.getDAGUI().getLocation().y);
				Main.properties.set("desktop_agent_width", "" + Main.getDAGUI().getSize().width);
				Main.properties.set("desktop_agent_height", "" + Main.getDAGUI().getSize().height);			
				Main.properties.writeProperties();
			} catch(InvalidPropertiesFormatException ipfe) {
				Main.logger.severe(Main.DEFAULT, ipfe.getMessage());
			} catch(IOException ioe) {
				Main.logger.severe(Main.DEFAULT, ioe.getMessage());
			}
		
			System.exit(0);
		}
	}
	
	public void windowDeactivated(WindowEvent we) {}
	
	public void windowDeiconified(WindowEvent we) {}
	
	public void windowIconified(WindowEvent we) {}
	
	public void windowOpened(WindowEvent we) {}

}
