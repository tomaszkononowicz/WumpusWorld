/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package element;

import sequence.SequenceItem;
import world.Position;
import world.World;

/**
 *
 * @author Tomasz
 */
public class Wumpus extends Element{
    public Wumpus(Position position, World world) {
        super(position, world);
        name = "Smok";     
    }
    
    public Wumpus() {
        super();
        name = "Smok";     
    }
   

}
