package edu.cuny.brooklyn.cisc3120.ShapeObjects;

public class Circle extends Shape {
	private double radius;
	
	public Circle(String name, double radius) {
		super(name);
		this.radius = radius;
	}
	
	public double area() {
		return Math.PI * radius * radius;
	}
}
