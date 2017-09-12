package cisc3120.java;

import java.util.Random;

public class App 
{
    public static void main( String[] args )
    {
    	// TODO: uncomment each commented block (separated by blank lines) and observe compilation results.
    	//       To observe, click the "Problems" tab in Eclipse or compile it in command line. Be
    	//       cautious about what Java compiler (javac) tells you and what Eclipse (an IDE) tells
    	//       you, and try to differentiate them.
    	
//    	while (true);
    	
//    	while (false);
    	
//    	System.out.println("Hello, World!");
    	
//    	while (true);
//    	System.out.println("Hello,  World!");
    	
//    	if (true) {
//    		System.out.println("Hello,  World!");
//    	}
    	
//    	if (false) {
//    		System.out.println("Hello, World!");
//    	}
    	
//    	while (1);
    	
//    	if (0);
    	
//    	Integer n1 = new Integer(3120);
//    	Integer n2 = new Integer(3120);
//    	System.out.println(n1 == n2);
//    	System.out.println(n1 != n2);
//    	System.out.println(n1.equals(n2));
    	
//    	String s1 = new String("CISC 3120");
//    	String s2 = new String("CISC 3120");
//    	System.out.println(s1 == s2);
//    	System.out.println(s1.equals(s2));
    	
//    	String s1 = "CISC 3120";
//    	String s2 = "CISC 3120";
//    	System.out.println(s1 == s2);
//    	System.out.println(s1.equals(s2));
//    	
//    	String s1 = "CISC 3120"; // string literals allocated in run time constant pool
//    	String s2 = s1;
//    	System.out.println(s1 == s2);
//    	System.out.println(s1.equals(s2));
//    	
//    	Random rand = new Random();
//    	int i = rand.nextInt(100);
//    	int j = rand.nextInt(100);
//    	System.out.println("i = " + i);
//    	System.out.println("j = " + j);
//    	System.out.println("i > j is " + (i > j));
//    	System.out.println("i < j is " + (i < j));
//    	System.out.println("i >= j is " + (i >= j));
//    	System.out.println("i <= j is " + (i <= j));
//    	System.out.println("i == j is " + (i == j));
//    	System.out.println("i != j is " + (i != j));
//    	System.out.println("(i < 10) && (j < 10) is " + ((i < 10) && (j < 10)));
//    	System.out.println("(i < 10) || (j < 10) is " + ((i < 10) || (j < 10)));
    	
    	// Can you treat an integer as a boolean as C/C++ does?:
//    	int i = 10;
//    	int j = 20;
//    	System.out.println("i && j is " + (i && j));
//    	System.out.println("i || j is " + (i || j));
//    	System.out.println("!i is " + !i);   
    	
    	// && and & are different ...
//    	int i = 2;
//    	int j = 4;
//    	System.out.println("i & j is " + (i & j));
//    	System.out.println("i | j is " + (i | j));
//    	System.out.println("~i is " + ~i);     	

    	// short-circuiting
//    	boolean b = test1(0) && test2(2) && test3(2);
//    	System.out.println("The expression is " + b);   
    	
    	
//		Random rand = new Random();
//		for (int i = 0; i < 100; i++) {
//			int c = rand.nextInt(26) + 'a';
//			System.out.print((char) c + ", " + c + ": ");
//			
//			switch (c) {
//			case 'a':
//			case 'e':
//			case 'i':
//			case 'o':
//			case 'u':
//				System.out.println("vowel");
//				break;
//			case 'y':
//			case 'w':
//				System.out.println("Sometimes a vowel");
//				break;
//			default:
//				System.out.println("consonant");
//			}
//    	}   
    	
    	
//		for (int i = 0; i < 100; i++) {
//			if (i == 74)
//				break; // Out of for loop
//			if (i % 9 != 0)
//				continue; // Next iteration
//			System.out.print(i + " ");
//		}
//		System.out.println();
//		for (int i = 0; i < 100; i++) {
//			if (i == 74)
//				break; // Out of for loop
//			if (i % 9 != 0)
//				continue; // Next iteration
//			System.out.print(i + " ");
//		}
//		System.out.println();
//		int i = 0;
//		// An "infinite loop":
//		while (true) {
//			i++;
//			int j = i * 27;
//			if (j == 1269)
//				break; // Out of loop
//			if (i % 10 != 0)
//				continue; // Top of loop
//			System.out.print(i + " ");
//		}
    		
    }
    
	static boolean test1(int val) {
		System.out.println("test1(" + val + ")");
		System.out.println("result: " + (val < 1));
		return val < 1;
	}

	static boolean test2(int val) {
		System.out.println("test2(" + val + ")");
		System.out.println("result: " + (val < 2));
		return val < 2;
	}

	static boolean test3(int val) {
		System.out.println("test3(" + val + ")");
		System.out.println("result: " + (val < 3));
		return val < 3;
	}  
}
