/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author Tomasz
 */
public class Position implements Serializable  {
    private int x;
    private int y;
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void offset(int range) {
        Random generator = new Random();
        int x = generator.nextInt(range*2+1) - range;
        int y = generator.nextInt(range*2+1) - range;
        this.x = this.x + x;
        this.y = this.y + y;
    }
    
    public boolean equal(Position position) {
        if (this.x == position.x
                &&this.y == position.y) return true;
        return false;
    }
    
    @Override
    public String toString() {
        return new String(x + ", " + y);
    }
}
