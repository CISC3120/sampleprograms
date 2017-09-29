package edu.cuny.brooklyn.cisc3120.ShapeObjects;

import java.util.ArrayList;
import java.util.Random;

public class App 
{
    public static void main(String[] args) {
        ArrayList<Shape> shapeList = makeRandomShapes(5);
        System.out.println("List of shapes before sorted on the length of names");
        for(Shape s: shapeList) {
        	System.out.println(s.getName() + ": " + s.getName().length());
        }
        shapeList.sort(new ShapeNameComparator());
        System.out.println("\nList of shapes after sorted on the length of names");
        for(Shape s: shapeList) {
        	System.out.println(s.getName() + ": " + s.getName().length());
        }
    }

    public static ArrayList<Shape> makeRandomShapes(int numOfShapes) {
        ArrayList<Shape> shapeList = new ArrayList<Shape>();

        Random rng = new Random();
        // shapes of random types
        for (int i = 0; i < numOfShapes; i++) {
            int rn = rng.nextInt(2);
            if (rn == 0) {
                double radius = rng.nextDouble() * 100;
                String name = "Circle_" + i;
                Circle circle = new Circle(name, radius);
                shapeList.add(circle);
            } else {
                double width = rng.nextDouble() * 100;
                double length = rng.nextDouble() * 100;
                String name = "Rectangle_" + i;
                Rectangle rectangle = new Rectangle(name, width, length);
                shapeList.add(rectangle);
            }
        }

        return shapeList;
    }
    
}
