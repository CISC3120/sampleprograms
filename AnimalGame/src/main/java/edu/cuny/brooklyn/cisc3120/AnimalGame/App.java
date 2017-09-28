package edu.cuny.brooklyn.cisc3120.AnimalGame;

public class App 
{
    public static void main( String[] args )
    {
        Animal ginger = new Cat("Ginger");
        ginger.makeNoise();       
        System.out.println("My name is " + ginger.getName() + ". I am a " + ginger.getClass().getName());
        
        Animal brave = new Panther("Brave");
        brave.makeNoise();       
        System.out.println("My name is " + brave.getName()+ ". I am a " + brave.getClass().getName());         
        

        Animal feather = new Dove("Feather");
        feather.makeNoise();       
        System.out.println("My name is " + feather.getName()+ ". I am a " + feather.getClass().getName()); 
        
        Animal jumbo = new Whale("Jumbo");
        jumbo.makeNoise();       
        System.out.println("My name is " + jumbo.getName()+ ". I am a " + jumbo.getClass().getName());         
    }
}
