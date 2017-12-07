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
        /**
         * The application starts always with the default locale (language & country). This is not ideal
         * because users may wish to keep their choice of locale  selection via the user interface 
         * from previous runs. The solution is to save the users' choice in a configuration file before
         * the app exits. When the app starts, it should check if such a configuration file exists, if 
         * the configuration file exists, the app loads the configuration file, and uses the settings
         * in the configuration file to initialize the app.
         * 
         * Perhaps, the easiest method to save a configuration file is to use Java's Properties. 
         * See https://docs.oracle.com/javase/tutorial/essential/environment/properties.html
         * for a tutorial. Also be aware that the message bundle files to support internationalization
         * are also Java Properties files. 
         */
        ResourceBundle bundle = ResourceBundle.getBundle(I18n.getBundleBaseName(), I18n.getDefaultLocale());
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_MAIN_SCENE), bundle);
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
