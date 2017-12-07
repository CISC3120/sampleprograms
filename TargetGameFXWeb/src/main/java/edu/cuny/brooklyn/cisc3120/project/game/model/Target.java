package edu.cuny.brooklyn.cisc3120.project.game.model;

import javafx.scene.paint.Color;

public class Target {
    private int x;
    private int y;
    private Color color;
    
    public Target(int x, int y) {
        this.x = x;
        this.y = y;
        this.color = Color.RED;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public Color getColor() {
        return color;
    }
    
    public boolean isTargetShot(Shot shot) {
        if (x == shot.getX() && y == shot.getY()) { 
            return true;
        } else {
            return false;
        }
    }
    
    public String toString() {
        return "Point Target at (" + x + ", " + y + ")";
    }
}
