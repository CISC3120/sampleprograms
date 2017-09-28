package edu.cuny.brooklyn.cisc3120.project.game.Target;

public class PointTargetShape extends TargetShape {
	public PointTargetShape() {
		int[][] targetCells = new int[1][1];
		targetCells[0][0] = 'X';
		super.setTargetCells(targetCells);
		super.setWidth(1);
		super.setHeight(1);
	}
}
