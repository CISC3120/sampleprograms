package edu.cuny.brooklyn.cisc3120.simpleeditor;

import edu.cuny.brooklyn.cisc3120.simpleeditor.controller.MainSceneController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SimpleEditorApp extends Application
{
    private final static String FXML_MAIN_SCENE = "view/fxml_mainscene.fxml";
    private final static String APP_TITLE = "Simple Editor";
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_MAIN_SCENE));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        MainSceneController controller = loader.getController();
        controller.setStage(primaryStage);
        
        primaryStage.setTitle(APP_TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
