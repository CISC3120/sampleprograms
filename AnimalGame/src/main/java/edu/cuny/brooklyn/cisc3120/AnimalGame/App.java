package edu.cuny.brooklyn.cisc3120.AnimalGame;

public class App 
{
    public static void main( String[] args )
    {
        Animal ginger = new Cat("Ginger");
        ginger.makeNoise();
        
        System.out.println("My name is " + ginger.getName());
    }
}
