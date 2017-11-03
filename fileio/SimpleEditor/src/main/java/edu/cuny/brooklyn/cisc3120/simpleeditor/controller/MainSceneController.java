package edu.cuny.brooklyn.cisc3120.simpleeditor.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.cuny.brooklyn.cisc3120.simpleeditor.model.SimpleEditor;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainSceneController {
    private final static Logger LOGGER = LoggerFactory.getLogger(MainSceneController.class);
    
    @FXML
    private MenuBar menuBar;
    
    @FXML
    private MenuItem closeTheFileMenuItem;
    
    @FXML
    private MenuItem saveTheFileMenuItem;

    @FXML
    private HTMLEditor htmlEditor;
    
    private Stage stage;
    private SimpleEditor editor = new SimpleEditor();
    private HTMLEditorContentChangeListener contentChangeListener = null;
    
    public void setStage(Stage stage) {
        this.stage = stage;
        this.stage.setOnCloseRequest(event -> {
            LOGGER.debug("User clicked the X button on the stage.");
            exitApp(event);
        });
    }
    
    @FXML
    void initialize() {
        makeContentChangeListener();
        htmlEditor.setDisable(true);
        closeTheFileMenuItem.setDisable(true);
        saveTheFileMenuItem.setDisable(true);
        WebView webview = (WebView) htmlEditor.lookup("WebView");
        GridPane.setHgrow(webview, Priority.ALWAYS);
        GridPane.setVgrow(webview, Priority.ALWAYS);
        LOGGER.debug("Controller MainSceneController initialized.");
    }
    
    @FXML
    void newFile(ActionEvent event) {
        if (editor.isEditorContentChanged()) {
            UserDecision decision = askUserDecision();
            switch(decision) {
            case CancelPendingAction:
                break;
            case DiscardChange:
                setUIForContentNotChanged();
                createNewFile();
                resetContentChangeListener();
                htmlEditor.setDisable(false);
                LOGGER.debug(String.format("Discared content edited for the new file %s."
                        , editor.getTheFile().getPath()));
                break;
            case SaveChange:
                try {
                    editor.saveTheFile(htmlEditor.getHtmlText());
                    LOGGER.debug(String.format("Saved the file %s.", editor.getTheFile().getPath()));
                    setUIForContentNotChanged();                   
                    createNewFile();
                    resetContentChangeListener();
                    htmlEditor.setDisable(false);
                    LOGGER.debug(String.format("Created new file %s for editing.", editor.getTheFile().getPath()));                   
                } catch (FileNotFoundException e) {
                    LOGGER.error(String.format("Cannot found the file %s while saving the file."
                            , editor.getTheFile().getPath()), e);
                    NotificationHelper.showFileNotFound(editor.getTheFile().getPath());
                } catch (IOException e) {
                    LOGGER.error(String.format("Cannot write to the file %s while saving the file."
                            , editor.getTheFile().getPath()), e);
                    NotificationHelper.showWritingError(editor.getTheFile().getPath());
                }
                break;
            default:
                throw new IllegalArgumentException(String.format(
                        "User decision's value (%s) is unexpected", decision));
            }
        } else {
            createNewFile();
            if (editor.getTheFile() != null) {
                resetContentChangeListener();
                htmlEditor.setDisable(false);
                LOGGER.debug(String.format("Created new file %s for editing.", editor.getTheFile().getPath()));
            }
        }
    }

    @FXML
    void openFile(ActionEvent event) {
        try {
            if (editor.isEditorContentChanged()) {
                UserDecision decision = askUserDecision();
                switch(decision) {
                case CancelPendingAction:
                    break;
                case DiscardChange:
                    setUIForContentNotChanged();
                    openFileFromFileSystem();
                    resetContentChangeListener();
                    htmlEditor.setDisable(false);
                    LOGGER.debug(String.format("Opened file %s for editing.", editor.getTheFile().getPath()));                                       
                    break;
                case SaveChange:
                    editor.saveTheFile(htmlEditor.getHtmlText());
                    LOGGER.debug(String.format("Saved the file %s.", editor.getTheFile().getPath()));                    
                    setUIForContentNotChanged();
                    openFileFromFileSystem();
                    resetContentChangeListener();
                    htmlEditor.setDisable(false);
                    LOGGER.debug(String.format("Opened file %s for editing.", editor.getTheFile().getPath()));                                                           
                    break;
                default:
                    throw new IllegalArgumentException(String.format(
                            "User decision's value (%s) is unexpected", decision));
                }
            } else {
                openFileFromFileSystem();
                if (editor.getTheFile() != null) {
                    setUIForContentNotChanged();
                    htmlEditor.setDisable(false);
                    closeTheFileMenuItem.setDisable(false);
                    resetContentChangeListener();
                    LOGGER.debug(String.format("Opened file %s for editing.", editor.getTheFile().getPath()));                                                                               
                }
            }
        } catch (FileNotFoundException e) {
            LOGGER.error(String.format("Cannot found the file %s while opening the file."
                    , editor.getTheFile().getPath()), e);
            NotificationHelper.showFileNotFound(editor.getTheFile().getPath());
        } catch (IOException e) {
            LOGGER.error(String.format("Cannot load the file %s while opening the file."
                    , editor.getTheFile().getPath()), e);
            NotificationHelper.showWritingError(editor.getTheFile().getPath());
        }
    }

    @FXML
    void saveTheFile(ActionEvent event) {
        if (editor.isEditorContentChanged()) {
            try {
                editor.saveTheFile(htmlEditor.getHtmlText());
                setUIForContentNotChanged();
                closeTheFileMenuItem.setDisable(false);                
                resetContentChangeListener();
                LOGGER.debug(String.format("Saved the file %s.", editor.getTheFile().getPath()));
            } catch (FileNotFoundException e) {
                LOGGER.error(String.format("Cannot found the file %s while saving the file."
                        , editor.getTheFile().getPath()), e);
                NotificationHelper.showFileNotFound(editor.getTheFile().getPath());
            } catch (IOException e) {
                LOGGER.error(String.format("Cannot write to the file %s while saving the file."
                        , editor.getTheFile().getPath()), e);
                NotificationHelper.showWritingError(editor.getTheFile().getPath());
            }
        } else {
            closeTheFileMenuItem.setDisable(false);
        }
    }
    
    @FXML
    void closeTheFile(ActionEvent event) {
        if (editor.isEditorContentChanged()) {
            UserDecision decision = askUserDecision();
            switch(decision) {
            case CancelPendingAction:
                break;
            case DiscardChange:
                htmlEditor.setHtmlText("");
                setUIForContentNotChanged();
                htmlEditor.setDisable(true);
                LOGGER.debug(String.format("Closed editting pane for the file %s.", editor.getTheFile().getPath()));                
                break;
            case SaveChange:
                try {
                    editor.saveTheFile(htmlEditor.getHtmlText());
                    LOGGER.debug(String.format("Saved the file %s.", editor.getTheFile().getPath()));                    
                    setUIForContentNotChanged();
                    htmlEditor.setDisable(true);   
                    LOGGER.debug(String.format("Closed editting pane for the file %s.", editor.getTheFile().getPath()));                                    
                } catch (FileNotFoundException e) {
                    LOGGER.error(String.format("Cannot found the file %s while saving the file."
                            , editor.getTheFile().getPath()), e);
                    NotificationHelper.showFileNotFound(editor.getTheFile().getPath());
                } catch (IOException e) {
                    LOGGER.error(String.format("Cannot write to the file %s while saving the file."
                            , editor.getTheFile().getPath()), e);
                    NotificationHelper.showWritingError(editor.getTheFile().getPath());
                }
                break;
            default:
                throw new IllegalArgumentException(String.format(
                        "User decision's value (%s) is unexpected", decision));
            }
        } else {
            htmlEditor.setHtmlText("");
            setUIForContentNotChanged();
            htmlEditor.setDisable(true);
        }
    }
    
    @FXML
    void exitApp(ActionEvent event) {
        LOGGER.debug("call exitApp(ActionEvent event).");
        exitApp((Event)event);
    }
    
    
    private void createNewFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Create New HTML File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("HTML Files", "*.htm", "*.html"),
                new ExtensionFilter("All Files", "*.*"));
        File theFile = fileChooser.showSaveDialog(stage);
        if (theFile != null) {
            editor.setTheFile(theFile);
        }
    }    
    
    private void openFileFromFileSystem() throws FileNotFoundException, IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open an HTML File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("HTML Files", "*.htm", "*.html"),
                new ExtensionFilter("All Files", "*.*"));
        File theFile = fileChooser.showOpenDialog(stage);
        if (theFile != null) {
            editor.setTheFile(theFile);
            String htmlText;
            htmlText = editor.readFile();
            htmlEditor.setHtmlText(htmlText);
        }
    }
    
    private void exitApp(WindowEvent event) {
        LOGGER.debug("call exitApp(WindowEvent event).");
        exitApp((Event)event);
    }
    
    private void exitApp(Event event) {
        LOGGER.debug("call exitApp(Event event).");
        if (editor.isEditorContentChanged()) {
            UserDecision decision = askUserDecision();
            switch (decision) {
            case CancelPendingAction:
                event.consume();
                break;
            case DiscardChange:
                Platform.exit();
                break;
            case SaveChange:
                try {
                    editor.saveTheFile(htmlEditor.getHtmlText());
                    LOGGER.debug(String.format("Saved the file %s.", editor.getTheFile().getPath()));
                    Platform.exit();
                } catch (FileNotFoundException e) {
                    LOGGER.error(String.format("Cannot found the file %s while saving the file.",
                            editor.getTheFile().getPath()), e);
                    NotificationHelper.showFileNotFound(editor.getTheFile().getPath());
                } catch (IOException e) {
                    LOGGER.error(String.format("Cannot write to the file %s while saving the file.",
                            editor.getTheFile().getPath()), e);
                    NotificationHelper.showWritingError(editor.getTheFile().getPath());
                }
                break;
            default:
                throw new IllegalArgumentException(String.format("User decision's value (%s) is unexpected", decision));
            }

        } else {
            Platform.exit();
        }       
    }
    
    private UserDecision askUserDecision() {
        DecisionWrapper decision = new DecisionWrapper(UserDecision.CancelPendingAction);
        
        ButtonType saveButton = new ButtonType("Save", ButtonData.YES);
        ButtonType dontSaveButton = new ButtonType("Don't Save", ButtonData.NO);
        Alert alert = new Alert(AlertType.WARNING,
                "Want to save your content to a file?" + "\n\n"
                        + "If you click \"Don't save\", your change will be lost.",
                saveButton, dontSaveButton, ButtonType.CANCEL);
        alert.showAndWait().ifPresent((response) -> {
            if (response.getButtonData() == ButtonData.YES) {
                decision.setValue(UserDecision.SaveChange);
            } else if (response.getButtonData() == ButtonData.NO) {
                decision.setValue(UserDecision.DiscardChange);
            } else {
                decision.setValue(UserDecision.CancelPendingAction);
            }
        });
        
        return decision.getValue();
    }
    
    private class DecisionWrapper {
        private UserDecision value;

        public DecisionWrapper(UserDecision decisionValue) {
            value = decisionValue;
        }

        public UserDecision getValue() {
            return value;
        }

        public void setValue(UserDecision value) {
            this.value = value;
        }
    }
    
    private enum UserDecision {
        SaveChange,
        DiscardChange,
        CancelPendingAction
    }
    
    private void makeContentChangeListener() {
        contentChangeListener = new HTMLEditorContentChangeListener(htmlEditor);
        contentChangeListener.contentChangedProperty().addListener((observedValue, oldValue, newValue) -> {
            editor.setEditorContentChanged(newValue);
            setUIForContentChanged();
        });
    }
    
    private void setUIForContentNotChanged() {
        editor.setEditorContentChanged(false);
        closeTheFileMenuItem.setDisable(true);
        saveTheFileMenuItem.setDisable(true); 
        // call resetContentChangeListener after call this method
    }
    
    private void setUIForContentChanged() {
        editor.setEditorContentChanged(true);
        closeTheFileMenuItem.setDisable(false);
        saveTheFileMenuItem.setDisable(false);
        // don't call resetContentChangeListener after call this method
    }
    
    private void resetContentChangeListener() {
        releaseContentChangeListener();
        makeContentChangeListener();
    }
    
    private void releaseContentChangeListener() {
        contentChangeListener = null;
        editor.setEditorContentChanged(false);
    }    
    
    /**
     * Unfortunately, HTMLEditor does not have a convenient EventHandler for us
     * to determine whether the content is changed. We have to improvise. 
     * 
     * In Java a Property is a class that one may monitor its changes with
     * a ChangeListener. There are many kinds of Property classes. Below
     * we use a BooleanProperty to monitor whether the content of the
     * HTMLEditor is changed by monitoring keyboard and mouse events.
     */
    private class HTMLEditorContentChangeListener {
        private final BooleanProperty contentChangedProperty;

        private String prevContent;

        public HTMLEditorContentChangeListener(final HTMLEditor editor) {
            contentChangedProperty = new SimpleBooleanProperty();
            contentChangedProperty.set(false);
            prevContent = null;
            editor.setOnMouseClicked(event -> checkContentEdited(editor));
            /*
             *  You may wonder why we use an EventFilter instead of an EventHandler.
             *  We use EventFilter because EventFilter does not consume the event.
             *  Imaging that you replace the following by a EventHander, e.g.,
             *      editor.setOnKeyTyped(event -> checkContentEdited(editor));
             *  you will not be ableto edit the content, because the above code
             *  consumes the KEY_TYPED event (the KEY_TYPED is gone). 
             *  
             *  See https://docs.oracle.com/javase/8/javafx/events-tutorial/events.htm 
             *  for more information.
             */
            
            editor.addEventFilter(KeyEvent.KEY_TYPED, event -> checkContentEdited(editor));
        }

        public BooleanProperty contentChangedProperty() {
            return contentChangedProperty;
        }

        private void checkContentEdited(final HTMLEditor editor) {
            String currentContent = editor.getHtmlText();
            if (contentChangedProperty.get()) {
                // if already changed, do nothing, for efficiency
                return;
            } else if (prevContent != null && prevContent.length() != currentContent.length()
                    || prevContent == null & !currentContent.isEmpty()
                    || !prevContent.equals(currentContent)) {
                // it is a change
                contentChangedProperty.set(true);
            }
            prevContent = currentContent;
        }
    }
}
