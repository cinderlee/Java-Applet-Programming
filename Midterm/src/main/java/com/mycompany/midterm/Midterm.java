/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.midterm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author cindylee
 */
public class Midterm {
    static Question[] questions;
    static String[] responses;
    static int quesNum = 0;
    static JButton leftButton;
    static JButton rightButton;
    static JLabel question;
    static Timer timer;
    
    public static void main(String[] args){
        int numQuestions = 5;
        
        questions = new Question[numQuestions];
        responses = new String[numQuestions];
        
        questions[0] = new Question("Favorite Ice Cream", "Vanilla", "Chocolate");
        questions[1] = new Question("Which season is better", "Winter", "Summer");
        questions[2] = new Question("Which pet is better", "Cat", "Dog");
        questions[3] = new Question("Unicorns are real");
        questions[4] = new Question("Text or call", "Text", "Call");
        
        for (int index = 0; index < numQuestions; index++){
            responses[index] = "NO RESPONSE";
        }
        
        JFrame jf = new JFrame("Midterm");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(400, 400);
        
        question = new JLabel(questions[0].questionText);
        leftButton = new JButton(questions[0].leftText);
        rightButton = new JButton(questions[0].rightText);
        leftButton.addActionListener(new ButtonListener());
        rightButton.addActionListener(new ButtonListener());
        
        JPanel surveyPanel = new JPanel();
        surveyPanel.setLayout(new GridLayout(2, 1, 0, 200));
        surveyPanel.add(question);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 100, 0));
        buttonPanel.add(leftButton);
        buttonPanel.add(rightButton);
        
        surveyPanel.add(buttonPanel);
        jf.add(surveyPanel);
        jf.setVisible(true);
        
        timer = new Timer();
        timer.start();
    }
}


class Question{
    String questionText;
    String leftText;
    String rightText;
    
    Question(String ques, String left, String right){
        questionText = ques;
        leftText = left;
        rightText = right;
    }
    
    Question(String ques){
        this(ques, "True", "False");
    }
}

class ButtonListener implements ActionListener{    
    @Override
    public void actionPerformed(ActionEvent e){
        if (Midterm.leftButton.equals((JButton)e.getSource())){
            Midterm.responses[Midterm.quesNum] = Midterm.questions[Midterm.quesNum].leftText;
        }
        else{
            Midterm.responses[Midterm.quesNum] = Midterm.questions[Midterm.quesNum].rightText;
        }
        Midterm.quesNum++;
        
        Midterm.timer.buttonClicked = true;
        
        if (Midterm.quesNum < Midterm.questions.length){
            Midterm.question.setText(Midterm.questions[Midterm.quesNum].questionText);
            Midterm.leftButton.setText(Midterm.questions[Midterm.quesNum].leftText);
            Midterm.rightButton.setText(Midterm.questions[Midterm.quesNum].rightText);
            Midterm.timer = new Timer();
            Midterm.timer.start();
        }
        else{
            String surveyResponse = "";
            for (int i = 0; i < Midterm.responses.length; i++){
                surveyResponse += Midterm.responses[i] + ", ";
            }
            Midterm.question.setText(surveyResponse);
            Midterm.leftButton.setEnabled(false);
            Midterm.rightButton.setEnabled(false);
        }
    }
}

class Timer extends Thread{
    boolean buttonClicked = false;
    
    @Override
    public void run(){
        try{
            sleep(5000);
            if (!buttonClicked){
                String surveyResponse = "";
                for (int i = 0; i < Midterm.responses.length; i++){
                    surveyResponse += Midterm.responses[i] + ", ";
                }
                Midterm.question.setText(surveyResponse);
                Midterm.leftButton.setEnabled(false);
                Midterm.rightButton.setEnabled(false);
            }
        }
        catch (InterruptedException ex) {}
    }
}