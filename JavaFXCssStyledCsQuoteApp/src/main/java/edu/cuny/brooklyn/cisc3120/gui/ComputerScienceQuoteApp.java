package edu.cuny.brooklyn.cisc3120.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * Style JavaFX with CSS:
 *   1. Create folder src/main/resources where we place CSS files 
 *      For this, in Eclipse, select "src/main" folder (not the package), 
 *      and we do "File -> New -> Folder". Make sure you spell "resources"
 *      correctly.
 *   2. Create CSS file under src/main/resources, e.g., appmain.css. 
 *      For this, in Eclipse, we do "File -> New -> Other -> Web -> CSS". 
 *      Make sure you create the CSS in the src/main/resources folder of
 *      the project you are working on.
 */
public class ComputerScienceQuoteApp extends Application
{
    private static final int INIT_MAIN_SCENE_WIDTH = 800;
    private static final int INIT_MAIN_SCENE_HEIGHT = 600;
    private static final String APP_TITLE = "Quotations in Computer Science";
    private static final String MAIN_CSS_FILE = "appmain.css";
    private static final String APP_ICON_IMAGE = "appmain.png";
    private static final int GRID_PANE_GRIDS_IN_ROW = 10;
    private static final int GRID_PANE_GRIDS_IN_COL = 10;
    
    private static final String[] QUOTES = {
            "In computing, the mean time to failure keeps getting shorter.",
            "Everything should be built top-down, except the first time.",
            "Computers are good at following instructions, but not at reading your mind.",
            "Computer Science is no more about computers than astronomy is about telescopes.",
            "Besides a mathematical inclination, an exceptionally good mastery of one's native tongue is the most vital asset of a competent programmer." 
    };
    private static final String[] AUTHORS = {
            "Alan Perlis",
            "Alan Perlis",
            "Donald Knuth",
            "Edsger Dijkstra",
            "Edsger Dijkstra"
    };
    private static final String[] PORTRAITS = {
            "alan_perlis.jpg",
            "alan_perlis.jpg",
            "don_knuth.jpg",
            "edsger_dijkstra.jpg",
            "edsger_dijkstra.jpg"            
    };
    private int quoteIndex;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // See effect of the root style
//        GridPane gridPane = new GridPane();
//        Scene mainScene = new Scene(gridPane, INIT_MAIN_SCENE_WIDTH, INIT_MAIN_SCENE_HEIGHT);
//        mainScene.getStylesheets().add(MAIN_CSS_FILE);
//        
//        Button nextQuoteButton = new Button("Next Quote");
//        gridPane.add(nextQuoteButton, 2, 1);
//
//        primaryStage.setTitle(APP_TITLE);
//        primaryStage.getIcons().add(new Image(APP_ICON_IMAGE));
//        primaryStage.setScene(mainScene);
//        primaryStage.show();
        
        
        
        
        // Add a button and apply style based on the button's type selector
//        GridPane gridPane = new GridPane();
//        Scene mainScene = new Scene(gridPane, INIT_MAIN_SCENE_WIDTH, INIT_MAIN_SCENE_HEIGHT);
//        mainScene.getStylesheets().add(MAIN_CSS_FILE);
//        
//        
//        Button nextQuoteButton = new Button("Next Quote");
//        gridPane.add(nextQuoteButton, 2, 1);
//        System.out.println("nextQuoteButton's type selector is " + nextQuoteButton.getTypeSelector());
//
//        primaryStage.setTitle(APP_TITLE);
//        primaryStage.getIcons().add(new Image(APP_ICON_IMAGE));
//        primaryStage.setScene(mainScene);
//        primaryStage.show();   
        
        
        
        
        // Add a button and apply style based on the button's class selector
//        GridPane gridPane = new GridPane();
//        Scene mainScene = new Scene(gridPane, INIT_MAIN_SCENE_WIDTH, INIT_MAIN_SCENE_HEIGHT);
//        mainScene.getStylesheets().add(MAIN_CSS_FILE);
//
//        Button nextQuoteButton = new Button("Next Quote");
//        nextQuoteButton.getStyleClass().add("apple-blue-button");
//        gridPane.add(nextQuoteButton, 2, 1);
//
//        primaryStage.setTitle(APP_TITLE);
//        primaryStage.getIcons().add(new Image(APP_ICON_IMAGE));
//        primaryStage.setScene(mainScene);
//        primaryStage.show();
        
