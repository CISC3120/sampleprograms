package edu.cuny.brooklyn.cisc3120;

public class FruitArraySol 
{
	private String[] fruits;
	private int size;
	private int capacity;
	
	public FruitArraySol(int capacity) {
		fruits = new String[capacity];
		this.capacity = capacity;
		size = 0;
	}
	
	public boolean add(String fruitName) {
		if (size >= capacity) return false;
		
		fruits[size] = fruitName;
		size ++;
		
		return true;
	}
	
	public void delete(String fruitName) {
		int pos = 0;
		while (pos < size && !fruits[pos].equalsIgnoreCase(fruitName)) {
			pos ++;
		}
		
		if (pos >= size) return;
		System.arraycopy(fruits, pos+1, fruits, pos, size-(pos+1));
		fruits[size-1] = null;
		size --;		
	}
	
	public void printAll() {
		for (int i=0; i<size; i++) {
			System.out.println("fruits[" + i + "] = \"" + fruits[i] + "\"");
		}
	}
}
