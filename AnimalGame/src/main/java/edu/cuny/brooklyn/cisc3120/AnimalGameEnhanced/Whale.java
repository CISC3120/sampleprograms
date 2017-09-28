package edu.cuny.brooklyn.cisc3120.AnimalGameEnhanced;

public class Whale extends Animal {

	protected Whale(String name) {
		super(name);
	}

	@Override
	public void makeNoise() {
		System.out.println("Whistling");
	}

}
