package edu.cuny.brooklyn.cisc3120.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ComputerScienceQuoteApp extends Application
{
    private final static String APP_TITLE = "Quotations in Computer Science";
    private final static String MAIN_SCENE_FXML = "fxml_mainscene.fxml";
    @Override
    public void start(Stage primaryStage) throws IOException {
        Pane mainPane = (Pane)FXMLLoader.load(getClass().getResource(MAIN_SCENE_FXML));
        Scene mainScene = new Scene(mainPane);        
        
        primaryStage.setTitle(APP_TITLE);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }
    
    public static void main( String[] args )
    {
        launch(args);
    }
}
