package edu.cuny.brooklyn.cisc3120.project.game.Weapon;

public abstract class Gun {
	public abstract void shoot();
	public abstract boolean withinShootingArea(int xGuess, int yGuess);
}
