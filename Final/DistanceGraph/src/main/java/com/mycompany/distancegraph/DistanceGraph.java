/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.distancegraph;

/**
 *
 * @author cindylee
 */

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.text.DecimalFormat;

public class DistanceGraph {
    public static void main(String[] args){
        JFrame jf = new JFrame("Graph Q1");
        jf.setSize(500, 500);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        DrawingPanel grid = new DrawingPanel();
        jf.add(grid);
        grid.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent m){
                if (m.getButton() == MouseEvent.BUTTON1){
                    grid.addPoint(m.getX(), m.getY());
                    grid.repaint();
                }
                else{
                    grid.clearList();
                }
            }
        });
        jf.setVisible(true);
    }
}

class DrawingPanel extends JPanel{
    ArrayList<MyPoint> points;
    final int size = 5;
    
    DrawingPanel(){
        super();
        points = new ArrayList<MyPoint>();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
        g.setColor(Color.RED);
        g.setFont(new Font("Arial Bold", Font.PLAIN, 13));
        NumberFormat formatter = new DecimalFormat("#0.00");
        for (int index = 0; index < points.size(); index++){
            MyPoint p = points.get(index);
            g.fillOval(p.x-(size/2), p.y-(size/2), size, size);
            g.drawString("(" + p.x + ", " + p.y+ ")", p.x, p.y + 13);
            if (index > 0){
                MyPoint prior = points.get(index - 1);
                g.drawLine(prior.x, prior.y, p.x, p.y);
                double distance = p.distance(prior);
                int midpointX = (p.x + prior.x) / 2;
                int midpointY = (p.y + prior.y) / 2;
                g.drawString(formatter.format(distance), midpointX, midpointY);
            }
        }
    }
    
    void drawGrid(Graphics g){
        int height = this.getSize().height;
        int width = this.getSize().width;
        g.setColor(Color.GRAY);
        for (int xPos = 0; xPos < width; xPos += width/10){
            g.drawLine(xPos, 0, xPos, height);
        }
        for (int yPos = 0; yPos < height; yPos += height / 10){
            g.drawLine(0, yPos, width, yPos);
        }
    }
    
    void addPoint(int x, int y){
        points.add(new MyPoint(x, y));
    }
    
    void clearList(){
        points.clear();
        this.repaint();
    }
}

class MyPoint{
    int x;
    int y; 
    
    MyPoint(int xcoord, int ycoord){
        x = xcoord;
        y = ycoord;
    }
    
    double distance(MyPoint otherPoint){
        return Math.sqrt(Math.pow(x - otherPoint.x, 2) + Math.pow(y - otherPoint.y, 2));
    }
}