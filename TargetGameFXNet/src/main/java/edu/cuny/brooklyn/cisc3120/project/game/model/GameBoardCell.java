package edu.cuny.brooklyn.cisc3120.project.game.model;

public class GameBoardCell {
	private int value;
	private boolean visible;
	
	public GameBoardCell(int value, boolean visible) {
		this.value = value;
		this.visible = visible;
	}
	
	public int getValue() {
		return value;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
