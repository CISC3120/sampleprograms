package edu.cuny.brooklyn.cisc3120.ShapeObjects;

import java.util.Comparator;

public class ShapeNameComparator implements Comparator<Shape>  {

	/*
	 * lhs: Left Hand Side
	 * rhs: Right Hand Side
	 */	
	@Override
	public int compare(Shape lhs, Shape rhs) {
		if (lhs.getName().length() > rhs.getName().length()) {
			return 1;
		} else if (lhs.getName().length() < rhs.getName().length()) {
			return -1;
		} else {
			return 0;
		}
	}

}
