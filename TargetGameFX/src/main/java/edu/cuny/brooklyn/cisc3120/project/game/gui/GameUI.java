package edu.cuny.brooklyn.cisc3120.project.game.gui;

import edu.cuny.brooklyn.cisc3120.project.game.GameBoard;
import edu.cuny.brooklyn.cisc3120.project.game.Shot;
import edu.cuny.brooklyn.cisc3120.project.game.Target;
import edu.cuny.brooklyn.cisc3120.project.game.TargetGame.PostShotAction;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameUI {
    final static String APP_TITLE = "CISC 3120 Fall 2017: TargetGame";
    
    private final static int PADDING = 20;
    private final static int INIT_TARGET_CANVAS_WIDTH = 400;
    private final static int INIT_TARGET_CANVAS_HEIGHT = 400;
    private final static int INIT_MAIN_SCENE_WIDTH = 600;
    private final static int INIT_MAIN_SCENE_HEIGHT = 500;
    
    private Stage primaryStage;
    private GameBoard gameBoard;
    private Canvas targetCanvas;
    private Target target;
    private EventHandler<ActionEvent> shootingActionHandler;
    private PostShotAction postShotAction;
    
    public GameUI(GameBoard board, Stage stage) {
        gameBoard = board;
        primaryStage = stage;

        HBox hboxShooting = buildKeyboardInputBox();
        
        HBox hboxMain = buildMainBox();

        VBox vboxMain = new VBox();
        vboxMain.setPadding(new Insets(PADDING));
        vboxMain.getChildren().addAll(hboxMain, hboxShooting);
        
        Scene scene = new Scene(vboxMain, INIT_MAIN_SCENE_WIDTH, INIT_MAIN_SCENE_HEIGHT);
        primaryStage.setTitle(APP_TITLE);
        primaryStage.setScene(scene);
    }
    
    public void addTargetToUI(Target target) {
        this.target = target;
        double width = targetCanvas.getWidth();
        double height = targetCanvas.getHeight();
        double cellWidth = width / gameBoard.getWidth();
        double cellHeight = height / gameBoard.getHeight();
        double xPos = cellWidth * target.getX();
        double yPos = cellHeight * target.getY();
        
        GraphicsContext gc = targetCanvas.getGraphicsContext2D();
        gc.setFill(target.getColor());
        gc.fillRect(xPos, yPos, cellWidth, cellHeight);
    }
    
    
    public void show() {
        primaryStage.show();
    }

    public void setPostShotActionHandler(PostShotAction postShotAction) {
        this.postShotAction = postShotAction;
    }
    
    private HBox buildKeyboardInputBox() {
        TextField xGuessedTextField = new TextField(Integer.toString((int)gameBoard.getWidth()/2));
        xGuessedTextField.setOnMouseClicked((MouseEvent e) -> {xGuessedTextField.selectAll();});
        // xGuessedTextField.setOnMouseClicked(e -> xGuessedTextField.selectAll());
        // xGuessedTextField.setOnMouseClicked((MouseEvent e) -> xGuessedTextField.selectAll());
        // xGuessedTextField.setOnMouseClicked(e -> {xGuessedTextField.selectAll();} );
        TextField yGuessedTextField = new TextField(Integer.toString((int)gameBoard.getHeight()/2));
        yGuessedTextField.setOnMouseClicked((MouseEvent e) -> {yGuessedTextField.selectAll();});
        Button btnShoot = new Button("Shoot!");
        postShotAction = null;
        shootingActionHandler = (ActionEvent e) -> {
            int shotX = Integer.parseInt(xGuessedTextField.getText());
            int shotY = Integer.parseInt(yGuessedTextField.getText());
            Shot shot = new Shot(shotX, shotY);
            handleShotAction(target, shot);
        };
        btnShoot.setOnAction(shootingActionHandler);
        
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(PADDING));
        hbox.setAlignment(Pos.BOTTOM_CENTER);
        hbox.getChildren().addAll(xGuessedTextField, yGuessedTextField, btnShoot);
        
        return hbox;
    }
    
    private VBox buildSideBar() {
        VBox vboxSideBar = new VBox();
        // StackPane shootingPane = buildShootingPane();
        // VBox vboxStatistics = buildStatisticsBox();
        return vboxSideBar;
    }
    
    private HBox buildMainBox() {
        targetCanvas = new Canvas(INIT_TARGET_CANVAS_WIDTH, INIT_TARGET_CANVAS_HEIGHT);
        StackPane canvasHolder = new StackPane();
        canvasHolder.setMaxWidth(Double.MAX_VALUE);
        canvasHolder.setBackground(new Background(new BackgroundFill(Color.WHITE, 
                CornerRadii.EMPTY , Insets.EMPTY)));
        canvasHolder.getChildren().add(targetCanvas);
        
        VBox vboxSideBar = buildSideBar();
        
        HBox hbox = new HBox();
        hbox.getChildren().addAll(canvasHolder, vboxSideBar);
        
        return hbox;
    }
    
    private void clearTarget() {
        double width = targetCanvas.getWidth();
        double height = targetCanvas.getHeight();
        double cellWidth = width / gameBoard.getWidth();
        double cellHeight = height / gameBoard.getHeight();
        double xPos = cellWidth * target.getX();
        double yPos = cellHeight * target.getY();
        
        GraphicsContext gc = targetCanvas.getGraphicsContext2D();
        gc.clearRect(xPos, yPos, cellWidth, cellHeight);
        
    }
    
    private void handleShotAction(Target target, Shot shot) {
        if (target.isTargetShot(shot)) {
            Alert alert = new Alert(AlertType.INFORMATION, "You shot the target!", ButtonType.CLOSE);
            alert.setTitle(APP_TITLE + ":Target Shot");
            alert.setHeaderText("Shot!");
            alert.showAndWait();
            clearTarget();
            if (postShotAction != null)  {
                postShotAction.postAction();
            }                
        } else {
            Alert alert = new Alert(AlertType.INFORMATION, "Missed!", ButtonType.CLOSE);
            alert.setTitle(APP_TITLE + ":Target Missed");
            alert.setHeaderText("You missed the target!");                
            alert.showAndWait();
        }
   }
}
