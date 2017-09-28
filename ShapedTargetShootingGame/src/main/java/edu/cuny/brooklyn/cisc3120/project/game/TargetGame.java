package edu.cuny.brooklyn.cisc3120.project.game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.cuny.brooklyn.cisc3120.project.game.Target.TargetShape;

public class TargetGame {
	private static Logger logger = LoggerFactory.getLogger(TargetGame.class);
	
	private final int GAME_TARGET_AREA_WIDTH = 80;
	private final int GAME_TARGET_AREA_HEIGHT = 25;

	private GameBoard gameBoard;      // having its own dimension: cells
	private GameDisplay gameDisplay;  // having its own dimension: cells to characters
	private Random rng;
	private Scanner in;
	private ArrayList<TargetShape> targetList; // TODO
	
	public TargetGame() {
		gameBoard = new GameBoard(GAME_TARGET_AREA_HEIGHT, GAME_TARGET_AREA_WIDTH);
		gameDisplay = new GameDisplay(gameBoard);
		rng = new Random();
		in = new Scanner(System.in);
		in.useDelimiter("(\\p{javaWhitespace}|,)+");
	}
	
	public void play() {
		boolean won = false;
		setTarget();
		projectTarget(); // TODO: project targets to game board
		gameBoard.plotBorder();
		gameBoard.writeText(0, GAME_TARGET_AREA_HEIGHT-1, "Enter your target position (x, y):");
		while(!won) {
			gameDisplay.draw();

			int xGuess = in.nextInt();
			int yGuess = in.nextInt();
			logger.debug("Player guessed x = " + xGuess + ", y =" + yGuess + ".");
			if (targetHit(xGuess, yGuess)) {
				gameBoard.plotBorder();
				gameBoard.writeText(0, GAME_TARGET_AREA_HEIGHT-1, "You won. Game over.");
				won = true;
			} else {
				gameBoard.plotBorder();
				gameBoard.writeText(0, GAME_TARGET_AREA_HEIGHT-1,"Try again. Enter your target position (x, y): ");
			}
			gameDisplay.draw();
		}
	}
	
	private void projectTarget() {
		// TODO: project the lists of target to game board, only for display
	}

	private void setTarget() {
		int x = rng.nextInt(GAME_TARGET_AREA_WIDTH);
		int y = rng.nextInt(GAME_TARGET_AREA_HEIGHT);
		gameBoard.setCell(x, y, 'X');
		logger.debug("Target: " + x + "," + y);
	}
	
	private boolean targetHit(int xGuess, int yGuess) {
		// Hit any target? if hit, remove it. 
		return false;
	}
	
}
