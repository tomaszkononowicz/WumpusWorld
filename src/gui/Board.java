/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import element.*;
import sequence.Agent;
import java.awt.Color;
import java.awt.GridBagConstraints;
import static java.awt.GridBagConstraints.BOTH;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import sequence.SequenceItem;
import evironment.*;
import java.util.ArrayList;
import world.Position;
import world.World;

/**
 *
 * @author Tomasz
 */
public class Board extends JPanel{
    private BoardButton[][] boardButtons;
    private BoardButton selected;
    private ButtonsListener boardListener;
    public Board(World world, Window window) {
        setBackground(Color.GRAY);
        setLayout(new GridBagLayout());
        setFocusable(false);
        setBorder(new EmptyBorder(15,15,15,15));
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = BOTH;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.insets = new Insets(1,1,1,1);
        boardListener = new ButtonsListener(world, window);
        boardButtons = new BoardButton[world.getWidth()][];
        for (int x=0; x<world.getWidth(); x++) {
            boardButtons[x]=new BoardButton[world.getHeight()];
            for (int y=0; y<world.getHeight(); y++) {
                boardButtons[x][y]=new BoardButton(x,y,window);
                boardButtons[x][y].addActionListener(boardListener);
                gridBagConstraints.gridx = x;
                gridBagConstraints.gridy = y;
                add(boardButtons[x][y], gridBagConstraints);
                boardButtons[x][y].setVisible(true);
                boardButtons[x][y].setFocusable(false);
                selected = boardButtons[x][y];
            }
        }
    }
    
    public void setSelected(BoardButton selected) {
        this.selected = selected;
    }
    
    public BoardButton getSelected() {
        return selected;
    }
    
    public void refreshBoard(World world) {
        Position position = new Position(0,0);
        double[][] probabilityBoard = null;
        boolean[][] visitedBoard = null;
        if (world.getContainer().getAgent() != null) {
            probabilityBoard = world.getContainer().getAgent().getProbabilityBoard();
            visitedBoard = world.getContainer().getAgent().getVisitedBoard();
        }
        //ImageIcon iI = new ImageIcon("empty.png");
        for (int x=0; x<world.getWidth(); x++) {
            for (int y=0; y<world.getHeight(); y++) {
                position.setX(x);
                position.setY(y);
                BoardButton bb = boardButtons[x][y];
                bb.setBackground(Color.WHITE);
                ArrayList<SequenceItem> elements = world.getElements(position);
                String label = "";

                if (probabilityBoard != null) {
                    label = String.format("%.2f", probabilityBoard[x][y]) + " ";
                    if (probabilityBoard[x][y]<1) bb.setBackground(Color.PINK);
                    if (visitedBoard != null) {
                        if (visitedBoard[x][y]) bb.setBackground(Color.YELLOW);
                    }   
                    if (probabilityBoard[x][y]<=0) bb.setBackground(Color.RED);
                    if (probabilityBoard[x][y]>1) bb.setBackground(Color.GREEN);
                }

                
                if (elements.isEmpty()) {
                    
                }
                else {
                    for (SequenceItem e : elements) {
                        if (e != null) {
                            if (e instanceof Wumpus) label += "Smok ";
                            else if (e instanceof Wall) label += "Ściana ";
                            else if (e instanceof Hole) label += "Dziura ";
                            else if (e instanceof Gold) label += "Złoto ";
                            else if (e instanceof Agent) {
                                if (((Agent) e).getGold()>0) bb.setBackground(new Color(150, 75, 0));
                                else bb.setBackground(Color.ORANGE);
                                label += "Agent ";
                            }
                            else if (e instanceof Wind) label += "Wiatr ";
                            else if (e instanceof Smell) label += "Zapach ";
                            else if (e instanceof Luminance) label += "Blask ";
                            else if (e instanceof Cave) label += "Jaskinia ";
                            else if (e instanceof Darkness) label += "Ciemnosc ";
                            /*if (e instanceof Wumpus) iI = new ImageIcon("tortoise.png");
                            else if (e instanceof Wall) iI = new ImageIcon("dandelion.png");
                            else if (e instanceof Hole) iI = new ImageIcon("belladona.png");
                            else if (e instanceof Grass) iI = new ImageIcon("grass.png");
                            else if (e instanceof Gold) iI = new ImageIcon("guarana.png"); 
                            else if (e instanceof Agent) iI = new ImageIcon("human1.png");
                            else iI = new ImageIcon("empty.png");*/
                        }
                        /*else iI = new ImageIcon("empty.png");
                        bb.setIcon(iI);*/
                    }
                }
                
                bb.setText(label);
            }
        }
    }
    
    public void worldUpdate(World world) {
        boardListener.worldUpdate(world);
    }
}
