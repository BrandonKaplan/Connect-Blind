package model;

public class DiscPiece {
	private String color;
	
	public DiscPiece() {
		this.color = null;
	}
	
	public DiscPiece(String color) {
		this.color = color;
	}
	
	public String getColorLetter() {
		return color.substring(0, 1);
	}
	
	public String getColor() {
		return color;
	}

}
