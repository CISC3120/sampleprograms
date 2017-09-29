package edu.cuny.brooklyn.cisc3120.ShapeObjects;

public class Rectangle extends Shape {
	private double width;
	private double length;
	
	public Rectangle(String name, double width, double length) {
		super(name);
		this.width = width;
		this.length = length;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof Rectangle) {
			return super.equals(other) 
					&& Double.compare(this.width, ((Rectangle)other).width) == 0
					&& Double.compare(this.length, ((Rectangle)other).length) == 0;
		} else {
			return false;
		}
	}
	
	
	// TODO: implement the area() method that computers the area 
	//       of the rectangle. The method provides an implementation
	//       to the Shape class's abstract area() method. 
}
