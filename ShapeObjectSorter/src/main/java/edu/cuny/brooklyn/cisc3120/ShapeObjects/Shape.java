package edu.cuny.brooklyn.cisc3120.ShapeObjects;

public abstract class Shape {
	protected String name;
	
	public Shape(String name) {
		this.name = name;
	}
	
	public abstract double area();
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof Shape) {
			// return this.name == null ? other.name == null : this.name.equals(other.name);
			// or equivalently, more easily to read:
			if (this.name == null) {
				return ((Shape) other).name == null;
			} else {
				return this.name.equals(((Shape) other).name);
			}
		} else {
			return false;
		}
	}

	public String getName() {
		return name;
	}
}
