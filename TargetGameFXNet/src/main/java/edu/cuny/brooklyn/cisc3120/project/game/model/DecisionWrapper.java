package edu.cuny.brooklyn.cisc3120.project.game.model;

public class DecisionWrapper {
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
    

    public enum UserDecision {
        SaveGame, DiscardGame, CancelPendingAction
    }
}

