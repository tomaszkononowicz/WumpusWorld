/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evironment;

import sequence.SequenceItem;
import world.Position;
import world.World;

/**
 *
 * @author Tomasz
 */
public class Darkness extends Environment{
    public Darkness(Position position, World world) {
        super(position, world);
        name = "Ciemnosc";
    }
        
    public Darkness() {
        super();
        name = "Ciemnosc";
    }    
}
