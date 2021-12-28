/**
 * @author Brandon Kaplan
 * FILE: ConnectBlind.java
 * DESCRIPTION: Runs the two different versions of Connect Blind 
 * depending on the user's arguments 
 * 
 * USAGE: Accepts one command line argument which is either "GUI" for
 * the JavaFX version of Connect Blind or "TEXT" which would bring up 
 * the ASCII version of the game
 * 
 * RELATED FILES: 
 */
package view;

import javafx.application.Application;

public class ConnectBlind {
	
	public static void main(String[] arguments) {
		// JavaFX version
		if (arguments[0].equals("GUI")) {
			Application.launch(ConnectBlindGUIView.class);
		// ASCII version 
		} else {
			ConnectBlindText.main(arguments);
		}
	}
}
