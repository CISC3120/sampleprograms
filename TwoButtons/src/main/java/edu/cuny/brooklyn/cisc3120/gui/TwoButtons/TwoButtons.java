package edu.cuny.brooklyn.cisc3120.gui.TwoButtons;

import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class TwoButtons extends Application
{
	private final int WINDOW_WIDTH = 400;
	private final int WINDOW_HEIGHT = 200;
	private final int BUTTON_SPACING = 20;
	private final int PADDING_TOP = 20;
	private final int PADDING_RIGHT = 20;
	private final int PADDING_BOTTOM = 20;
	private final int PADDING_LEFT = 20;
	private final Insets HBOX_PADDING = new Insets(PADDING_TOP, PADDING_RIGHT, PADDING_BOTTOM, PADDING_LEFT);
	private final String[] MESSAGES = {
			"Hello, World!",
			"What a wonderful day!",
			"Happy Holidays!",
			"Are you driven?",
			"I am bored, let's play!"
	};
	
	
	private Random rng;
	private Label lblText;	
	
    public static void main( String[] args ) {
        launch(args);
    }
    
    @Override
    public void init() {
		rng = new Random();    	
    }

	@Override
	public void start(Stage primaryStage) throws Exception {	
		lblText = new Label();
		lblText.setText("Hello, World!");
		// On Mac: use
		//     lblText.setFont(Font.font("Zapfino", FontWeight.NORMAL, 60.));
		// On Windows: use
		//     lblText.setFont(Font.font("Kunstler Script", FontWeight.NORMAL, 60.));		
		lblText.setFont(Font.font("Kunstler Script", FontWeight.NORMAL, 60.));
		
		Button btnSetText = new Button();
		btnSetText.setText("Set Random Text");
		btnSetText.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				lblText.setText(getRandomMessage());			
			}} );
		
		Button btnSetColor = new Button();
		btnSetColor.setText("Set Random Color");
		btnSetColor.setOnAction(new RandomColorEventHandler());

			
		HBox hboxButtons = new HBox();
		hboxButtons.getChildren().add(btnSetText);
		hboxButtons.getChildren().add(btnSetColor);
		hboxButtons.setAlignment(Pos.CENTER);
		hboxButtons.setSpacing(BUTTON_SPACING);
		hboxButtons.setPadding(HBOX_PADDING);
		
		VBox vbox = new VBox();
		vbox.getChildren().add(hboxButtons);
		vbox.getChildren().add(lblText);
		vbox.setAlignment(Pos.TOP_CENTER);
		
		Scene scene = new Scene(vbox, WINDOW_WIDTH, WINDOW_HEIGHT);
		primaryStage.setTitle("Two Buttons");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	private class RandomColorEventHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			lblText.setTextFill(getRandomColor());
		}		
	}
	
	private String getRandomMessage() {
		return MESSAGES[rng.nextInt(MESSAGES.length)];
	}
	
	private Color getRandomColor() {
		int r = rng.nextInt(256);
		int g = rng.nextInt(256);
		int b = rng.nextInt(256);
		return Color.rgb(r, g, b);
	}
}
