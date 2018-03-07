/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import evironment.Environment;
import java.awt.Color;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import world.Position;

/**
 *
 * @author Tomasz
 */
public class BoardButton extends JButton{
    private int x;
    private int y;
    private Window window;
    public ArrayList<Environment> elements = new ArrayList<Environment>();
    public BoardButton(int x, int y, Window window) {
        this.x = x;
        this.y = y;
        this.window = window;
        setBackground(Color.WHITE);
        setMargin(new Insets(0,0,0,0));
        setBorder(new EmptyBorder(0,0,0,0));
      
    }
      
    public void setSelected() {
        window.board.setSelected(this);
    }
    
    public Position getPosiion() {
        return new Position(x, y);
    }
}
