package edu.cuny.brooklyn.cisc3120.project.game.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.cuny.brooklyn.cisc3120.project.game.model.DecisionWrapper;
import edu.cuny.brooklyn.cisc3120.project.game.model.GameState;
import edu.cuny.brooklyn.cisc3120.project.game.model.Shot;
import edu.cuny.brooklyn.cisc3120.project.game.model.DecisionWrapper.UserDecision;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class GameController {
    private final static Logger LOGGER = LoggerFactory.getLogger(GameController.class);
    
    private final static String APP_TITLE = "CISC 3120 Fall 2017: TargetGame";
    
    @FXML
    private Canvas targetCanvas;

    @FXML
    private TextField xAimedAtTextField;

    @FXML
    private TextField yAimedAtTextField;

    @FXML
    private Button fireWeaponButton;
    
    private GameState gameState = new GameState();
    
    private Stage stage;
    
    public void setStage(Stage stage) {
        this.stage = stage;
        this.stage.setOnCloseRequest(event -> {
            LOGGER.debug("User clicked the X button on the stage.");
            exitGame(event);
        });
    }
    
    @FXML
    void initialize() {
        LOGGER.debug("Initializing GameController.");
        setWeaponDisable(true);
    }
    
    @FXML
    void fireTheWeapon(ActionEvent event) {
        LOGGER.debug("Weapon fired!");
        int shotX = Integer.parseInt(xAimedAtTextField.getText());
        int shotY = Integer.parseInt(yAimedAtTextField.getText());
        Shot shot = new Shot(shotX, shotY);
        processShotAction(gameState, shot);
    }
    
    @FXML
    void exitGame(ActionEvent event) {
        LOGGER.debug("calling exitGame(ActionEvent event).");
        exitGame((Event)event);
    }

    @FXML
    void newGame(ActionEvent event) {
        LOGGER.debug("started new game.");
        addTarget(gameState, targetCanvas);
        setWeaponDisable(false);
    }

    @FXML
    void openGame(ActionEvent event) {
        LOGGER.debug("openning a saved game: not implemented yet");
    }

    @FXML
    void saveTheGame(ActionEvent event) {
        LOGGER.debug("saving the game: not implemented yet");
    }
    
    private void exitGame(Event event) {
        LOGGER.debug("calling exitGame(Event event).");
        if (gameState.isGameStateChanged()) {
            UserDecision decision = NotificationHelper.askUserDecision(new DecisionWrapper(UserDecision.CancelPendingAction));
            switch (decision) {
            case CancelPendingAction:
                event.consume();
                break;
            case DiscardGame:
                Platform.exit();
                break;
            case SaveGame:
                try {
                    gameState.saveTheGame();
                    LOGGER.debug(String.format("Saved the game at %s.", gameState.getTheGameFile().getPath()));
                    Platform.exit();
                } catch (FileNotFoundException e) {
                    LOGGER.error(String.format("Cannot found the file %s while saving the game.",
                            gameState.getTheGameFile().getPath()), e);
                    NotificationHelper.showFileNotFound(gameState.getTheGameFile().getPath());
                } catch (IOException e) {
                    LOGGER.error(String.format("Cannot write to the file %s while saving the game.",
                            gameState.getTheGameFile().getPath()), e);
                    NotificationHelper.showWritingError(gameState.getTheGameFile().getPath());
                }
                break;
            default:
                throw new IllegalArgumentException(String.format("User decision's value (%s) is unexpected", decision));
            }
        } else {
            Platform.exit();
        }       
    }
    
    private void addTarget(GameState gameState, Canvas canvas) {
        gameState.setNewTarget();
        double width = canvas.getWidth();
        double height = canvas.getHeight();
        double cellWidth = width / gameState.getGameBoard().getWidth();
        double cellHeight = height / gameState.getGameBoard().getHeight();
        double xPos = cellWidth * gameState.getTarget().getX();
        double yPos = cellHeight * gameState.getTarget().getY();
        GraphicsContext gc = targetCanvas.getGraphicsContext2D();
        gc.setFill(gameState.getTarget().getColor());
        gc.fillRect(xPos, yPos, cellWidth, cellHeight);
    }
    
    private void processShotAction(GameState gameState, Shot shot) {
        if (gameState.getTarget().isTargetShot(shot)) {
            Alert alert = new Alert(AlertType.INFORMATION, "You shot the target!", ButtonType.CLOSE);
            alert.setTitle(APP_TITLE + ":Target Shot");
            alert.setHeaderText("Shot!");
            alert.showAndWait();
            clearTarget();
            addTarget(gameState, targetCanvas);
        } else {
            Alert alert = new Alert(AlertType.INFORMATION, "Missed!", ButtonType.CLOSE);
            alert.setTitle(APP_TITLE + ":Target Missed");
            alert.setHeaderText("You missed the target!");                
            alert.showAndWait();
        }
   }
    
    private void clearTarget() {
        double width = targetCanvas.getWidth();
        double height = targetCanvas.getHeight();
        double cellWidth = width / gameState.getGameBoard().getWidth();
        double cellHeight = height / gameState.getGameBoard().getHeight();
        double xPos = cellWidth * gameState.getTarget().getX();
        double yPos = cellHeight * gameState.getTarget().getY();
        
        GraphicsContext gc = targetCanvas.getGraphicsContext2D();
        gc.clearRect(xPos, yPos, cellWidth, cellHeight);
        
    }
    
    private void setWeaponDisable(boolean disabled) {
        xAimedAtTextField.setDisable(disabled);
        yAimedAtTextField.setDisable(disabled);
        fireWeaponButton.setDisable(disabled);
    }
}
