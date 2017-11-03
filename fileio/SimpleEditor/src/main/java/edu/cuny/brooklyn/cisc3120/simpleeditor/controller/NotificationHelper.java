package edu.cuny.brooklyn.cisc3120.simpleeditor.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class NotificationHelper {
    public static void showFileNotFound(String path) {
        Alert alert = new Alert(AlertType.WARNING
                , "The application cannot find the file at " + path
                , ButtonType.OK);
        alert.showAndWait();
    }

    public static void showReadingError(String path) {
        Alert alert = new Alert(AlertType.WARNING
                , "The application cannot read the file at " + path
                , ButtonType.OK);
        alert.showAndWait();
    }

    public static void showWritingError(String path) {
        Alert alert = new Alert(AlertType.WARNING
                , "The application cannot write the file at " + path
                , ButtonType.OK);
        alert.showAndWait();
    }
}