        // Add a button and apply style based on the button's id selector
//        GridPane gridPane = new GridPane();
//        Scene mainScene = new Scene(gridPane, INIT_MAIN_SCENE_WIDTH, INIT_MAIN_SCENE_HEIGHT);
//        mainScene.getStylesheets().add(MAIN_CSS_FILE);
//
//        Button nextQuoteButton = new Button("Next Quote");
//        nextQuoteButton.setId("brooklyn-orange-next-quote");
//        gridPane.add(nextQuoteButton, 2, 1);
//
//        primaryStage.setTitle(APP_TITLE);
//        primaryStage.getIcons().add(new Image(APP_ICON_IMAGE));
//        primaryStage.setScene(mainScene);
//        primaryStage.show();    
        
        // A CSS-style app: however the design is not well done
        quoteIndex = 0;
        
        GridPane gridPane = new GridPane();
        Scene mainScene = new Scene(gridPane, INIT_MAIN_SCENE_WIDTH, INIT_MAIN_SCENE_HEIGHT);
        mainScene.getStylesheets().add(MAIN_CSS_FILE);
        
        // divide the pane into equal width grid of 10 in a row
        double gridWidth = INIT_MAIN_SCENE_WIDTH / GRID_PANE_GRIDS_IN_ROW;
        for (int i=0; i<GRID_PANE_GRIDS_IN_ROW; i++) {
            gridPane.getColumnConstraints().add(new ColumnConstraints(gridWidth));
        }
        
        double gridHeight = INIT_MAIN_SCENE_HEIGHT / GRID_PANE_GRIDS_IN_COL;
        for (int i=0; i<GRID_PANE_GRIDS_IN_COL; i++) {
            gridPane.getRowConstraints().add(new RowConstraints(gridHeight)); 
        }

        // add a Text as the title of the scene
        Text mainSceneTitle = new Text("Quotations in Computer Science");
        mainSceneTitle.getStyleClass().add("scene-title");
        gridPane.add(mainSceneTitle, 1, 1, 8, 1);
        

        // add an image to show portrait of the person whose quote is being displayed
        StackPane imageHolder = new StackPane();
        ImageView portrait = new ImageView(new Image(PORTRAITS[quoteIndex]));
        imageHolder.getChildren().add(portrait);
        imageHolder.setAlignment(Pos.TOP_LEFT);
        gridPane.add(imageHolder, 0, 3, 3, 3);
        
        StackPane quoteTextHolder = new StackPane();
        quoteTextHolder.setId("cs-quote-text-holder");
        Text quoteText = new Text(QUOTES[quoteIndex] + "     --" + AUTHORS[quoteIndex]);
        quoteText.setWrappingWidth(gridWidth*6);
        quoteText.setTextAlignment(TextAlignment.JUSTIFY);
        quoteTextHolder.getChildren().add(quoteText);
        quoteTextHolder.setAlignment(Pos.TOP_LEFT);
        gridPane.add(quoteTextHolder,  3, 2, 6, 6);

        Button nextQuoteButton = new Button("Next Quote");
        nextQuoteButton.setId("brooklyn-orange-next-quote");
        gridPane.add(nextQuoteButton, 6, 8, 4, 1);
        
        nextQuoteButton.setOnAction((ActionEvent e) -> {
            quoteIndex = (quoteIndex + 1) % QUOTES.length;
            quoteText.setText(QUOTES[quoteIndex] + "     --" + AUTHORS[quoteIndex]);
            portrait.setImage(new Image(PORTRAITS[quoteIndex]));
        });

        primaryStage.setTitle(APP_TITLE);
        primaryStage.getIcons().add(new Image(APP_ICON_IMAGE));
        primaryStage.setScene(mainScene);
        primaryStage.show();       
    }
    
    public static void main( String[] args )
    {
        launch(args);
    }
}
