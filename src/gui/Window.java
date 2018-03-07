/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import javafx.scene.layout.Border;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import world.World;

/**
 *
 * @author Tomasz
 */
public class Window extends JFrame{
    public Adding adding;
    public Board board;
    public World world;
    KeyboardListener keyboardListener;
    public Window(World world) {
        setTitle("Mateusz Szypulski s160329 i Tomasz Kononowicz s160839 - Sztuczna inteligencja - Gra w smoka");
        this.world = world;
        board = new Board(world, this);
        adding = new Adding(world, this);
        keyboardListener = new KeyboardListener(world, this);
        addKeyListener(keyboardListener);
        setSize(1200,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        setLayout(new BorderLayout());
        contentPane.setBackground(Color.BLUE);
        contentPane.add(board, BorderLayout.CENTER);
        contentPane.add(new Logs(new JTextArea()), BorderLayout.LINE_END);
        contentPane.add(adding, BorderLayout.PAGE_END);
        this.setFocusable(true);
        
        


        setVisible(true);
    }
    
    public Board getBoard() {
        return board;
    }
    
    public void updateWorld(World world) {
        this.world = world;
        keyboardListener.worldUpdate(world);
        board.worldUpdate(world);
        adding.worldUpdate(world);
    }
    
}
