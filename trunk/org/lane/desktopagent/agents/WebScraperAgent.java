package org.lane.desktopagent.agents;

/*
 * The web scraper agent allows a user to browse to a website and specify
 * which components of it to watch.  The user will also specify how often 
 * to check the components, and so on.
 * 
 * This agent should be able to look at one element at a time.
 * 
 * @author Nathan Lane
 * @date 5/17/2007
 */

import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
//Local packages
import org.lane.desktopagent.*;

public class WebScraperAgent {
	
	private URL httpUrl;
	private URLConnection httpUrlConnection;
	private BufferedReader websiteReader;
	private ArrayList<String> webSiteSource;
	private HTMLElement watchElement;
	
	public WebScraperAgent(String url) {
		// This constructor provides a URL to load a webpage
		Main.logger.info(Main.DEFAULT, "Created new Web Scraper Agent");
		String source = getWebPageSource(url);
		HTMLElement webPageTree = parseWebPage(source);
	}
	
	private HTMLElement parseWebPage(String pageSource) {
		Main.logger.info(Main.DEFAULT, "Parsing web page source");
		HTMLElement tempPageHTML = new HTMLElement();
		
		int offset = 0;
		while(offset < pageSource.length()) {
			int beginNextHTMLElement = pageSource.indexOf("<", offset);
			
			if((beginNextHTMLElement < pageSource.length()) && (beginNextHTMLElement > -1)) {
				int presumedEndNextHTMLElement = pageSource.indexOf(">", beginNextHTMLElement) + 1;
				
				if((presumedEndNextHTMLElement < pageSource.length()) && (presumedEndNextHTMLElement >= 0)) {
					String nextHTMLElement = pageSource.substring(beginNextHTMLElement, presumedEndNextHTMLElement);
					
					if(!nextHTMLElement.equalsIgnoreCase("<html>")) {
						int presumedBeginNextHTMLElement = pageSource.indexOf("<", presumedEndNextHTMLElement);
						
						if((presumedBeginNextHTMLElement > pageSource.length()) || (presumedBeginNextHTMLElement < 0)) {
							presumedBeginNextHTMLElement = (pageSource.length() - 1);
						}
		
						String innerHTML = pageSource.substring(presumedEndNextHTMLElement, presumedBeginNextHTMLElement);
						System.out.println("HTML Element: " + nextHTMLElement);
						System.out.println("Inner HTML: " + innerHTML);
						offset = presumedEndNextHTMLElement + innerHTML.length();
					} else {
						offset = pageSource.length();
					}
				} else {
					offset = pageSource.length();
				}
			} else {
				offset = pageSource.length();
			}
		}
		
		Main.logger.info(Main.DEFAULT, "Finished parsing web page source");
		return tempPageHTML;
	}
	
	private String getWebPageSource(String url) {
		Main.logger.info(Main.DEFAULT, "Getting source for " + url);
		String source = new String();
		
		try {
			httpUrl = new URL(url);
			httpUrlConnection = httpUrl.openConnection();
			httpUrlConnection.connect();
			websiteReader = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));
			webSiteSource = new ArrayList<String>();
			
			while((source = websiteReader.readLine()) != null) {
				webSiteSource.add(source);
			}
			
			source = "";
			int counter = 0;
			while(counter < webSiteSource.size()) {
				source += webSiteSource.get(counter) + "\n";
				++counter;
			}
		} catch(MalformedURLException mue) {
			Main.logger.severe(Main.DEFAULT, mue.getMessage());
		} catch(IOException ioe) {
			Main.logger.severe(Main.DEFAULT, ioe.getMessage());
		}
		
		return source;
	}
	
	private class WebScraperAgentSourceViewer extends JFrame {
		
		private JTree htmlTree;
		private JScrollPane htmlTreeScrollPane;
		
		public WebScraperAgentSourceViewer(String pageSource) {
			
		}
		
	}
	
	private class HTMLElement {
		
		private Hashtable<String, String> attributes;
		private String innerText;
		private ArrayList<HTMLElement> innerHTML;
		
		public HTMLElement() {
			attributes = new Hashtable<String, String>();
			innerText = new String("");
			innerHTML = new ArrayList<HTMLElement>();
		}
		
		public void addAttribute(String attribute, String value) {
			attributes.put(attribute, value);
		}
		
		public void setInnerText(String strInnerText) {
			innerText = strInnerText;
		}
		
		public void addInnerHTMLElement(HTMLElement htmlElement) {
			innerHTML.add(htmlElement);
		}
		
	}
	
}
