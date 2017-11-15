package edu.cuny.brooklyn.cisc3120.project.game.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.cuny.brooklyn.cisc3120.project.game.TargetGameApp;
import edu.cuny.brooklyn.cisc3120.project.game.model.DecisionWrapper;
import edu.cuny.brooklyn.cisc3120.project.game.model.TargetGame;
import edu.cuny.brooklyn.cisc3120.project.game.utils.I18n;
import edu.cuny.brooklyn.cisc3120.project.game.model.Shot;
import edu.cuny.brooklyn.cisc3120.project.game.model.DecisionWrapper.UserDecision;
import edu.cuny.brooklyn.cisc3120.project.game.model.GameStatistics.StatNameValue;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

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

    @FXML
    private ComboBox<Locale> lcComboBox;
    
    @FXML
    private TableView<StatNameValue> gameStatTableView;
    
    @FXML
    private TableColumn<StatNameValue, String> tableViewStatName;
    
    @FXML
    private TableColumn<StatNameValue, String> tableViewStatValue;
    
    private TargetGame targetGame = new TargetGame();
    
    private Stage stage;
    
    public void setStage(Stage stage) {
        this.stage = stage;
        this.stage.setOnCloseRequest(event -> {
            LOGGER.debug("User clicked the X button on the stage.");
            exitGame(event);
        });
    }
    
    @FXML
    void initialize() throws IOException, URISyntaxException {
        LOGGER.debug("Initializing GameController.");
        setWeaponDisable(true);
        initializeI18n();
        gameStatTableView.setVisible(false);
    }
    
    @FXML
    void fireTheWeapon(ActionEvent event) {
        LOGGER.debug("Weapon fired!");
        int shotX = Integer.parseInt(xAimedAtTextField.getText());
        int shotY = Integer.parseInt(yAimedAtTextField.getText());
        Shot shot = new Shot(shotX, shotY);
        processShotAction(targetGame, shot);
    }
    
    @FXML
    void exitGame(ActionEvent event) {
        LOGGER.debug("calling exitGame(ActionEvent event).");
        exitGame((Event)event);
    }

    @FXML
    void newGame(ActionEvent event) {
        LOGGER.debug("started new game.");
        addTarget(targetGame, targetCanvas);
        setWeaponDisable(false);
        gameStatTableView.setVisible(true);
        gameStatTableView.setItems(targetGame.getGameStatistics().toObservableList());
        tableViewStatName.setCellValueFactory(new PropertyValueFactory<StatNameValue, String>(StatNameValue.COLUMN_NAME_TITLE));
        tableViewStatValue.setCellValueFactory(new PropertyValueFactory<StatNameValue, String>(StatNameValue.COLUMN_VALUE_TITLE));
        gameStatTableView.getColumns().set(0,  tableViewStatName);
        gameStatTableView.getColumns().set(1,  tableViewStatValue);
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
        if (targetGame.isGameStateChanged()) {
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
                    targetGame.saveTheGame();
                    LOGGER.debug(String.format("Saved the game at %s.", targetGame.getTheGameFile().getPath()));
                    Platform.exit();
                } catch (FileNotFoundException e) {
                    LOGGER.error(String.format("Cannot found the file %s while saving the game.",
                            targetGame.getTheGameFile().getPath()), e);
                    NotificationHelper.showFileNotFound(targetGame.getTheGameFile().getPath());
                } catch (IOException e) {
                    LOGGER.error(String.format("Cannot write to the file %s while saving the game.",
                            targetGame.getTheGameFile().getPath()), e);
                    NotificationHelper.showWritingError(targetGame.getTheGameFile().getPath());
                }
                break;
            default:
                throw new IllegalArgumentException(String.format("User decision's value (%s) is unexpected", decision));
            }
        } else {
            Platform.exit();
        }       
    }
    
    private void addTarget(TargetGame game, Canvas canvas) {
        game.setNewTarget();
        double width = canvas.getWidth();
        double height = canvas.getHeight();
        double cellWidth = width / game.getGameBoard().getWidth();
        double cellHeight = height / game.getGameBoard().getHeight();
        double xPos = cellWidth * game.getTarget().getX();
        double yPos = cellHeight * game.getTarget().getY();
        GraphicsContext gc = targetCanvas.getGraphicsContext2D();
        gc.setFill(game.getTarget().getColor());
        gc.fillRect(xPos, yPos, cellWidth, cellHeight);
    }
    
    private void processShotAction(TargetGame gameState, Shot shot) {
        if (gameState.getTarget().isTargetShot(shot)) {
            Alert alert = new Alert(AlertType.INFORMATION
                    , I18n.getBundle().getString("uShotTarget"), ButtonType.CLOSE);
            alert.setTitle(APP_TITLE + ":" + I18n.getBundle().getString("targetShot"));
            alert.setHeaderText(I18n.getBundle().getString("greatShot"));
            alert.showAndWait();
            clearTarget();
            addTarget(gameState, targetCanvas);
        } else {
            Alert alert = new Alert(AlertType.INFORMATION
                    , I18n.getBundle().getString("uMissedTarget"), ButtonType.CLOSE);
            alert.setTitle(APP_TITLE + ":" + I18n.getBundle().getString("targetMissed"));
            alert.setHeaderText(I18n.getBundle().getString("lousyShooter"));                
            alert.showAndWait();
        }
   }
    
    private void clearTarget() {
        double width = targetCanvas.getWidth();
        double height = targetCanvas.getHeight();
        double cellWidth = width / targetGame.getGameBoard().getWidth();
        double cellHeight = height / targetGame.getGameBoard().getHeight();
        double xPos = cellWidth * targetGame.getTarget().getX();
        double yPos = cellHeight * targetGame.getTarget().getY();
        
        GraphicsContext gc = targetCanvas.getGraphicsContext2D();
        gc.clearRect(xPos, yPos, cellWidth, cellHeight);
        
    }
    
    private void setWeaponDisable(boolean disabled) {
        xAimedAtTextField.setDisable(disabled);
        yAimedAtTextField.setDisable(disabled);
        fireWeaponButton.setDisable(disabled);
    }
    
    private void initializeI18n() throws IOException, URISyntaxException {
        List<Locale> lcList = I18n.getAvailableLocale();
        lcComboBox.getItems().addAll(lcList);
        Callback<ListView<Locale>, ListCell<Locale>> lcCellFactory = 
                new Callback<ListView<Locale>, ListCell<Locale>>() {

            @Override
            public ListCell<Locale> call(ListView<Locale> lv) {
                return new ListCell<Locale>() {
                    @Override
                    protected void updateItem(Locale lc, boolean empty) {
                        super.updateItem(lc, empty);
                        if (lc == null || empty) {
                            setText("");
                        } else {
                            setText(I18n.getDisplayLC(lc));
                        }
                    }
                };
            }
        };
        lcComboBox.setValue(I18n.getSelectedLocale());
        lcComboBox.setConverter(new StringConverter<Locale>() {

            @Override
            public Locale fromString(String arg0) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public String toString(Locale lc) {
                return I18n.getDisplayLC(lc);
            }
        });
        lcComboBox.setCellFactory(lcCellFactory);
        lcComboBox.valueProperty().addListener(
                (observedLocale, oldLocale, newLocale) -> {
                    LOGGER.debug(String.format("Change locale from %s to %s.", oldLocale, newLocale));
                    try {
                        LOGGER.debug("TODO: change language results to a new game. Need to handle it better.");
                        reLoadScene(stage, newLocale);
                    } catch (IOException e) {
                        LOGGER.error("failed to load locale specific scene.", e);
                    }
        });
    }
    
    
    private void reLoadScene(Stage stage, Locale locale) throws IOException {
        I18n.setSelectedLocale(locale);
        I18n.setBundle(ResourceBundle.getBundle(I18n.getBundleBaseName(), locale));
        FXMLLoader loader = new FXMLLoader(TargetGameApp.class.getResource(TargetGameApp.FXML_MAIN_SCENE)
                , I18n.getBundle());
        Parent pane = loader.load();
        
        StackPane viewHolder = (StackPane)stage.getScene().getRoot();

        viewHolder.getChildren().clear();
        viewHolder.getChildren().add(pane);
        
        GameController controller = loader.getController();
        controller.setStage(stage);
        stage.setTitle(I18n.getBundle().getString(TargetGameApp.APP_TITLE_KEY));
        
        LOGGER.debug(targetGame.getTarget() == null? "No target set yet.":targetGame.getTarget().toString());
    } 
}
