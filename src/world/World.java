/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world;

import action.Dead;
import action.Punch;
import action.Take;
import element.Element;
import sequence.Agent;
import element.*;
import evironment.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import sequence.Container;
import sequence.SequenceItem;
import gui.Logs;
import gui.Window;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import sequence.Sequence;

/**
 *
 * @author Tomasz
 */
public class World {
    private int turns;
    private int height;
    private int width;
    private Container container;
    private Sequence sequence;
    private ArrayList<SequenceItem> goodThingsPrevious;
    private ArrayList<SequenceItem> badThingsPrevious;
    private Sequence sumSequence;
    private Window window;
    private boolean pause;
    private static int MAX_TURNS = 1000;
    public World(int width, int height) {
        this.width = width;
        this.height = height;
        container = new Container();
        sequence = new Sequence(); 
        goodThingsPrevious = new ArrayList<SequenceItem>();
        badThingsPrevious = new ArrayList<SequenceItem>();
        
    }
    
    public void game() {
        pause=false;
        for (int i=0; i<MAX_TURNS; i++) {
        turns++;
        newGame();
        
        while (!warunkiSpelnione()) {
            while (getPause()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(World.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            long start = System.nanoTime();
            //Logs.clear();
            /*ZAPETLIC*/
            //window.board.refreshBoard(this);
            nextTurn();
            //Logs.addLogs(Logs.getLogs());
            

            if (getElements(window.board.getSelected().getPosiion()).isEmpty()) {
                window.adding.setVisibility(true);
            } else {
                window.adding.setVisibility(false);
            }

            if (getContainer().getAgent() == null) {
                //Logs.addLog("Brak agenta na planszy!");  
            }

            
            while (System.nanoTime() - start < 1e9 / window.adding.getSpeed());
            
        }
        
     


            
            
//            percent = 80;
//            sequence.calculate(sequence.getAntecendents(new Dead(), 2), percent);
//            System.out.print("<- 2 "+new Dead().getName()+" 2 -> "); 
//            //sequence.calculate(sequence.getConsequences(new Dead(), 2));      
//            System.out.println(""); 
//
//            sequence.calculate(sequence.getAntecendents(new Punch(), 2), percent);
//            System.out.print("<- 2 "+new Punch().getName()+" 2 -> "); 
//            //sequence.calculate(sequence.getConsequences(new Punch(), 2));      
//            System.out.println(""); 
//
//            sequence.calculate(sequence.getAntecendents(new Take(), 2), percent);
//            System.out.print("<- 2 "+new Take().getName()+" 2 -> "); 
//            //sequence.calculate(sequence.getConsequences(new Take(), 2));      
//            System.out.println(""); 

        }
            int percent = 70;
            System.out.println("Predykaty"); 
            sequence.calculate(sequence.getAntecendents(new Hole(), 1), percent);
            System.out.print("<- "+new Hole().getName()+" -> "); 
            sequence.calculate(sequence.getConsequences(new Hole(), 1), percent);      
            System.out.println(""); 

            sequence.calculate(sequence.getAntecendents(new Gold(), 1), percent);
            System.out.print("<- "+new Gold().getName()+" -> "); 
            sequence.calculate(sequence.getConsequences(new Gold(), 1), percent);      
            System.out.println("");

            sequence.calculate(sequence.getAntecendents(new Wumpus(), 1), percent);
            System.out.print("<- "+new Wumpus().getName()+" -> "); 
            sequence.calculate(sequence.getConsequences(new Wumpus(), 1), percent);      
            System.out.println("");

            sequence.calculate(sequence.getAntecendents(new Wall(), 1), percent);
            System.out.print("<- "+new Wall().getName()+" -> "); 
            sequence.calculate(sequence.getConsequences(new Wall(), 1), percent);      
            System.out.println("");

            sequence.calculate(sequence.getAntecendents(new Cave(), 1), percent);
            System.out.print("<- "+new Cave().getName()+" -> "); 
            sequence.calculate(sequence.getConsequences(new Cave(), 1), percent);      
            System.out.println("");
        

        

//        sequence.calculate(sequence.getAntecendents(new Hole(), 1));
//        System.out.print("<- "+new Hole().getName()+" -> "); 
//        sequence.calculate(sequence.getConsequences(new Hole(), 1));      
//        System.out.println(""); 
//        
//        sequence.calculate(sequence.getAntecendents(new Gold(), 1));
//        System.out.print("<- "+new Gold().getName()+" -> "); 
//        sequence.calculate(sequence.getConsequences(new Gold(), 1));      
//        System.out.println("");
//        
//        sequence.calculate(sequence.getAntecendents(new Wumpus(), 1));
//        System.out.print("<- "+new Wumpus().getName()+" -> "); 
//        sequence.calculate(sequence.getConsequences(new Wumpus(), 1));      
//        System.out.println("");
//        
//        sequence.calculate(sequence.getAntecendents(new Wall(), 1));
//        System.out.print("<- "+new Wall().getName()+" -> "); 
//        sequence.calculate(sequence.getConsequences(new Wall(), 1));      
//        System.out.println("");
//        
//        sequence.calculate(sequence.getAntecendents(new Cave(), 1));
//        System.out.print("<- "+new Cave().getName()+" -> "); 
//        sequence.calculate(sequence.getConsequences(new Cave(), 1));      
//        System.out.println("");
//        
//        sequence.calculate(sequence.getAntecendents(new Dead(), 2));
//        System.out.print("<- 2 "+new Dead().getName()+" 2 -> "); 
//        //sequence.calculate(sequence.getConsequences(new Dead(), 2));      
//        System.out.println(""); 
//        
//        sequence.calculate(sequence.getAntecendents(new Punch(), 2));
//        System.out.print("<- 2 "+new Punch().getName()+" 2 -> "); 
//        sequence.calculate(sequence.getConsequences(new Punch(), 2));      
//        System.out.println(""); 
//        
//        sequence.calculate(sequence.getAntecendents(new Gold(), 2));
//        System.out.print("<- 2 "+new Gold().getName()+" 2 -> "); 
//        sequence.calculate(sequence.getConsequences(new Gold(), 2));      
//        System.out.println(""); 
       
        
        
        
        
        /*
        System.out.println("Poprzednicy dziury");
        sequence.getAntecendents(new Hole());
        */
        /*System.out.println("Ostatnia sekwencja");
        sequence.getLastSequence();*/

        Logs.addLog("Koniec");
    }
    
    public boolean warunkiSpelnione() {
        Agent agent = getContainer().getAgent();
        if (agent == null) return true;
        if (agent.getPosition().getX() == 0 
                && agent.getPosition().getY() == 0 
                && agent.getGold() > 0) return true;
        return false;
    }
    
    public void setWindow(Window window) {
        this.window = window;
    }
    
    public int getTurns() {
        return turns;
    }
    
    public int getHeight() {
        return height;
    }
    
    public int getWidth() {
        return width;
    }
    
    public Window getWindow() {
        return window;
    }
    
    public boolean getPause() {
        return pause;
    }
    
    public void setPause(boolean pause) {
        this.pause = pause;
    }
    
    public Container getContainer() {
        return container;
    }
    
    public boolean inMap(Position position) {
        if (position.getX() < 0
                || position.getX() >= width
                || position.getY() < 0
                || position.getY() >= height) return false;
        return true;
    }
    
    public ArrayList<SequenceItem> getElements(Position position) {
        ArrayList<SequenceItem> elements = new ArrayList<SequenceItem>();
        for (SequenceItem e : getContainer().getListElement()) {
            
            if (e != null && e.getPosition().getX() == position.getX()
                    && e.getPosition().getY() == position.getY())
                elements.add(e);
        }
        return elements;
    }
       
    public void nextTurn(){
        //Logs.clear();
        getContainer().removeNulls();
        sequence.nextSequenceItem();
	int size = getContainer().getSize();
	for (int i = 0; i < size; i++) {
                SequenceItem si = getContainer().getElement(i);
		if (si != null) {
                    if (si instanceof Agent) {
                        Agent agent = (Agent)si;
                        agent.action();
                    }
                }		
	}
        //sequence.print();
        
    }
    
    
    public void generateAround(Class<?> cls, Position p) {
        int x = p.getX();
        int y = p.getY();
        int[] tabX = {-1, 0, 1, 0};
        int[] tabY = {0, 1, 0, -1};
        boolean canPlace;
        ArrayList<SequenceItem> si;
        for (int i=0; i<4; i++) {
            canPlace = true;
            si = getElements(new Position(x+tabX[i], y+tabY[i]));
            for (int k=0; k<si.size(); k++) {
                if (si.get(k) instanceof Element) canPlace = false;
                if (si.get(k) instanceof Agent) canPlace = false;
                if (si.get(k).getClass().getName().equals(cls.getName())) canPlace = false;
            }
            if (canPlace) {
                try {
                    cls.getDeclaredConstructor(Position.class, World.class).newInstance(new Position(x+tabX[i], y+tabY[i]), this);
                } catch (NoSuchMethodException ex) {
                    Logger.getLogger(World.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SecurityException ex) {
                    Logger.getLogger(World.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(World.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(World.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(World.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(World.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
        }
        
    }
    
    public void newGame() {
        container.clear();
        Random generator = new Random();
        Agent agent = new Agent(new Position(0,0), this, sequence, badThingsPrevious, goodThingsPrevious);
        sequence.nextSequenceItem();
        sequence.addSequenceItem(new Blank());
        
        for (int i = 0; i < 4; i++) {
            int x, y;
            do {
                x = generator.nextInt(width);
                y = generator.nextInt(height);	
            } while (!getElements(new Position(x, y)).isEmpty());
            Position position = new Position(x, y);
            new Hole(position, this);
            generateAround(Wind.class, position);
            
        }
        for (int i = 0; i < 6; i++) {
            int x, y;
            do {
                x = generator.nextInt(width);
                y = generator.nextInt(height);	
            } while (!getElements(new Position(x, y)).isEmpty());
            Position position = new Position(x, y);
            switch (i) { 
                case 0: 
                    new Gold(position, this); 
                    generateAround(Luminance.class, position);
                    break;
                case 1: 
                    new Wall(position, this); 
                    break;
                case 9: 
                    new Wall(position, this); 
                    break;
                case 3: 
                    new Wumpus(position, this); 
                    generateAround(Smell.class, position);
                    break;
                case 4: 
                    new Cave(position, this); 
                    generateAround(Darkness.class, position);
                    break;
                case 10: 
                    new Cave(position, this); 
                    generateAround(Darkness.class, position);
                    break;
            }
        }
        agent.setTarget(getContainer().getGold().getPosition());
        
    }
}
