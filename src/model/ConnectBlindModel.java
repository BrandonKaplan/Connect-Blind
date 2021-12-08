package model;

public class ConnectBlindModel {
	private DiscPiece[][] board;
	private final int numColumns = 7;
	private final int numRows = 6;
	public ConnectBlindModel() {
		this.board = new DiscPiece[numRows][numColumns]; 
	}
	
	public DiscPiece[][] getBoard() {
		return board;
	}
	
	/**
	 * 
	 * @param column
	 * @param color
	 * @return true or false if the piece was successfully added to the
	 * board
	 */
	public boolean addPiece(int column, String color) {
		// if the user enters a column not in bounds
		if (column < 0 || column >= numColumns) {
			return false;
		}
		
		int nextAvailable = -1;
		// iterates from the top down to find the next available spot
		for (int row = numRows - 1; row >= 0; row-=1) {
			if (board[row][column] == null) {
				nextAvailable = row;
				break;
			}
		}
		
		// if the column is already full its an invalid move
		if (nextAvailable == -1) {
			return false;
		}
		
		DiscPiece newPiece = new DiscPiece(color);
		board[nextAvailable][column] = newPiece;
		return true;
	}
	
	public void printActualBoard() {
		System.out.println("_______________________");
		
		for (int row = 0; row < board.length; row++) {
			System.out.print("|");
			for (int column = 0; column < board[0].length; column++) {
				DiscPiece current = board[row][column];
				if (current == null) {
					System.out.print("( )");
				} else {
					System.out.print("(" + current.getColorLetter() + ")");
				}
			}
			System.out.print("|\n");
		}
		
		System.out.println("^---------------------^");
		System.out.println("| 1  2  3  4  5  6  7 |");
	}
	
	public void printEncryptedBoard() {
		System.out.println("_______________________");
		
		for (int row = 0; row < board.length; row++) {
			System.out.print("|");
			for (int column = 0; column < board[0].length; column++) {
				DiscPiece current = board[row][column];
				if (current == null) {
					System.out.print("( )");
				} else {
					System.out.print("(X)");
				}
			}
			System.out.print("|\n");
		}
		
		System.out.println("^---------------------^");
		System.out.println("| 1  2  3  4  5  6  7 |");
	}
}
