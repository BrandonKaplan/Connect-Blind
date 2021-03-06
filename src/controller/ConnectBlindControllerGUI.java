/**
 * @author Brandon Kaplan
 * PROJECT: Connect Blind
 * FILE: ConnectBlindGUIController.java
 * DESCRIPTION: With the MVC design model (model, view, controller), this file
 * represents the controller portion for the GUI implementation of Connect Blind.
 * With the controller, this provides the game with the game mechanics that 
 * dictate when a player wins and reports that information back to model. 

 * IMPORTANT NOTE: Must have ConnectBlindModelGUI.java and DiscPieceGUI.java 
 * to successfully run.
 */
package controller;
import model.ConnectBlindModel;
import model.ConnectBlindModelGUI;
import model.DiscPiece;


public class ConnectBlindControllerGUI {

	private ConnectBlindModelGUI model;
	
	public ConnectBlindControllerGUI(ConnectBlindModelGUI model) {
		this.model = model;
	}
	
	/**
	 * Dictates if someone (or no one) one the game
	 * 
	 * Iterates for each of the directions of possible ways to win and seeing
	 * if a player won the game (or neither 
	 * 
	 * @return
	 */
	public String dictateWinner() {
		DiscPiece[][] board = model.getBoard();
		// iterates for each piece on the board
		for (int row = 0; row < board.length; row++) {
			for (int column = 0; column < board[0].length; column++) {
				// if there is no piece there, there's no reason to check
				if (board[row][column] == null) {
					continue;
				} else {
					boolean win = checkWin(board, row, column);
					// check to see if someone won
					if (win == true) {
						return board[row][column].getColor();
					}
				}
			}
		}
		// returns null if no one won
		return null;
	}

	private boolean checkWin(DiscPiece[][] board, int row, int column) {
		String playerColor = board[row][column].getColorLetter();
		int width = board[0].length;
		int height = board.length;
		
		// iterates left to right and right to left
		if (column + 3 < width) {
			int target = column + 3;
			int totalConnected = 0;
			for (int index = column; index <= target; index ++) {
				DiscPiece current = board[row][index];
				if (current == null) {
					break;
				} else if (!current.getColorLetter().equals(playerColor)) {
					break;
				} else {
					totalConnected = totalConnected + 1;
				}
			}
			if (totalConnected == 4) {
				return true;
			}
		}
	
		// iterates top to bottom and bottom to top
		if (row - 3 >= 0) {
			int target = row - 3;
			int totalConnected = 0;
			for (int index = row; index >= target; index --) {
				DiscPiece current = board[index][column];
				if (current == null) {
					break;
				} else if (!current.getColorLetter().equals(playerColor)) {
					break;
				} else {
					totalConnected = totalConnected + 1;
				}
			}
			if (totalConnected == 4) {
				return true;
			}
		}
		
		// iterates top left to bottom right (diagonally)
		if (row + 3 < height && column + 3 < width) {
			int colTarget = column + 3;
			int totalConnected = 0;
			int jindex = row;
			
			for (int index = column; index <= colTarget; index ++) {
				DiscPiece current = board[jindex][index];
				if (current == null) {
					break;
				} else if (!current.getColorLetter().equals(playerColor)) {
					break;
				} else {
					totalConnected = totalConnected + 1;
					jindex = jindex + 1;
				}
			}
			if (totalConnected == 4) {
				return true;
			}
		}
		
		// iterates bottom left to top right (diagonally) 
		if (row - 3 >= 0 && column + 3 < width) {
			int colTarget = column + 3;
			int totalConnected = 0;
			int jindex = row;
			
			for (int index = column; index <= colTarget; index ++) {
				DiscPiece current = board[jindex][index];
				if (current == null) {
					break;
				} else if (!current.getColorLetter().equals(playerColor)) {
					break;
				} else {
					totalConnected = totalConnected + 1;
					jindex = jindex - 1;
				}
			}
			if (totalConnected == 4) {
				return true;
			}
		}
		
		// if no one wins after move
		return false;
	}
}
