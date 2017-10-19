package edu.cuny.brooklyn.cisc3120.gui;

import java.io.Serializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FXMLGridViewController implements Serializable {
    private static final long serialVersionUID = 8415380762906053627L;

        private int nextIndex;
        
        @FXML
        private Label sceneTitleLabel;

        @FXML
        private Label quoteTextLabel;
        
        @FXML
        private ImageView portraitHolder; 
        
        @FXML
        private Button nextQuoteButton;

        @FXML
        void initialize() {
            nextIndex = 0;
        }
        
        @FXML
        void nextQuote() {
            nextIndex = (nextIndex + 1) % ComputerScienceQuoteDataModel.getNumOfQuotes();
            quoteTextLabel.setText(ComputerScienceQuoteDataModel.getQuote(nextIndex));
            portraitHolder.setImage(new Image(ComputerScienceQuoteDataModel.getPortrait(nextIndex)));
        }
    }
