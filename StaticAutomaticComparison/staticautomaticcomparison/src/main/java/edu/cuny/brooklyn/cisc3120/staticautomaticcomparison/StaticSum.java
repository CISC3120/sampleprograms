package edu.cuny.brooklyn.cisc3120.staticautomaticcomparison;

public class StaticSum 
{
    public static void main( String[] args )
    {
    	StaticSum staticSum1 = new StaticSum();
        System.out.println(staticSum1.sumToNumber(5));
        StaticSum staticSum2 = new StaticSum();
        System.out.println(staticSum2.sumToNumber(5));
    }

    public int sumToNumber(int number) {
    	for (int i=0; i<number; i++) {
    		sum += i;
    	}
    	return sum;
    }
    
	public int sum = 0;    
}
