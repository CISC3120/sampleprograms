package edu.cuny.brooklyn.cisc3120.staticautomaticcomparison;

public class StaticSum 
{
    public static void main( String[] args )
    {
    	StaticSum staticSum = new StaticSum();
        System.out.println(staticSum.sumToNumber(5));
        System.out.println(staticSum.sumToNumber(5));
    }

    public int sumToNumber(int number) {
    	for (int i=0; i<number; i++) {
    		StaticSum.sum += i;
    	}
    	return StaticSum.sum;
    }
    
	public static int sum = 0;    
}
