/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sequence;

import element.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Tomasz
 */
public class Container implements Serializable  {
    private List<SequenceItem> organisms;
    
    public Container() {
         organisms = new ArrayList<SequenceItem>();
    }
    
    public void add(SequenceItem organism) {
        organisms.add(organism);
    }
    
    public void delete(SequenceItem organism){
        organisms.set(organisms.indexOf(organism), null);
    }
    
    public void removeNulls() {
        for (int i = 0; i < organisms.size(); i++) {
            if (organisms.get(i) == null) {
                organisms.remove(i);
                i--;
            }
        }
    }
    
    public int getSize() {
        return organisms.size();
    }
    
    public SequenceItem getOrganism(SequenceItem organism) {
        for (SequenceItem o : organisms) {
            if (organism.equals(o)) return o;
        }
        return null;
    }
    
    public SequenceItem getElement(int i) {
        return organisms.get(i);
    }
    
    public Agent getAgent() {
        for (SequenceItem o : organisms) {
            if (o instanceof Agent) return ((Agent)o);
        }
        return null;
    }
    
    public Gold getGold() {
        for (SequenceItem o : organisms) {
            if (o instanceof Gold) return ((Gold)o);
        }
        return null;
    }
    
    public void clear() {
        organisms.clear();
    }
    
    public List<SequenceItem> getListElement() {
        return organisms;
    }
}
