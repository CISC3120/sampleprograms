package edu.cuny.brooklyn.cisc3120.observable;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;



/* 
 * This example is copied from: 
 * https://www.ibm.com/developerworks/java/tutorials/j-patterns/j-patterns.html 
 * and revised to use two threads. 
 * */

public class DisplayApp implements Observer {
    
    public void update(Observable o, Object arg) {
        System.out.println("DisplayApp: update: New Temp: " + ((Sensor) o).getReading());
    }

    public static void main(String[] args) {
        Sensor sensor = new Sensor();
        DisplayApp display = new DisplayApp();
        // register observer with observable class
        sensor.addObserver(display);
        
        // Simulate measuring temp over time
        TimerTask sensorReader = new TimerTask () {
            @Override
            public void run() {
                sensor.takeReading(); 
                sensor.notifyObservers();
            }
        };
        
        Timer timer = new Timer("Temperature Sensor #1");
        timer.schedule(sensorReader, 0, 5000);
        
        System.out.println("Simulating doing some work by asking user to enter message repeatedly.");
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter message: ");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println("Entered " + line);
                if (line.equals("Bye bye.")) {
                    break;
                }
                System.out.println("Enter message: ");
            }
            timer.cancel(); // what if you delete this line?
        }
        System.out.println("App terminated");
    }
}