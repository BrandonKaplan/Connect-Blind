package view;

import javafx.application.Application;

public class ConnectBlind {
	
	public static void main(String[] arguments) {
		if (arguments[0].equals("gui")) {
			//Application.launch(ConnectBlindGUIView.class);
		} else {
			ConnectBlindText.main(arguments);
		}
	}
}
