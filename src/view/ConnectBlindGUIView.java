package view;

import controller.ConnectBlindController;
import controller.ConnectBlindControllerGUI;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
		
		// sets the portions of the border panes
		window.setTop(title);
		window.setCenter(board);
		
		//displays the scene 
		mainStage.setScene(scene);
		mainStage.show();
	}

	
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
					model.printActualBoard();
					showTrueColors();
					System.out.println(winner + " DISCS WINS!");
				} else {
					model.printEncryptedBoard();
					// switches the players turn
					playerTurn ^= true;
				}
			}
	    });
		
	}

	private void showTrueColors() {
		for (Node child : board.getChildren()) {
			if (child instanceof DiscPieceGUI) {
				DiscPieceGUI piece = (DiscPieceGUI) child;
				Circle newCircle = new Circle(20, Color.BLACK);
				String color = piece.getColor();
				System.out.println(color);
				if (color.equals("BLACK")) {
//					DiscPieceGUI newpiece = new DiscPieceGUI(20, Color.BLACK, "BLACK", piece.getColumn(), piece.getRow());
//					board.add(newpiece, piece.getColumn(), piece.getRow());
					piece.setFill(Color.BLACK);
				} else if (color.equals("RED")) {
					piece.setFill(Color.RED);
//					DiscPieceGUI newpiece = new DiscPieceGUI(20, Color.RED, "RED", piece.getColumn(), piece.getRow());
//					board.add(newpiece, piece.getColumn(), piece.getRow());
				} else if (color.equals("BLUE")) {
					piece.setFill(Color.BLUE);
//					DiscPieceGUI newpiece = new DiscPieceGUI(20, Color.BLUE, "BLUE", piece.getColumn(), piece.getRow());
//					board.add(newpiece, piece.getColumn(), piece.getRow());
				}
			}
		}
		
	}
	
	
	
}
