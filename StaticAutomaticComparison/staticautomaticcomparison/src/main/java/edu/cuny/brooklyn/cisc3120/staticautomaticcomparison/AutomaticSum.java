package edu.cuny.brooklyn.cisc3120.staticautomaticcomparison;

public class AutomaticSum 
{
    public static void main( String[] args )
    {
    	AutomaticSum autoSum = new AutomaticSum();
        System.out.println(autoSum.sumToNumber(5));
        System.out.println(autoSum.sumToNumber(5));
    }

    public int sumToNumber(int number) {
    	int sum = 0;
    	for (int i=0; i<number; i++) {
    		sum += i;
    	}
    	return sum;
    }
}
