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
public abstract class Element extends SequenceItem{
    public Element(Position position, World world) {
        super(position, world);
    }
    
    public Element() {
        super();
    }
}
