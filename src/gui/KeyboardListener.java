/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_L;
import static java.awt.event.KeyEvent.VK_N;
import static java.awt.event.KeyEvent.VK_P;
import static java.awt.event.KeyEvent.VK_S;
import static java.awt.event.KeyEvent.VK_Z;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import world.World;

/**
 *
 * @author Tomasz
 */
public class KeyboardListener implements KeyListener {

    private World world;
    private Window window;

    public KeyboardListener(World world, Window window) {
        this.window = window;
        this.world = world;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getExtendedKeyCode() == VK_P) {
            world.setPause(!world.getPause());
            Logs.clear();
            window.board.refreshBoard(world);
            if (world.getPause()) Logs.addLog("PAUZA");
        } else
        if (e.getExtendedKeyCode() == VK_N) {
            world.newGame();
            window.board.refreshBoard(world);
            Logs.clear();
        }
        else {
            Logs.clear();
            if (world.getContainer().getAgent() != null) {
                world.getContainer().getAgent().setKey(e.getExtendedKeyCode());
            }
            /*ZAPETLIC*/
            world.nextTurn();
            Logs.addLogs(Logs.getLogs());
            window.board.refreshBoard(world);
        }
        if (world.getElements(window.board.getSelected().getPosiion()).isEmpty()) {
            window.adding.setVisibility(true);
        } else {
            window.adding.setVisibility(false);
        }
        if (world.getContainer().getAgent() == null)
            Logs.addLog("Brak agenta na planszy!");
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void worldUpdate(World world) {
        this.world = world;
    }
}
