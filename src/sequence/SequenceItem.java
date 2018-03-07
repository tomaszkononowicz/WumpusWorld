/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sequence;

import gui.Logs;
import java.io.Serializable;
import world.Position;
import world.World;

/**
 *
 * @author Tomasz
 */
abstract public class SequenceItem {
    protected String name;
    protected Position position;
    protected World world;
    
    public SequenceItem(Position position, World world) {
        this.position = position;
        this.world = world;
        this.addToContainer();
    }
    
    public SequenceItem() {
    }
    
    public String getName() {
        return name;
    }
    
    public Position getPosition() {
        return position;
    }
    
    public World getWorld() {
        return world;
    }
    
    public void setPosition(int x, int y) {
        position = new Position(x, y);
    }
    
    public void setPosition(Position position) {
        setPosition(position.getX(), position.getY());
    }
      
    public void addToContainer() {
        world.getContainer().add(this);
    }
    
    public void deleteFromContainer() {
        world.getContainer().delete(this);
    }
    
    public boolean equals(SequenceItem si) {
        boolean result = name.equalsIgnoreCase(si.name);
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        boolean result;
        try {
            SequenceItem si = (SequenceItem)obj;
            result = name.equalsIgnoreCase(si.name);
        } catch (Exception e) {
            result = false;
        }      
        return result;
    }
    
    public Position getFreeAround() {
        for (int i=-1; i<2; i++) {
            for (int j= -1; j<2; j++) {
                Position tmp = new Position(getPosition().getX() + i, getPosition().getY() + j);
                if (world.inMap(tmp)) {
                    if (world.getElements(tmp)  == null) return tmp;
                }
            }
        }
        return null;
    }
    
}
