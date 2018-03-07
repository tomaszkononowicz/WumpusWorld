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
public class Gold extends Element{
    public Gold(Position position, World world) {
        super(position, world);
        name = "Zloto";
    }
    
    public Gold() {
        super();
        name = "Zloto";
    }
}
