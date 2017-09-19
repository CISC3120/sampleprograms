package edu.cuny.brooklyn.cisc3120;

import java.util.ArrayList;

/*
 * Wrapper for ArrayList 
 */
public class FruitArrayListSol {
	ArrayList<String> fruits;
	
	public FruitArrayListSol() {
		fruits = new ArrayList<String>();
	}
	
	
	public void add(String fruitName) {
		fruits.add(fruitName);
	}
	
	public void delete(String fruitName) {
		fruits.remove(fruitName);
	}
	
	public void printAll() {
		for (int i=0; i<fruits.size(); i++) {
			System.out.println("fruits[" + i + "] = \"" + fruits.get(i) + "\"");
		}
	}
}
