package edu.cuny.brooklyn.cisc3120.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class JavaFXCalculatorApp extends Application {
    private final static String MAIN_SCENE_FXML = "fxml_mainscene.fxml";
    private final static String APP_TITLE = "Simple JavaFX Calculator";
            
            
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane mainPane = (Pane)FXMLLoader.load(getClass().getResource(MAIN_SCENE_FXML));
        Scene mainScene = new Scene(mainPane);      
        
        primaryStage.setTitle(APP_TITLE);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }
}
