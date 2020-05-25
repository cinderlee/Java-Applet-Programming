/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.homework5;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author cindylee
 */
public class Homework5 {

    static ClockPanel clock;

    public static void main(String[] args) {
        JFrame jf = new JFrame("Homework 5");
        jf.setSize(500, 500);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clock = new ClockPanel(500 / 4);
        jf.add(clock);
        clock.fetchTime();
        // The default time is set to 0:00:00 in case the first call of fetchTime 
        // fails from not receiving a response back from the server (possibly due to poor internet connection)
        new UpdateTime(System.currentTimeMillis()).start();
        jf.setVisible(true);
    }
}

class ClockPanel extends JPanel {
    int radius;
    int hour = 0;
    int minute = 0;
    int second = 0;
    boolean recalibrated = false;

    ClockPanel(int r) {
        super();
        radius = r;
    }

    @Override
    protected void paintComponent(Graphics g) {
        // fixing the center point's value if offsetted
        int centerX = this.getSize().width/2;
        int centerY = this.getSize().height/2;
        
        // drawing the clock base
        g.setColor(Color.BLACK);
        g.drawOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
        g.setColor(Color.WHITE);
        g.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
        g.setColor(Color.BLACK);
        
        // labeling the hours on the clock
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        for (int i = 1; i <= 12; i++) {
            double hourTextAngle = (i % 12) * 360 / 12;
            int textLocX = getX(centerX, hourTextAngle, radius - 20);
            int textLocY = getY(centerY, hourTextAngle, radius - 20);
            String num = "" + i;
            textLocX -= g.getFontMetrics().stringWidth(num) / 2;
            g.drawString(num, textLocX, textLocY + g.getFontMetrics().getHeight() / 4);
        }
        
        // drawing the ticks on the clock 
        for (int i = 1; i <= 60; i++) {
            double markAngle = (i % 60) * 360 / 60;
            int startX = getX(centerX, markAngle, radius - 5);
            int endX = getX(centerX, markAngle, radius);
            int startY = getY(centerY, markAngle, radius - 5);
            int endY = getY(centerY, markAngle, radius);
            g.drawLine(startX, startY, endX, endY);
        }
        
        // drawing the hour hand
        g.setColor(Color.BLACK);
        double hourAngle = ((hour % 12) * (360 / 12)) + ((minute / 60.0) * (360 / 12));
        g.drawLine(centerX, centerY, getX(centerX, hourAngle, radius * 0.5), getY(centerY, hourAngle, radius * 0.5));

        // drawing the minute hand
        double minuteAngle = minute * (360 / 60) + ((second / 60.0) * (360 / 60));
        g.drawLine(centerX, centerY, getX(centerX, minuteAngle, radius * 0.75), getY(centerY, minuteAngle, radius * 0.75));

        // drawing the second hand
        g.setColor(Color.RED);
        double secondAngle = second * 360 / 60;
        g.drawLine(centerX, centerY, getX(centerX, secondAngle, radius), getY(centerY, secondAngle, radius));

        // drawing a dot for the center of the clock
        g.setColor(Color.BLACK);
        g.fillOval(centerX - 5, centerY - 5, 10, 10);
    }
    
    int getX(int centerXCoord, double angle, double rad){
        return centerXCoord + (int) (rad * Math.cos(Math.toRadians(90 - angle)));
    }
    
    int getY(int centerYCoord, double angle, double rad){
        return centerYCoord - (int) (rad * Math.sin(Math.toRadians(90 - angle)));
    }

    void fetchTime() {
        try {
            Socket s = new Socket("time-a-g.nist.gov", 13);
            if (s.isConnected()){
                Scanner sin = new Scanner(s.getInputStream());
                String timeString = "";
                while (sin.hasNext()) {
                    timeString = sin.nextLine();
                }
                // check if the server gave back a response 
                // sometimes the server doesn't give back a time 
                // but when called again the next minute, it does
                if (!timeString.equals("")){
                    parseTimeString(timeString);
                    recalibrated = true;
                }
                else{
                    recalibrated = false;
                }
            }
        } catch (IOException ex) {
        }
    }

    void parseTimeString(String timeString) {
        // parse the inputted string for the time
        // stores new time values in hour, minute, and second
        // example of input from server: 58973 20-05-04 00:54:43 50 0 0   7.7 UTC(NIST) *
        String time = timeString.split(" ")[2];
        String[] timeStringPart = time.split(":");
        int hourIndex = 0;
        int minuteIndex = 1;
        int secondIndex = 2;
        
        hour = Integer.parseInt(timeStringPart[hourIndex]);
        minute = Integer.parseInt(timeStringPart[minuteIndex]);
        second = Integer.parseInt(timeStringPart[secondIndex]);
    }
}

class UpdateTime extends Thread {

    long startTime;

    UpdateTime(long timeMilli) {
        startTime = timeMilli;
    }

    @Override
    public void run() {
        while (true) {
            long currentTime = System.currentTimeMillis();
            
            // if it's been a minute -- fetch the time 
            if (currentTime - startTime >= 60000) {
                Homework5.clock.fetchTime();
                startTime = currentTime;
                Homework5.clock.repaint();
            }
            
            // if clock was recalibrated with the time fetched
            if (Homework5.clock.recalibrated){
                Homework5.clock.recalibrated = false;
                try {
                    sleep(1000);
                } catch (InterruptedException ex) {
                }
            }
            // if it hasn't been a minute yet or empty response from the time site
            // update the time by 1 second
            // make sure to update minutes and hours in case seconds go over 59
            else{
                Homework5.clock.second += 1;
                Homework5.clock.minute += Homework5.clock.second / 60;
                Homework5.clock.second = Homework5.clock.second % 60;
                Homework5.clock.hour += Homework5.clock.minute / 60;
                Homework5.clock.minute = Homework5.clock.minute % 60;
                Homework5.clock.hour = Homework5.clock.hour % 24;
                Homework5.clock.repaint();
                try {
                    sleep(1000);
                } catch (InterruptedException ex) {
                }
            }
        }
    }
}
