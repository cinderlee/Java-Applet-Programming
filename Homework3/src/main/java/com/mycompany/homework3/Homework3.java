/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.homework3;

/**
 *
 * @author cindylee
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class Homework3 {
    
    static JButton[] buttons;
    static ButtonThread[] togglers;
    
    public static void main(String[] args){
        // setting up
        int cellNum = 8;
        int colNum = 2;
        buttons = new JButton[cellNum];
        togglers = new ButtonThread[cellNum];
        
        // set up the color toggle threads
        for (int toggleIndex = 0; toggleIndex < cellNum; toggleIndex++){
            togglers[toggleIndex] = new ButtonThread(toggleIndex);
        }
        
        Random rand = new Random();
        
        JFrame jf = new JFrame("Homework 3");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(600, 600);
        
        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(cellNum / colNum, colNum, 4, 4));
        
        // setting up array of buttons
        for(int buttonIndex = 0; buttonIndex < cellNum; buttonIndex++){
            int red = rand.nextInt(256);
            int green = rand.nextInt(256);
            int blue = rand.nextInt(256);
            Color buttonColor = new Color(red, green, blue);
            
            buttons[buttonIndex] = new JButton();
            buttons[buttonIndex].setOpaque(true);
            buttons[buttonIndex].setBorderPainted(false);
            buttons[buttonIndex].setBackground(buttonColor);
            buttons[buttonIndex].addActionListener(new ButtonListener(buttonIndex));
            jp.add(buttons[buttonIndex]);
        }
        
        jf.add(jp);
        
        jf.setVisible(true);
        
        // start the toggle threads
        for (int toggleNum = 0; toggleNum < cellNum; toggleNum++){
            togglers[toggleNum].start();
        }
    }
}

class ButtonListener implements ActionListener{
    int idNum;
    
    ButtonListener(int number){
        idNum = number;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        Homework3.togglers[idNum].toggle();
    }
}

class ButtonThread extends Thread{
    int idNum;
    boolean changeColor = true;
    
    ButtonThread(int number){
        idNum = number;
    }
    
    void toggle(){
        changeColor = !changeColor;
    }
    
    @Override
    public void run(){
        while(true){
            if (changeColor){
                Random rand = new Random();
                int red = rand.nextInt(256);
                int green = rand.nextInt(256);
                int blue = rand.nextInt(256);
                Color buttonColor = new Color(red, green, blue);
                
                Homework3.buttons[idNum].setBackground(buttonColor);
            }
            try{
                sleep(1000);
            }
            catch(InterruptedException ex){
            }
        }
    }
}