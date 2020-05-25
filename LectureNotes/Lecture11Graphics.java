// create a panel to draw 

package cs3913spring2020graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CS3913 Spring2020Graphics{
    public static void main(String[] args){
        JFrame jf = new JFrame("My Window");
        jf.setSize(400, 400);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // MyPanel mp = new MyPanel();
        DrawingPanel mp = new DrawingPanel();
        jf.add(mp);
        mp.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent m){
                if (m.getButton() == MouseEvent.BUTTON1){
                    MyPoint p = new MyPoint(m.getX(), m.getY());
                    mp.addPoint(p);
                    mp.repaint();
                }
                if (m.getButton() == MouseEvent.BUTTON2){
                    mp.clearList();
                }
            }
        });
        jf.setVisible(true);

        // for (int i = 0; i < 1000000; i++){
        //     try{
        //         mp.repaint();
        //         // dont have the graphic object to call paintcomponent
        //         // but you can repaint
        //         Thread.currentThread().sleep(1000);

        //     }
        //     catch(Exception e){}
        // }
    }
}

class MyPanel extends JPanel{
    int i; 
    MyPanel(){
        super();
        i = 0;
    }

    protected void paintComponent(Graphics g){
        i++; // how many times is this run?

        // this is from the JComponent class of which JPanel derives fromm
        // graphics area is the area where you paint 
        // shouldnt paint outside the specified area 
        int height = this.getSize().height;
        int width = this.getSize().width;
        // height of a panel is not fixed

        g.setColor(Color.GRAY);
        g.fillRect(0, 0, width, height);
        
        // think of it like a pen 
        // can change the pen im using (pick up the pen)
        g.setColor(Color.RED);
        g.fillRect(width/2 - 50, height/2, 100, 100);
        // starts drawing the rectangle upper left corner as the coordinate 
        // and then expands to the right
        
        // g.setColor(Color.GREEN);
        g.setColor(new Color(34,139, 34));
        g.setFont(new Font("Arial Bold", PLAIN, 40));
        g.drawString("i=" + i, width/2 - 100, height/2 - 2)/
        // g.drawString("Hello World", width/2 - 100, height/2 - 2);
        // g.drawString("Hello World", width/2, height/2);
        // g.drawString("Hello World", 0, 0); // draws nothing -- where is this 0,0 position?
        // 0, 0 position is upper left corner - drawString uses coordinate as bottom left hand corner of the drawing string 
        // draws above 0,0 
        // center point is bottom left hand corner of where you are writing the string
        // printing to upper right of the point

        // if you show the two above hello World is drawn above the rectangle (both share the same coordinate though)
    }
}

// draw a string - drawing not at the center point 
// will draw to the right 

// edge coordinates : 
// (0, 0)                       (width, 0)




// (0, height)                  (width, height)


// dragging 
// paint component is run every time a change occurs to the screen/window
// so it drastically increases how many times its run when its dragged
// minimize, maximize = 1 
// redraws panel every time window changes 


class DrawingPanel extends JPanel{
    ArrayList<MyPoint> al;
    final int size = 20;
    DrawingPanel(){
        super();
        al = new ArrayList<Point>();
    }

    void addPoint(MyPoint p){al.add(p);}

    void clearList(){
        al.clear;
        this.repaint();
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g); // to let it clean up itself
        g.setColor(Color.RED);
        for (Point p: al){
            g.fillOval(p.x-(size/2), p.y-(size/2), size, size);
        }
    }
}

// drawing circle uses upper left hand corner

class MyPoint{
    int x;
    int y;
    MyPoint(int newx, int newy){x = newx, y = newy;}
    MyPoint() {this(0,0);}
}

// hw
// thread - wait for x millisecond (not really accurate)
// doesnt perform accurately but we want to accurately portray the analog clock
// sleep (1000) really means sleep at least 1000

