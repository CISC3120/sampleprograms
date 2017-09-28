package edu.cuny.brooklyn.cisc3120.AnimalGameEnhanced;


// This is wrong. A class cannot extend multiple classes
//public class FlyingCat extends Cat, Dove {
//
//
//}

// This is allowed. An class can implement multiple interfaces
//public class FlyingCat implements BirdMotion, CatMotion {
//
//	public void walk(Direction direction, double speed, double distance) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void pounce(Animal prey) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void tap(Object object) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void fly(Direction direction, double speed, double distance) {
//		// TODO Auto-generated method stub
//		
//	}
//	
//}

public class FlyingCat extends Cat implements BirdMotion, CatMotion {

	public FlyingCat(String name) {
		super(name);
	}

	public void fly(Direction direction, double speed, double distance) {
		System.out.println("I got wings, I can fly, hooray ... wings flapping, legs stepping in air ...");
	}
}
