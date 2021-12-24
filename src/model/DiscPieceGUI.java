package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DiscPieceGUI extends Circle {
	private String color;
	private Circle piece;
	private int rowNumber;
	private int columnNumber;
	
	public DiscPieceGUI(int size, Color display, String color, int columnNumber, int rowNumber) {
		super(size, display);
		this.color = color;
		this.rowNumber = rowNumber;
		this.columnNumber = columnNumber;
	}
	
	public DiscPieceGUI(int size, String color, int columnNumber, int rowNumber) {
		super(size);
		this.color = color;
		this.rowNumber = rowNumber;
		this.columnNumber = columnNumber;
	}
	
	// not zero indexed
	public int getColumn() {
		return columnNumber;
	}
	
	public int getRow() {
		return rowNumber;
	}

	public Circle getDisplay() {
		return piece;
	}
	public String getColorLetter() {
		return color.substring(0, 1);
	}
	
	public String getColor() {
		return color;
	}
}
