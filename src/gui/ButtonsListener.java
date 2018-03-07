/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import world.Position;
import world.World;

/**
 *
 * @author Tomasz
 */
public class ButtonsListener implements ActionListener {

    private World world;
    private Window window;

    public ButtonsListener(World world, Window window) {
        this.window = window;
        this.world = world;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o instanceof BoardButton) {
            ((BoardButton) o).setSelected();
            if (world.getElements(window.board.getSelected().getPosiion()).isEmpty()) {
                window.adding.setVisibility(true);
            } else {
                window.adding.setVisibility(false);
            }
        } else if (o instanceof JButton) {
            if (((JButton) o).getText().equals("Nowa gra")) {
                world.newGame();
                Logs.clear();
                window.board.refreshBoard(world);
            } 
        }
    }

    public void worldUpdate(World world) {
        this.world = world;
    }

}
