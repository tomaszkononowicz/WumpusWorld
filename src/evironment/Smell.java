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
public class Smell extends Environment{
        public Smell(Position position, World world) {
        super(position, world);
        name = "Zapach";
    }
    public Smell() {
        super();
        name = "Zapach";
    } 
}
