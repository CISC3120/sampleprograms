package edu.cuny.brooklyn.cisc3120.project.game;

import edu.cuny.brooklyn.cisc3120.project.game.controller.GameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TargetGameApp extends Application
{
    private final static String FXML_MAIN_SCENE = "view/fxml_gamemainview.fxml";
    private final static String APP_TITLE = "Target Game";
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_MAIN_SCENE));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        GameController controller = loader.getController();
        controller.setStage(primaryStage);
        
        primaryStage.setTitle(APP_TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
