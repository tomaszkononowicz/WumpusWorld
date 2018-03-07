/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sequence;

import action.*;
import element.*;
import evironment.*;
import static java.awt.event.KeyEvent.VK_P;
import gui.Logs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import world.Position;
import world.World;

/**
 *
 * @author Tomasz
 */
public class Agent extends SequenceItem {

    private int arrow;
    private int key;
    private int gold;
    private Position oldPosition;
    private Position target;
    private Sequence sequence;
    double[] probability = new double[4];
    double[][] probabilityBoard;
    boolean[][] visitedBoard;
    double desire;
    public ArrayList<SequenceItem> goodThings;
    public ArrayList<SequenceItem> badThings;
    private ArrayList<SequenceItem> goodThingsPrevious;
    private ArrayList<SequenceItem> badThingsPrevious;
    private static final int LEFT = 0;
    private static final int UP = 1;
    private static final int RIGHT = 2;
    private static final int DOWN = 3;

    public Agent(Position position, World world, Sequence sequence, ArrayList<SequenceItem> goodThingsPrevious, ArrayList<SequenceItem> badThingsPrevious) {
        super(position, world);
        oldPosition = position;
        arrow = 1;
        gold = 0;
        desire = 0;
        name = "Agent";
        probability[LEFT] = 1;
        probability[UP] = 1;
        probability[RIGHT] = 1;
        probability[DOWN] = 1;
        goodThings = new ArrayList<SequenceItem>();
        this.goodThingsPrevious = goodThingsPrevious;
        goodThings.add(new Take());
        badThings = new ArrayList<SequenceItem>();
        this.badThingsPrevious = badThingsPrevious;
        badThings.add(new Dead());
        badThings.add(new Punch());
        this.sequence = sequence;
        sequence.nextSequenceItem();
        sequence.addSequenceItem(new Blank());
        probabilityBoard = new double[world.getWidth()][];
        visitedBoard = new boolean[world.getWidth()][];
        for (int i = 0; i < world.getWidth(); i++) {
            probabilityBoard[i] = new double[world.getHeight()];
            visitedBoard[i] = new boolean[world.getHeight()];
            for (int j = 0; j < world.getHeight(); j++) {
                probabilityBoard[i][j] = 1;
                visitedBoard[i][j] = false;
            }
        }

    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getArrow() {
        return arrow;
    }

    public int getGold() {
        return gold;
    }

    public double getDesire() {
        return desire;
    }

    public boolean[][] getVisitedBoard() {
        return visitedBoard;
    }

    public double[][] getProbabilityBoard() {
        return probabilityBoard;
    }

    public void setTarget(Position target) {
        this.target = target;
    }

    public void calculateProbability() {
        desire = world.getWindow().adding.getDesire();
        probability[LEFT] = 0;
        probability[UP] = 0;
        probability[RIGHT] = 0;
        probability[DOWN] = 0;
        if (position.getX() - 1 >= 0) {
            if (target.getX() - position.getX() < 0) 
                probabilityBoard[position.getX() - 1][position.getY()]+=desire;
            probability[LEFT] += probabilityBoard[position.getX() - 1][position.getY()];
        }
        if (position.getY() - 1 >= 0) {
            if (target.getY() - position.getY() < 0) 
                probabilityBoard[position.getX()][position.getY() - 1]+=desire;
            probability[UP] += probabilityBoard[position.getX()][position.getY() - 1];
        }
        if (position.getX() + 1 < world.getWidth()) {
            if (target.getX() - position.getX() > 0) 
                probabilityBoard[position.getX() + 1][position.getY()]+=desire;
            probability[RIGHT] += probabilityBoard[position.getX() + 1][position.getY()];
        }
        if (position.getY() + 1 < world.getHeight()) {
            if (target.getY() - position.getY() > 0) 
                probabilityBoard[position.getX()][position.getY() + 1]+=desire;
            probability[DOWN] += probabilityBoard[position.getX()][position.getY() + 1];
        }

        world.getWindow().board.refreshBoard(world);
        
        if (target.getX() - position.getX() < 0) 
            probabilityBoard[position.getX() - 1][position.getY()]-=desire;
        if (target.getY() - position.getY() < 0) 
            probabilityBoard[position.getX()][position.getY() - 1]-=desire;
        if (target.getX() - position.getX() > 0) 
            probabilityBoard[position.getX() + 1][position.getY()]-=desire;
        if (target.getY() - position.getY() > 0) 
            probabilityBoard[position.getX()][position.getY() + 1]-=desire;
        
        /*probability[LEFT] += desire;
        probability[UP] += desire;
        probability[RIGHT] += desire;
        probability[DOWN] += desire;*/
    }

    private void probabilityAround(double value) {
        int x = position.getX();
        int y = position.getY();
        if (x - 1 >= 0 && !visitedBoard[x - 1][y]) {
            probabilityBoard[x - 1][y] += value;
        }
        if (y + 1 < world.getHeight() && !visitedBoard[x][y + 1]) {
            probabilityBoard[x][y + 1] += value;
        }
        if (x + 1 < world.getWidth() && !visitedBoard[x + 1][y]) {
            probabilityBoard[x + 1][y] += value;
        }
        if (y - 1 >= 0 && !visitedBoard[x][y - 1]) {
            probabilityBoard[x][y - 1] += value;
        }

        if (x - 1 >= 0 && probabilityBoard[x - 1][y] < 0) {
            probabilityBoard[x - 1][y] = 0;
        }
        if (y + 1 < world.getHeight() && probabilityBoard[x][y + 1] < 0) {
            probabilityBoard[x][y + 1] = 0;
        }
        if (x + 1 < world.getWidth() && probabilityBoard[x + 1][y] < 0) {
            probabilityBoard[x + 1][y] = 0;
        }
        if (y - 1 >= 0 && probabilityBoard[x][y - 1] < 0) {
            probabilityBoard[x][y - 1] = 0;
        }
    }

    public void action() {
        for (int i = 0; i < world.getWidth(); i++) {
            for (int j = 0; j < world.getHeight(); j++) {
                if (visitedBoard[i][j]) {
                probabilityBoard[i][j] = world.getWindow().adding.getWeariness();
                }
            }
        }
        //probabilityBoard[position.getX()][position.getY()] = 1;
        
        ArrayList<SequenceItem> opponents = world.getElements(position);
        for (SequenceItem opponent : opponents) {
            if (badThingsPrevious.contains(opponent) || goodThingsPrevious.contains(opponent)) {
                int bad = Collections.frequency(badThingsPrevious, opponent);
                int good = Collections.frequency(goodThingsPrevious, opponent);
                double p=(good/(double)(bad+good)) - (bad/(double)(bad+good));
                if (visitedBoard[position.getX()][position.getY()]==false) 
                    probabilityAround(p);
            }
        }
        
        visitedBoard[position.getX()][position.getY()] = true;
        
        calculateProbability();
               
        oldPosition = position;
        Position newPosition;
        Random generator = new Random();
        //jezeli stoi na rzeczy ktora powodowala bad things to zmniejsz prawdopodobienstwo, a jezeli na rzeczy ktora powodowala good thins to zwieksz prawdopodobienstwo
        //jezeli byl juz na jakiejs kratce to to prawdopodobienstwo musi byc stale
        double sumOfProbability = 0;
        for (int i = 0; i < 4; i++) {
            sumOfProbability += probability[i];
        }

        //wpływ predykatów na losowanie gdzie iść + czy strzelic do smoka
        double r = generator.nextDouble() * sumOfProbability;
        if (r <= probability[LEFT]) {
            newPosition = new Position(getPosition().getX() - 1, getPosition().getY());
            action(newPosition);
        } else if (r <= probability[UP] + probability[LEFT]) {
            newPosition = new Position(getPosition().getX(), getPosition().getY() - 1);
            action(newPosition);
        } else if (r <= probability[RIGHT] + probability[UP] + probability[LEFT]) {
            newPosition = new Position(getPosition().getX() + 1, getPosition().getY());
            action(newPosition);
        } else if (r <= probability[DOWN] + probability[RIGHT] + probability[UP] + probability[LEFT]) {
            newPosition = new Position(getPosition().getX(), getPosition().getY() + 1);
            action(newPosition);
        }

        if (world.getTurns() % 100 == 0 && world.getTurns() >= 100) {
            for (SequenceItem bad : badThings) {
                ArrayList<SequenceItem> antecendents = sequence.getAntecendents(bad, 2);
                for (SequenceItem antecendent : antecendents) {
                    if (antecendent instanceof Environment) {
                        badThingsPrevious.add(antecendent);
                    }
                }
            }
            for (SequenceItem good : goodThings) {
                ArrayList<SequenceItem> antecendents = sequence.getAntecendents(good, 2);
                for (SequenceItem antecendent : antecendents) {
                    if (antecendent instanceof Environment) {
                        goodThingsPrevious.add(antecendent);
                    }
                }
            }
        }
        
        Logs.clear();
        Environment[] env = {new Darkness(), new Luminance(), new Smell(), new Wind()};
        for (Environment e : env) {
            if (badThingsPrevious.contains(e) || goodThingsPrevious.contains(e)) {
                int bad = Collections.frequency(badThingsPrevious, e);
                int good = Collections.frequency(goodThingsPrevious, e);
                double p=(good/(double)(bad+good)) - (bad/(double)(bad+good));
                Logs.addLog(e.getName().concat(" ").concat(String.format("%.4f", p)));
            }
        }


       

       
       
        

        switch (key) {
            case VK_P:
                if (arrow > 0) {
                    arrow--;
                    shot();
                    Logs.addLog("Agent strzelil");

                }
                break;
        }
    }
    

    public void shot() {
    }

    public void action(Position newPosition) {
        World worldTmp = getWorld();
        if ((worldTmp.inMap(newPosition)) && (!getPosition().equal(newPosition))) {
            ArrayList<SequenceItem> opponents = worldTmp.getElements(newPosition);

            if (!opponents.isEmpty()) {
                for (SequenceItem opponent : opponents) {
                    /*PREDYKATY - CO SIE MA STAC JEZELI AGENT BEDZIE PROBOWAL STANAC NA INNE POLE*/
                    sequence.addSequenceItem(opponent);
                    setPosition(newPosition);
                    if (opponent instanceof Hole) {
                        sequence.nextSequenceItem();
                        //sequence.addSequenceItem(new Punch());
                        sequence.addSequenceItem(new Dead());
                        world.getContainer().delete(this);
                    } else if (opponent instanceof Gold) {
                        sequence.nextSequenceItem();
                        sequence.addSequenceItem(new Take());
                        gold = 1;
                        world.getContainer().delete(opponent);
                        setTarget(new Position(0, 0));
                    } else if (opponent instanceof Wall) {
                        sequence.nextSequenceItem();
                        sequence.addSequenceItem(new Punch());
                        visitedBoard[newPosition.getX()][newPosition.getY()] = true;
                        probabilityBoard[newPosition.getX()][newPosition.getY()] = 1;
                        sequence.nextSequenceItem();
                        action(oldPosition);
                    } else if (opponent instanceof Cave) {
                        sequence.addSequenceItem(new Gold());
                        sequence.nextSequenceItem();
                        sequence.addSequenceItem(new Take());
                        sequence.addSequenceItem(new Dead());
                        world.getContainer().delete(this);
                        sequence.nextSequenceItem();
                        sequence.addSequenceItem(new Take());
                    } else if (opponent instanceof Wumpus) {
                        sequence.nextSequenceItem();
                        sequence.addSequenceItem(new Dead());
                        world.getContainer().delete(this);
                    }
                }
            } else {
                sequence.addSequenceItem(new Blank(newPosition, world));
                setPosition(newPosition);
            }
        }
    }
    /*
        public boolean collision(SequenceItem opponent) {
            
                            
                    if (opponent instanceof Agent) {
                        
                    }
        return true;
    }*/

}
