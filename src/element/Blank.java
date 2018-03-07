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
public class Blank extends Element{
    public Blank(Position position, World world) {
        super(position, world);
        name = "Pusto";
    }
    public Blank() {
        super();
        name = "Pusto";
    }
}
