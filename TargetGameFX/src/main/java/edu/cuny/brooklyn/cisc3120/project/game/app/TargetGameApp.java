package edu.cuny.brooklyn.cisc3120.project.game.app;

import edu.cuny.brooklyn.cisc3120.project.game.TargetGame;
import javafx.application.Application;
import javafx.stage.Stage;

public class TargetGameApp extends Application
{
    public static void main( String[] args )
    {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {

        TargetGame game = new TargetGame(primaryStage);
        game.play();
    }
}
