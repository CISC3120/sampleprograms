package edu.cuny.brooklyn.cisc3120.AnimalGame;

public class Dove extends Animal {

	protected Dove(String name) {
		super(name);
	}

	@Override
	public void makeNoise() {
		System.out.println("Cooing");
	}
}
