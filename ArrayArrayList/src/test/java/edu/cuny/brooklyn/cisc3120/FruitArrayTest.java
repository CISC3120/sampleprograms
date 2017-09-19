package edu.cuny.brooklyn.cisc3120;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
// import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

/**
 * Unit test for simple App.
 * API documentation: http://junit.org/junit4/javadoc/latest/index.html
 * Example: https://github.com/junit-team/junit4/wiki/getting-started
 */

public class FruitArrayTest
{
	// TODO: 1. explain why I gave up the idea of using assertArrayEquals. It seems less code if I used it. 
	// TODO: 2. add a test class FruitArrayListTest.java, and design and implement a few tests for the 
	//          delete method in the FruitArrayList class
	
	
    /**
     * Test the delete method thoroughly
     */
	@Test
    public void testDeleteByDeleting1st() {
    	// why not this: String[] fruits = {"Apple", "Banana", "Kiwi", "Mango", "Orange"};
        String[] fruits = {new String("Apple"), new String("Banana"), new String("Kiwi"), new String("Mango"), new String("Orange")};
        FruitArray fruitArray = new FruitArray(fruits);    

        fruitArray.delete(new String("Apple"));
        String[] fruitsExpected = {new String("Banana"), new String("Kiwi"), new String("Mango"), new String("Orange")};
        
        assertEquals("The size of the FruitArray object is unexpected.", fruitArray.getSize(), fruitsExpected.length);
        assertEquals(fruitArray.getCapacity(), fruitsExpected.length + 1);
        // why not assertArrayEquals( ... )
		for (int i = 0; i < fruitsExpected.length; i++) {
			assertTrue(fruitArray.getFruitsAsArray()[i].equals(fruitsExpected[i]));
		}
    }

	@Test
    public void testDeleteByDeleting3rd() {
    	// why not this: String[] fruits = {"Apple", "Banana", "Kiwi", "Mango", "Orange"};
        String[] fruits = {new String("Apple"), new String("Banana"), new String("Kiwi"), new String("Mango"), new String("Orange")};
        FruitArray fruitArray = new FruitArray(fruits);    
        
        fruitArray.delete(new String("Kiwi"));
        String[] fruitsExpected = {new String("Apple"), new String("Banana"), new String("Mango"), new String("Orange")};

        assertEquals("The size of the FruitArray object is unexpected.", fruitArray.getSize(), fruitsExpected.length);
        assertEquals(fruitArray.getCapacity(), fruitsExpected.length + 1);
        // why not assertArrayEquals( ... )
        for (int i = 0; i < fruitsExpected.length; i++) {
			assertTrue(fruitArray.getFruitsAsArray()[i].equals(fruitsExpected[i]));
		}
    }  

	@Test
    public void testDeleteByDeletingLast() {
    	// why not this: String[] fruits = {"Apple", "Banana", "Kiwi", "Mango", "Orange"};
        String[] fruits = {new String("Apple"), new String("Banana"), new String("Kiwi"), new String("Mango"), new String("Orange")};
        FruitArray fruitArray = new FruitArray(fruits);    
        
        fruitArray.delete(new String("Orange"));
        String[] fruitsExpected = {new String("Apple"), new String("Banana"), new String("Kiwi"), new String("Mango")};
        
        assertEquals("The size of the FruitArray object is unexpected.", fruitArray.getSize(), fruitsExpected.length);
        assertEquals(fruitArray.getCapacity(), fruitsExpected.length + 1);
        // why not assertArrayEquals( ... )        
		for (int i = 0; i < fruitsExpected.length; i++) {
			assertTrue(fruitArray.getFruitsAsArray()[i].equals(fruitsExpected[i]));
		}
    } 
    
	@Test
    public void testDeleteIgnoreCaseByDeleting1st() {
    	// why not this: String[] fruits = {"Apple", "Banana", "Kiwi", "Mango", "Orange"};
        String[] fruits = {new String("Apple"), new String("Banana"), new String("Kiwi"), new String("Mango"), new String("Orange")};
        FruitArray fruitArray = new FruitArray(fruits);    
        
        fruitArray.delete(new String("apple"));
        String[] fruitsExpected = {new String("Banana"), new String("Kiwi"), new String("Mango"), new String("Orange")};

        assertEquals("The size of the FruitArray object is unexpected.", fruitArray.getSize(), fruitsExpected.length);
        assertEquals(fruitArray.getCapacity(), fruitsExpected.length + 1);
        // why not assertArrayEquals( ... )        
		for (int i = 0; i < fruitsExpected.length; i++) {
			assertTrue(fruitArray.getFruitsAsArray()[i].equals(fruitsExpected[i]));
		}
    }


	@Test
    public void testDeleteIgnoreCaseByDeletingLast() {
    	// why not this: String[] fruits = {"Apple", "Banana", "Kiwi", "Mango", "Orange"};
        String[] fruits = {new String("Apple"), new String("Banana"), new String("Kiwi"), new String("Mango"), new String("Orange")};
        FruitArray fruitArray = new FruitArray(fruits);    
        
        fruitArray.delete(new String("orangE"));
        String[] fruitsExpected = {new String("Apple"), new String("Banana"), new String("Kiwi"), new String("Mango")};

        
        assertEquals("The size of the FruitArray object is unexpected.", fruitArray.getSize(), fruitsExpected.length);
        assertEquals(fruitArray.getCapacity(), fruitsExpected.length + 1);
        // why not assertArrayEquals( ... )        
		for (int i = 0; i < fruitsExpected.length; i++) {
			assertTrue(fruitArray.getFruitsAsArray()[i].equals(fruitsExpected[i]));
		}
    }     
    
}
