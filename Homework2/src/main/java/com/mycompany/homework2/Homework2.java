/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.homework2;

/**
 *
 * @author cindylee
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class Homework2 {
    
    static JButton[] buttons;
    
    public static void main(String[] args){
        // setting up
        int cellNum = 8;
        int colNum = 2;
        buttons = new JButton[cellNum];
        
        Random rand = new Random();
        
        JFrame jf = new JFrame("Homework 2");
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
    }
}

class ButtonListener implements ActionListener{
    int idNum;
    
    ButtonListener(int number){
        idNum = number;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        Random rand = new Random();
        for (int index = 0; index < Homework2.buttons.length; index++){
            if (index != idNum){
                int red = rand.nextInt(256);
                int green = rand.nextInt(256);
                int blue = rand.nextInt(256);
                Color buttonColor = new Color(red, green, blue);
                
                Homework2.buttons[index].setBackground(buttonColor);
            }
        }
    }
}