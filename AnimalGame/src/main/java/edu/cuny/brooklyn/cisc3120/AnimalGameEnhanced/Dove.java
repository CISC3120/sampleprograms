package edu.cuny.brooklyn.cisc3120.AnimalGameEnhanced;

public class Dove extends Animal implements BirdMotion{

	protected Dove(String name) {
		super(name);
	}

	@Override
	public void makeNoise() {
		System.out.println("Cooing");
	}

	public void fly(Direction direction, double speed, double distance) {
		System.out.print("I am flying to direction " + direction.toString() + " for " + distance + " feet at " + speed
				+ " miles/hour. ");
		System.out.println("Wings flapping...");
	}
}
