package edu.cuny.brooklyn.cisc3120.project.game.controller;

import edu.cuny.brooklyn.cisc3120.project.game.model.DecisionWrapper;
import edu.cuny.brooklyn.cisc3120.project.game.model.DecisionWrapper.UserDecision;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;

public class NotificationHelper {
    public static void showFileNotFound(String path) {
        Alert alert = new Alert(AlertType.WARNING
                , "The Target Game cannot find the file at " + path
                , ButtonType.OK);
        alert.showAndWait();
    }

    public static void showReadingError(String path) {
        Alert alert = new Alert(AlertType.WARNING
                , "The Target Game cannot read the file at " + path
                , ButtonType.OK);
        alert.showAndWait();
    }

    public static void showWritingError(String path) {
        Alert alert = new Alert(AlertType.WARNING
                , "The Target Game cannot write the file at " + path
                , ButtonType.OK);
        alert.showAndWait();
    }    
    
    public static UserDecision askUserDecision(DecisionWrapper decision) {
        ButtonType saveButton = new ButtonType("Save", ButtonData.YES);
        ButtonType dontSaveButton = new ButtonType("Don't Save", ButtonData.NO);
        Alert alert = new Alert(AlertType.WARNING,
                "Want to save your content to a file?" + "\n\n"
                        + "If you click \"Don't save\", your change will be lost.",
                saveButton, dontSaveButton, ButtonType.CANCEL);
        alert.showAndWait().ifPresent((response) -> {
            if (response.getButtonData() == ButtonData.YES) {
                decision.setValue(UserDecision.SaveGame);
            } else if (response.getButtonData() == ButtonData.NO) {
                decision.setValue(UserDecision.DiscardGame);
            } else {
                decision.setValue(UserDecision.CancelPendingAction);
            }
        });
        
        return decision.getValue();
    }
}
