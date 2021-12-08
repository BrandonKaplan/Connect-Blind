package view;

import controller.ConnectBlindController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.ConnectBlindModel;

public class ConnectBlindGUIView extends Application { 
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage mainStage)  {
		
		// initialize all the elements we use for the gui
		ConnectBlindModel model = new ConnectBlindModel();
		ConnectBlindController controller= new ConnectBlindController(model);
		BorderPane window = new BorderPane();
		//top portion of borderpane
		mainStage.setTitle("Mastermind");
		//sets background to tan
		window.setStyle("-fx-background-color: #ffe7c2;");
		
		Scene scene = new Scene (window, 400, 600); //400 wide, 600 tall
		//displays the scene 
		mainStage.setScene(scene);
		mainStage.show();
	}
	
	
}
