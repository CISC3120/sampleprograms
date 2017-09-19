package edu.cuny.brooklyn.cisc3120;

class Boat {
    private int length;
    
    public void setLength(int len) {
        length = len;
    }
    
    public int getLength() {
        return length;
    }
    
    public void move() {
        System.out.print("Just drift.");
    }
}
