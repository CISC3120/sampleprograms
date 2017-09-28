package edu.cuny.brooklyn.cisc3120.AnimalGame;

public class Cat extends Feline {

	public Cat(String name) {
		super(name);
	}

	@Override
	public void makeNoise() {
		System.out.println("Meowing");
	}

}
