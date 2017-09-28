package edu.cuny.brooklyn.cisc3120.AnimalGame;

public class Whale extends Animal {

	protected Whale(String name) {
		super(name);
	}

	@Override
	public void makeNoise() {
		System.out.println("Whistling");
	}

}
