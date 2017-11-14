package edu.cuny.brooklyn.cisc3120.project.game.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameState {
    private static Logger LOGGER = LoggerFactory.getLogger(GameState.class);
    
    private final static int GAME_TARGET_AREA_WIDTH = 40;
    private final static int GAME_TARGET_AREA_HEIGHT = 40;
    private final static char TARGET_INDICATOR_ON_BOARD = 'X';
    
    private boolean gameStateChanged;
    
    private File theGameFile;
    private Target target;
    private GameBoard gameBoard;
    private Random rng;

    public GameState() {
        gameStateChanged = false;
        gameBoard = new GameBoard(GAME_TARGET_AREA_HEIGHT, GAME_TARGET_AREA_WIDTH);
        rng = new Random();
    }
    
    public boolean isGameStateChanged() {
        return gameStateChanged;
    }

    public void setGameStateChanged(boolean gameStateChanged) {
        this.gameStateChanged = gameStateChanged;
    }

    public void saveTheGame() throws FileNotFoundException, IOException {
        // TODO Auto-generated method stub
        
    }

    public File getTheGameFile() {
        return theGameFile;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public Target getTarget() {
        return target;
    }
    
    public void setNewTarget() {
        target = getRandomTarget();
        addTargetToBoard(target);
    }

    private Target getRandomTarget() {
        int x = rng.nextInt(GAME_TARGET_AREA_WIDTH);
        int y = rng.nextInt(GAME_TARGET_AREA_HEIGHT);
        Target target = new Target(x, y);
        LOGGER.debug("Target: " + x + "," + y);
        return target;
    }

    private void addTargetToBoard(Target target) {
        gameBoard.setCell(target.getX(), target.getY(), TARGET_INDICATOR_ON_BOARD);
    }    
}
