/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sequence;

import action.*;
import element.*;
import evironment.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toMap;

/**
 *
 * @author Tomasz
 */
public class Sequence {
    ArrayList<ArrayList<SequenceItem>> sequenceLists;
    int counter;
    
    public Sequence() {
        sequenceLists = new ArrayList<ArrayList<SequenceItem>>();
        counter = -1;
    }
    
    
    public void nextSequenceItem() {
        counter++;
        sequenceLists.add(new ArrayList<SequenceItem>());  
    }
    
    public void addSequenceItem(SequenceItem sequenceItem) {
        sequenceLists.get(counter).add(sequenceItem);
    }
    
    public ArrayList<SequenceItem> getAntecendents(SequenceItem sequenceItem, int step) {
        ArrayList<SequenceItem> antecendents = new ArrayList<SequenceItem>();
        for (int aSiIterator = 0; aSiIterator < sequenceLists.size(); aSiIterator++) {
            ArrayList<SequenceItem> aSi = sequenceLists.get(aSiIterator);
            for (int siIterator = 0; siIterator < aSi.size(); siIterator++) {
                SequenceItem si = aSi.get(siIterator);
                if (si != null && sequenceItem.equals(si)) {
                    if (aSiIterator>=step)
                        antecendents.addAll(sequenceLists.get(aSiIterator-step));
                }
            
            }
        }      
        return antecendents;
    }
    
    public ArrayList<SequenceItem> getConsequences(SequenceItem sequenceItem, int step) {
        ArrayList<SequenceItem> consequences = new ArrayList<SequenceItem>();
        for (int aSiIterator = 0; aSiIterator < sequenceLists.size(); aSiIterator++) {
            ArrayList<SequenceItem> aSi = sequenceLists.get(aSiIterator);
            for (int siIterator = 0; siIterator < aSi.size(); siIterator++) {
                SequenceItem si = aSi.get(siIterator);
                if (si != null && sequenceItem.equals(si)) {
                    if (aSiIterator<sequenceLists.size()-step)
                        consequences.addAll(sequenceLists.get(aSiIterator+step));
                }        
            }
        }  
        return consequences;
    }

    public ArrayList<SequenceItem> getLastSequence () {
        ArrayList<SequenceItem> sequence = new ArrayList<SequenceItem>();
        sequence.addAll(sequenceLists.get(sequenceLists.size()-1));

        for (SequenceItem si : sequence) {
            System.out.print(si.name + " ");
        }
        System.out.println();
        return sequence;
    }
    
    public ArrayList<SequenceItem> calculate(ArrayList<SequenceItem> sequenceList, int meabyOrPercentDiffrent) {
        int[] groups = new int[SequenceEnum.values().length];
        Arrays.fill(groups, 0);
       
        for (SequenceItem si : sequenceList) {
            if (si.equals(new Dead())) groups[SequenceEnum.DEAD.ordinal()]++;
            if (si.equals(new Punch())) groups[SequenceEnum.PUNCH.ordinal()]++;
            if (si.equals(new Take())) groups[SequenceEnum.TAKE.ordinal()]++;
            if (si.equals(new Blank())) groups[SequenceEnum.BLANK.ordinal()]=0;
            if (si.equals(new Cave())) groups[SequenceEnum.CAVE.ordinal()]++;
            if (si.equals(new Gold())) groups[SequenceEnum.GOLD.ordinal()]++;
            if (si.equals(new Hole())) groups[SequenceEnum.HOLE.ordinal()]++;
            if (si.equals(new Wall())) groups[SequenceEnum.WALL.ordinal()]++;
            if (si.equals(new Wumpus())) groups[SequenceEnum.WUMPUS.ordinal()]++;
            if (si.equals(new Darkness())) groups[SequenceEnum.DARKNESS.ordinal()]++;
            if (si.equals(new Luminance())) groups[SequenceEnum.LUMINANCE.ordinal()]++;
            if (si.equals(new Smell())) groups[SequenceEnum.SMELL.ordinal()]++;
            if (si.equals(new Wind())) groups[SequenceEnum.WIND.ordinal()]++;    
        }
        
        //Szukanie maxa
        int maxValue=0;
        ArrayList<Integer> maxIndex = new ArrayList<Integer>();     
        ArrayList<SequenceItem> toPredicate = new ArrayList<SequenceItem>();  
        for (int i=0; i<groups.length; i++) {
            if (groups[i]>maxValue) maxValue=groups[i];
        }
        
        //Szuaknie najczęściej występujących - wyniki predykatu
        for (int i=0; i<groups.length; i++) {
            if (groups[i]>=maxValue*((100-meabyOrPercentDiffrent)/100.0)) maxIndex.add(i);         
        }
        
        //zastepowanie indeksow najczesciej wystepujacych na predykaty
        for (int i : maxIndex) {
            switch(i) {
                case 0: toPredicate.add(new Dead()); break;
                case 1: toPredicate.add(new Punch()); break;
                case 2: toPredicate.add(new Take()); break;
                case 3: toPredicate.add(new Blank()); break;
                case 4: toPredicate.add(new Cave()); break;
                case 5: toPredicate.add(new Gold()); break;
                case 6: toPredicate.add(new Hole()); break;
                case 7: toPredicate.add(new Wall()); break;
                case 8: toPredicate.add(new Wumpus()); break;
                case 9: toPredicate.add(new Darkness()); break;
                case 10: toPredicate.add(new Luminance()); break;
                case 11: toPredicate.add(new Smell()); break;
                case 12: toPredicate.add(new Wind()); break; 
            }
        }
        for (SequenceItem si : toPredicate) {
            System.out.print(si.name + " ");
        }
        if (toPredicate.isEmpty()) {
            System.out.println("aaa");
        }
        return toPredicate;
    }

    
        
     public void print() {
        int i=0;
        for (ArrayList<SequenceItem> aSi: sequenceLists) {
            i++;
            System.out.print(i + " ");
            for (SequenceItem si : aSi) {
                System.out.print(si.name + " ");
            }
            System.out.println();
        }
     }   
     
}

