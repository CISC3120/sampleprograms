package edu.cuny.brooklyn.cisc3120.gui.javafxstyles;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class JavaFXStylesDemoApp extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button btnShowAlert = new Button("Show Confirmation Alert");
        btnShowAlert.getStyleClass().add("confirmation-button");
        btnShowAlert.setOnAction((ActionEvent e) -> {
            Alert alert = new Alert(AlertType.CONFIRMATION, "", ButtonType.OK);
            alert.getDialogPane().getStylesheets().add("stylesheet.css");
            alert.showAndWait();
        });
        
        Button btnShowError = new Button("Show Error Alert");

        Text textSceneTitle = new Text("JavaFX CSS Style Demo");
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        grid.add(textSceneTitle, 0, 0, 4, 1);
        grid.add(btnShowAlert, 0, 2, 8, 1);
        grid.getChildren().addAll(btnShowError);
        GridPane.setConstraints(btnShowError, 0, 4, 8, 1);
        Scene scene = new Scene(grid, 800, 600);
        scene.getStylesheets().add("stylesheet.css");
        //btnShowError.getStyleClass().clear();
        //btnShowError.setId("blue-button");
        btnShowError.setId("special-button");


        
        primaryStage.setScene(scene);
        primaryStage.show();
    }    
    
    public static void main( String[] args )
    {
        launch(args);
    }
}
