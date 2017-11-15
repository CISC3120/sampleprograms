package edu.cuny.brooklyn.cisc3120.project.game.model;

import java.util.LinkedList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GameStatistics {
    private int numOfTargetsShot;
    private int numOfShotsFired;
    private int numOfTargetsMade;
    private double accuracy;
    
    public GameStatistics() {
        numOfTargetsShot = 0;
        numOfShotsFired = 0;
        numOfTargetsMade = 0;
        accuracy = 0;
    }

    public int getNumOfTargetsShot() {
        return numOfTargetsShot;
    }

    public void setNumOfTargetsShot(int numOfTargetsShot) {
        this.numOfTargetsShot = numOfTargetsShot;
    }

    public int getNumOfShotsFired() {
        return numOfShotsFired;
    }

    public void setNumOfShotsFired(int numOfShotsFired) {
        this.numOfShotsFired = numOfShotsFired;
    }

    public int getNumOfTargetsMade() {
        return numOfTargetsMade;
    }

    public void setNumOfTargetsMade(int numOfTargetsMade) {
        this.numOfTargetsMade = numOfTargetsMade;
    }

    public void updateAccuracy() {
        if (numOfShotsFired > 0) {
            accuracy = (double)(numOfTargetsShot) / (double)numOfShotsFired;
        } else {
            accuracy = 0.0;
        }
    }
    
    public double getAccuracy() {
        return accuracy;
    }

    public ObservableList<StatNameValue> toObservableList() {
        List<StatNameValue> listStatistics = new LinkedList<StatNameValue>();
        listStatistics.add(
                new StatNameValue("# of Targets Shot", Integer.toString(numOfTargetsShot)));
        listStatistics.add(
                new StatNameValue("# of Shots Fired", Integer.toString(numOfShotsFired)));
        listStatistics.add(
                new StatNameValue("# of Targets Generated", Integer.toString(numOfTargetsMade)));
        listStatistics.add(
                new StatNameValue("Accuracy", String.format("%5.2f%%", accuracy)));
        return FXCollections.observableList(listStatistics);
    }
    
    public class StatNameValue {
        public final static String COLUMN_NAME_TITLE = "name";
        public final static String COLUMN_VALUE_TITLE = "value";
        
        private StringProperty name;
        private StringProperty value;
        
        public StatNameValue(String name, String value) {
            setName(name);
            setValue(value);
        }
        
        public void setName(String value) {
            nameProperty().set(value);
        }

        public String getName() {
            return nameProperty().get();
        }

        public StringProperty nameProperty() {
            if (name == null)
                name = new SimpleStringProperty(this, COLUMN_NAME_TITLE);
            return name;
        }



        public void setValue(String value) {
            valueProperty().set(value);
        }

        public String getValue() {
            return valueProperty().get();
        }

        public StringProperty valueProperty() {
            if (value == null)
                value = new SimpleStringProperty(this, COLUMN_VALUE_TITLE);
            return value;
        }
    }
}
