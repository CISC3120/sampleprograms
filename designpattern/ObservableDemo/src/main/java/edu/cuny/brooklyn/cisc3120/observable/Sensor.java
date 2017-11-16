package edu.cuny.brooklyn.cisc3120.observable;

import java.util.Observable;

/* 
 * This example is copied from: 
 * https://www.ibm.com/developerworks/java/tutorials/j-patterns/j-patterns.html 
 * and revised to use two threads. 
 * */

public class Sensor extends Observable {
    private int temp = 68;

    void takeReading() {
        double d;
        d = Math.random();
        
        if (d > 0.75) {
            temp++;
            setChanged();
        } else if (d < 0.25) {
            temp--;
            setChanged();
        }
    }

    public int getReading() {
        return temp;
    }
}

