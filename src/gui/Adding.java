/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import sequence.SequenceItem;
import java.util.ArrayList;
import javax.swing.event.ChangeListener;
import javax.swing.JSlider;
import world.World;

/**
 *
 * @author Tomasz
 */
public class Adding extends JPanel {

    private JLabel field = new JLabel("Pole");
    private JLabel element = new JLabel();
    private JLabel ageLabel = new JLabel("Przejscie");
    private JLabel age = new JLabel();
    private JLabel speedLabel = new JLabel("Prędkość gry");
    private JSlider speed = new JSlider(JSlider.HORIZONTAL, 1, 1000, 1);
    private JLabel speedValueLabel = new JLabel("");
    private JLabel desireLabel = new JLabel("Pragnienie");
    private JLabel desireValueLabel = new JLabel("");
    private JSlider desire = new JSlider(JSlider.HORIZONTAL, 0, 300, 0);
    private JLabel wearinessValueLabel = new JLabel("");
    private JLabel wearinessLabel = new JLabel("Znużenie");
    private JSlider weariness = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
    
    private World world;
    private Window window;
    private JButton newGame = new JButton("Nowa gra");
    private ButtonsListener buttonsListener;
    
    private ChangeListener changeListener;

    public Adding(World world, Window window) {
        this.world = world;
        this.window = window;
        buttonsListener = new ButtonsListener(world, window);
        setBackground(Color.LIGHT_GRAY);
        setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));
        setPreferredSize(new Dimension(100,100));
        /*field.setFocusable(false);
        add(field);
        element.setFocusable(false);
        add(element);*/
        ageLabel.setFocusable(false);
        add(ageLabel);
        age.setFocusable(false);
        add(age);
        /*newGame.setFocusable(false);
        newGame.addActionListener(buttonsListener);
        add(newGame);*/
        speedLabel.setFocusable(false);
        add(speedLabel);
        speed.setFocusable(false);
        speed.addChangeListener(changeListener);
        add(speed);
        speedValueLabel.setFocusable(false);
        add(speedValueLabel);
        
        desireLabel.setFocusable(false);
        add(desireLabel);
        desire.setFocusable(false);
        desire.addChangeListener(changeListener);
        add(desire);
        desireValueLabel.setFocusable(false);
        add(desireValueLabel);
        
        wearinessLabel.setFocusable(false);
        add(wearinessLabel);
        weariness.setFocusable(false);
        weariness.addChangeListener(changeListener);
        add(weariness);
        wearinessValueLabel.setFocusable(false);
        add(wearinessValueLabel);
        
        
        
        
        if (window.board.getSelected() != null) {
            if (world.getElements(window.board.getSelected().getPosiion()).isEmpty()) {
                setVisibility(true);
            } else {
                setVisibility(false);
            }
        } else {
            field.setVisible(false);
            element.setVisible(false);
        }

    }
    
    public int getSpeed(){
        return speed.getValue();
    }
    
    public double getDesire(){
        return desire.getValue()/100.0;
    }
    
    public double getWeariness(){
        return 1-(weariness.getValue()/100.0);
    }

    public void setVisibility(boolean adding) {
        field.setText("Wybrana pozycja: " + window.board.getSelected().getPosiion().toString());
        speedValueLabel.setText(Integer.toString(speed.getValue()));
        wearinessValueLabel.setText(Double.toString(weariness.getValue()/100.0));
        desireValueLabel.setText(Double.toString(desire.getValue()/100.0));
        age.setText(Integer.toString(world.getTurns()));
        if (adding) {
            field.setVisible(true);
            element.setVisible(false);
        } else {
            ArrayList<SequenceItem> elements = world.getElements(window.board.getSelected().getPosiion());
            field.setVisible(true);
            String names = new String();
            for (SequenceItem e : elements) {
                names += (e.getName() + " ") ;
            }
            element.setText(names);
            element.setVisible(true);
        }

    }
    
   
    
    public void worldUpdate(World world) {
        buttonsListener.worldUpdate(world);
    }
}
