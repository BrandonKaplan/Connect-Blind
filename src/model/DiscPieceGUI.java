package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DiscPieceGUI extends Circle {
	private String color;
	private Circle piece;
	private int rowNumber;
	private int columnNumber;
	
	/**
	 * Constructor for the DiscPieceGUI class
	 * 
	 * @param size int (size of the circle piece on the board)
	 * @param display JavaFX Color which is the color that will be displayed on the board
	 * @param colorString String actual color value for the piece
	 * @param columnNumber int the column number that the piece is in
	 * @param rowNumber int the row number that the piece is in
	 */
	public DiscPieceGUI(int size, Color display, String color, int columnNumber, int rowNumber) {
		super(size, display);
		this.color = color;
		this.rowNumber = rowNumber;
		this.columnNumber = columnNumber;
	}
	
	/**
	 * Constructor for the DiscPieceGUI class
	 * 
	 * Constructs a piece using only the size so the fill can be manually set
	 * 
	 * @param size int (size of the circle piece on the board)
	 * @param colorString String actual color value for the piece
	 * @param columnNumber int the column number that the piece is in
	 * @param rowNumber int the row number that the piece is in
	 */
	public DiscPieceGUI(int size, String color, int columnNumber, int rowNumber) {
		super(size);
		this.color = color;
		this.rowNumber = rowNumber;
		this.columnNumber = columnNumber;
	}

	/**
	 * Gets the column number of the piece
	 * 
	 * @return int that represents the color number 
	 */
	public int getColumn() {
		return columnNumber;
	}
	
	/**
	 * Gets the row number that the piece is at 
	 * 
	 * Gets the row number the piece is at where this number 
	 * is zero indexed (zero starting at the top and higher numbers
	 * at the bottom of the board
	 * 
	 * @return int the row number
	 */
	public int getRow() {
		return rowNumber;
	}

	/**
	 * Gets the display circle to be displayed
	 * 
	 * @return
	 */
	public Circle getDisplay() {
		return piece;
	}
	
	/**
	 * Gets the first letter of the color of the circle
	 * 
	 * @return
	 */
	public String getColorLetter() {
		return color.substring(0, 1);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getColor() {
		return color;
	}
}
