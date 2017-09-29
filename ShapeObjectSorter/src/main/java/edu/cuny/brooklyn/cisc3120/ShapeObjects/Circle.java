package edu.cuny.brooklyn.cisc3120.ShapeObjects;

public class Circle extends Shape {
	private double radius;
	
	public Circle(String name, double radius) {
		super(name);
		this.radius = radius;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof Circle) {
			return super.equals(other) && Double.compare(this.radius, ((Circle)other).radius) == 0;
		} else {
			return false;
		}
	}
	
	@Override
	public double area() {
		return Math.PI * radius * radius;
	}
}
