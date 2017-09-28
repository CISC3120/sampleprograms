package edu.cuny.brooklyn.cisc3120.AnimalGameEnhanced;

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

public class Cat extends Feline implements CatMotion{

	public Cat(String name) {
		super(name);
	}

	@Override
	public void makeNoise() {
		System.out.println("Meowing");
	}

	public void tap(Object object) {
		System.out.println("Tapping the object.");
	}
}
