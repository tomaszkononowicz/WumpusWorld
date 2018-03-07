/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package element;

import evironment.Environment;
import sequence.SequenceItem;
import world.Position;
import world.World;

/**
 *
 * @author Tomasz
 */
public class Hole extends Element{

    public Hole(Position position, World world) {
        super(position, world);
        name = "Dziura";
    }
    
    public Hole() {
        super();
        name= "Dziura";
    }

    
}
