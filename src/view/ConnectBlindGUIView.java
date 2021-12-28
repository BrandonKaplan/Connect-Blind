/**
 * @author Brandon Kaplan
 * PROJECT: Connect Blind
 * FILE: ConnectBlindGUIView.java
 * DESCRIPTION: With the MVC design model (model, view, controller), this file
 * represents the view portion for the GUI implementation of Connect Blind. This 
 * is the file that would be called on from ConnectBlind.java with the argument
 * "GUI" to pull up the JavaFX version. Here the graphic interface is created that
 * the user can interact with such as setting up the board, title, and pieces. This
 * view uses the controller with reflects the changes onto the model which updates
 * this view that the player can see.
 * 
 * SIDE NOTE: Field variables width and height can be changed for board variation
 * (i.e. a 7x6 board, 6x7 board, 10x10 board, etc.)
 * 
 * IMPORTANT NOTE: Must need JavaFX in order to run 
 * IMPORTANT NOTE: Must have ConnectBlindModelGUI.java, ConnectBlindControllerGUI.java
 * and DiscPieceGUI.java to successfully run.
 */
package view;

import controller.ConnectBlindController;
import controller.ConnectBlindControllerGUI;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.ConnectBlindModel;
import model.ConnectBlindModelGUI;
import model.DiscPieceGUI;

public class ConnectBlindGUIView extends Application { 
	private static final int width = 7;
	private static final int height = 6;
	private static boolean playerTurn;
	private ConnectBlindModelGUI model;
	private ConnectBlindControllerGUI controller;
	private GridPane board;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage mainStage)  {
		// initialize all the elements we use for the gui
		model = new ConnectBlindModelGUI();
		controller = new ConnectBlindControllerGUI(model);
		BorderPane window = new BorderPane();
		playerTurn = true;

		// top portion of the application title
		mainStage.setTitle("Connect Blind");
		
		//sets background to pink
		window.setStyle("-fx-background-color: #fed4ff;");

		Scene scene = new Scene (window, 800, 600); //800 wide, 600 tall
		
		board = new GridPane();
		// loops and creates the board based off the width and height of the board
		for (int index = 0; index < width; index++ ) {
			for (int jindex = 0; jindex < height; jindex++) {
				DiscPieceGUI newPiece = new DiscPieceGUI(20, "BLUE", index, jindex);
				newPiece.setFill(Color.BLUE);
				addEvent(newPiece);
				board.add(newPiece, index, jindex);
			}
		}
		
		// creates the column numbers at the bottom
		for (int index = 0; index < width; index++ ) {
			Text number = new Text(10, 50, String.valueOf(index + 1));
			number.setFont(new Font(20));
			board.add(number, index, height);
		}
		
		// creates the blue frame
		board.setStyle("-fx-background-color: #03b7ff;");
		
		// creates the title 
		Text title = new Text(10, 50, "CONNECT BLIND");
		title.setFont(new Font(20));
		
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
		
		double x = ((primScreenBounds.getWidth() - scene.getWidth()) / 25);
		double y = ((primScreenBounds.getHeight() - scene.getHeight()) / 2);
		int xInt = (int) x;
		int yInt = (int) y;
		
		// creates the left and right
		Text side1 = new Text(10, 50, (" ".repeat(xInt)));
		side1.setFont(new Font(20));
		Text side2 = new Text(10, 50, (" ".repeat(xInt)));
		side2.setFont(new Font(20));
		
		// sets the portions of the border panes
		window.setTop(title);
//		window.setLeft(side1);
//		window.setRight(side2);
		window.setCenter(board);
		
		//displays the scene 
		mainStage.setScene(scene);
		mainStage.show();
	}

	/**
	 * Adds the mouse interaction to each disc piece
	 * 
	 * Takes one of the disc pieces that would be added to the board and 
	 * adds the event handler which controls all the gameplay, which allows
	 * players to add peices to the board changing the colors of these circles
	 * and dictacting when the player will win.
	 * 
	 * @param newPiece DiscPieceGUI the new piece to be added to the board
	 */
	private void addEvent(DiscPieceGUI newPiece) {
		newPiece.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			int added = -1;
			DiscPieceGUI neutralCircle = null;
			// adds to the pieces depending on who's turn it is
			if (playerTurn == true) {
				added = model.addPiece(newPiece.getColumn(), "RED");
				neutralCircle = new DiscPieceGUI(20, "RED", newPiece.getColumn(), newPiece.getRow());
			} else {
				added = model.addPiece(newPiece.getColumn(), "BLACK");
				neutralCircle = new DiscPieceGUI(20, "BLACK", newPiece.getColumn(), newPiece.getRow());
			}
			
			// ensures the move was a valid move
			if (added == -1) {
				System.out.println("COLUMN NUMBER NOT IN BOUNDS "
				+ "OR COLUMN IS ALREADY FULL, TRY AGAIN");
			// if the piece was successfully added, then check win conditions
			} else {
				// change the color of the circle 
				neutralCircle.setFill(Color.PURPLE);
				board.add(neutralCircle, neutralCircle.getColumn(), added);
				
				String winner = controller.dictateWinner();
				// if one of the players wins
				if (winner != null) {
					showTrueColors();
					System.out.println(winner + " DISCS WINS!");
				} else {
					// switches the players turn
					playerTurn ^= true;
				}
			}
	    });
		
	}

	/**
	 * Reveals the actual color values of each of the pieces
	 * 
	 * Iterates for all the disc pieces in the board and changes the
	 * fill value to the actual color values of the pieces so the 
	 * players can see how the winner achieved four in a row
	 */
	private void showTrueColors() {
		// goes through all the pieces on the board
		for (Node child : board.getChildren()) {
			if (child instanceof DiscPieceGUI) {
				DiscPieceGUI piece = (DiscPieceGUI) child;
				String color = piece.getColor();
				// reveals the actual color value of the piece
				if (color.equals("BLACK")) {
					piece.setFill(Color.BLACK);
				} else if (color.equals("RED")) {
					piece.setFill(Color.RED);
				} else if (color.equals("BLUE")) {
					piece.setFill(Color.BLUE);
				}
			}
		}
		
	}
	
	
	
}
