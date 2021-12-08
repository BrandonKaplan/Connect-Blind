package view;

import java.util.Scanner;

import controller.ConnectBlindController;
import model.ConnectBlindModel;
import model.DiscPiece;

public class ConnectBlindText {
	private static Scanner connectBlindHost;
	
	public static void main(String[] args) {
		String input = "";
		while (!input.equalsIgnoreCase("No")) {
			System.out.println("	_________                                     __    ");
			System.out.println("	\\_   ___ \\  ____   ____   ____   ____   _____/  |_  ");
			System.out.println("	/    \\  \\/ /  _ \\ /    \\ /    \\_/ __ \\_/ ___\\   __\\ ");
			System.out.println("	\\     \\___(  <_> )   |  \\   |  \\  ___/\\  \\___|  |   ");
			System.out.println("	 \\______  /\\____/|___|  /___|  /\\___  >\\___  >__|   ");
			System.out.println("	        \\/            \\/     \\/     \\/     \\/       ");
			System.out.println("			__________.__  .__            .___");
			System.out.println("			\\______   \\  | |__| ____    __| _/");
			System.out.println("			 |    |  _/  | |  |/    \\  / __ | ");
			System.out.println("			 |    |   \\  |_|  |   |  \\/ /_/ | ");
			System.out.println("			 |______  /____/__|___|  /\\____ | ");
			System.out.println("			        \\/             \\/      \\/ ");
			playGame();
			System.out.println("Play Again? Type 'Yes' or 'No'");
			input = connectBlindHost.next();
		}
	}
	
	public static void playGame() {
		ConnectBlindModel model = new ConnectBlindModel();
		ConnectBlindController controller = new ConnectBlindController(model);
		connectBlindHost = new Scanner(System.in);
		
		boolean end = false; 
		// true for player one's turn, false for player's two turn
		boolean playerTurn = true;
		
		// prints out the board first so the player can see
		model.printEncryptedBoard();
		
		while (end == false) {
			if (playerTurn == true) {
				System.out.println("PLAYER ONE'S TURN (RED DISCS): ");
			} else {
				System.out.println("PLAYER TWO'S TURN (BLACK DISCS): ");
			}
			
			System.out.println("Enter a Column to place disk");
			String input = connectBlindHost.next();
			int column = Integer.parseInt(input) - 1;
			boolean added = false;
			if (playerTurn == true) {
				added = model.addPiece(column, "RED");
			} else {
				added = model.addPiece(column, "BLACK");
			}
			
			if (added == false) {
				System.out.println("COLUMN NUMBER NOT IN BOUNDS "
						+ "OR COLUMN IS ALREADY FULL, TRY AGAIN");
				continue;
			}
			
			String winner = controller.dictateWinner();
			// if one of the players wins
			if (winner != null) {
				model.printActualBoard();
				System.out.println(winner + " DISCS WINS!");
				end = true;
			} else {
				model.printEncryptedBoard();
				// switches the players turn
				playerTurn ^= true;
			}
		}
	}
}
