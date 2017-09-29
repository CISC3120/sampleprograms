package edu.cuny.brooklyn.cisc3120.AnimalGameEnhanced;

// Slide 13
//public class Cat extends Feline {
//
//	public Cat(String name) {
//		super(name);
//	}
//
//	@Override
//	public void makeNoise() {
//		System.out.println("Meowing");
//	}
//
//}

// Slide 18
public class Cat extends Feline implements CatMotion {

	public Cat(String name) {
		super(name);
	}

	@Override
	public void makeNoise() {
		System.out.println("Meowing");
	}

	public void tap(Animal animal) {
		System.out.println("Tapping " + animal.getName());
	}
}
