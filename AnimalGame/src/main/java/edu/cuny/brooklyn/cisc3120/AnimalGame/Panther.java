package edu.cuny.brooklyn.cisc3120.AnimalGame;

public class Panther extends Feline {
	
	public Panther(String name) {
		super(name);
	}

	@Override
	public void makeNoise() {
		System.out.println("Roaring");
	}

}
