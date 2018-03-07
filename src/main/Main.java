/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import gui.Window;
import gui.KeyboardListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import sequence.SequenceItem;
import world.Position;
import world.World;

/**
 *
 * @author Tomasz
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    private static final int WIDTH = 8;
    private static final int HEIGHT = 8;
    
    
    public static void main(String[] args) {
        World world = new World(WIDTH,HEIGHT);  
        Window window = new Window(world);
        world.setWindow(window);
        window.getBoard().refreshBoard(world);
        world.game();
    }
    
}
