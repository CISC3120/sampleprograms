package edu.cuny.brooklyn.cisc3120.project.game;

import java.io.IOException;
import java.util.ResourceBundle;

import edu.cuny.brooklyn.cisc3120.project.game.controller.GameController;
import edu.cuny.brooklyn.cisc3120.project.game.utils.I18n;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TargetGameApp extends Application
{
    public final static String FXML_MAIN_SCENE = "view/fxml_gamemainview.fxml";
    public final static String APP_TITLE_KEY = "appTitle";
    public final static String MAIN_CSS_FILE = "appmain.css";
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle(I18n.getBundleBaseName(), I18n.getDefaultLocale());
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_MAIN_SCENE)
                , ResourceBundle.getBundle(I18n.getBundleBaseName(), I18n.getDefaultLocale()));
        Parent pane = loader.load();
        
        GameController controller = loader.getController();
        controller.setStage(primaryStage);
        
        StackPane viewHolder = new StackPane();
        Scene scene = new Scene(viewHolder);
        scene.getStylesheets().add(MAIN_CSS_FILE);
        viewHolder.getChildren().add(pane);
        
        primaryStage.setTitle(bundle.getString(APP_TITLE_KEY));
        primaryStage.setScene(scene);
        primaryStage.show();
    }   
}
