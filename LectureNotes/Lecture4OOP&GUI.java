package com.mycompany.csuy3913spring2020window1;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CSUY3913Spring2020Window1{
    public static void main(String[] args){
        Base b = new Derived();
        b.someFunc();
        // which version of someFunc going to be called
        // derived version of some func will be used 
        Derived.s = 100;
        System.out.println("S="+Base.s);
        //derived has access to s - static - but it only exists inside base class class

        // b is a base class pointer but no access to derived stuff

        // can't call b.anotherFunc()

        // java not smart enough to know that b reference points to derived class object

        // stored ref of derived object into base class pointer
        // can't make a derived class pointer point to base class object

        Derived d; 
        d = (Derived) b;
        d.anotherFunc();
        // with typecasting in java -- can cast a b reference to derived 
        // if the base isnt truly reference a derived object then there will be a big error 
        // if base reference not actually referencign a derived object 
        // big error 
        // if base pointer is referencing derived object -- nothing wrong with copying reference
        // validity of object calls
        // can't say d = b 
    }
}

// Base is a class
// val is stored in oject of the base class
// is stored inside the instance 

// base class of Base is Object (java.lang.Object)
// object is the base class of everything except PRIMITIVES - integer, float, bool, char
// however it is the base class of the class equivalent of primitives

class Base{
    int val;
    static int s;

    Base(){
        // val = 100; NOT ALLOWED -- CAN'T HAVE CODE BEFORE THIS()
        this(0);
        // anything that is this must be the first line of code
        // can't have any operations in constructor prior to this 
        // so can't do val = 100 before
        // call to 1 argument constructor
        // default constructor
    }

    Base(int newval) {
        val = newval;
    }

    public void someFunc(){
        System.out.println(val);
    }
}

class Derived extends Base{
    // java threw out private inheritance (no one really does it )
    
    // diammond inheritance - avoid 
    // huge problem is c++
    // when you have a base class like Person -- you might use that base class as the base class of two other classes like students and employees
    // both of them deriving from person -- problem when u have a student who also works for the company/school
    // another class that derives from both student and employee
    // ends up creating 2 people in c++ -- base class object of student and base class object of employee
    // not clear which people you are working iin 
    // name -- student name or employee name o_O there are two names despite being in one object


    // java solution -- dont do it just dont do it 
    // java - can ONLY inherit from one base class -- 1 and only 1 
    // cannot derive from more than one base class

    // however be extension its derives from Object (indirectly)
    // 

    double d;
    Derived() {
        // get derived to call base class constructor
        // option of invoking an alternate constructor in base class
        // this calls sibling constructor 
        // but we want to call base 
        // dont need to list which class instructor being invoked since only one base
        super(90); // call to Base's 1 argument constructor
        d = 5.5;

        // but no chaining of supers!
    }
    Derived (){
        this(42, 5.5);
    }

    Derived (int newval){
        super(newval);
        d = 5.5;
    }

    Derived(int newval, double newd){
        super(newval);
        d = newd;
    }

    Derived(int newval){
        this(newval, 5.5)
    }

    @Override
    public void someFunc() {

        // call super's someFunc
        super.someFunc();
        // System.out.println("Val-"+val); -- part of super's
        System.out.println("D="+d);
        // val is package private
    }

    public void anotherFunc(){
        System.out.println("This is another func!");
    }
    // only in derived
}

/*

Classes and extended classes

some class that derives from another class
polymorphism

java - everything virtual

default parameters  -- can't do that anymore 

you can't use default parameters in java -- doesn't exists

no default parameters!!!!! 

no default construction of a base object -- can't falsely create a default object
must provide a value for newval 

constructor that takes one argument -- no way to default create a base object

what if we want a default constructor and wants to be lazy 


want default constructor to call another constructor to reuse code 

technically a constructor is a function and should be a way to call the function 

RULES


*/


// start to procedure -> next point -> follow points 

// event driven program - environmetn to set up from code
// user chooses what piece of code runs next 
// up to this point was procedural

// set up enviornment
// wait for event to happen
// deal with event 




// how does java create windows 
// platform independent -- java creates its own platform
// creating a window is not simple in platform dependent 

// awt toolkit 


// jframe inside swing component
// derives from awt class called frame 
// 


public class {
    static JLabel text;

    public static void main(String [] args){
        text = new JLabel("2")
        JFrame jf = new JFrame("My First frame! :) ");
        jf.setDefaultCloseOperations(JFrame.EXIT_ON_CLOSE);
        jf.setSize(400, 400);
        jf.setVisible(true);
        // x- button means wndow is done 
        // still runing in background 
        // stops only when u force kll 
        JButton jb = new JButton = ("PRESS ME, please");
        jb.addActionListener(new BustonListener());


        JPanel jp = new  JPanel();
        jp.setLayout(new GridLayout(3, 3, 4, 4));
        // 4 pixels between everything 
        jp.add(new JLabel("1", SwingConstants.CENTER));
        jp.add(text);
        // jp.add(new JLabel("2"));
        jp.add(new JLabel("3", SwingConstants.CENTER));

        // with jlabels you can use html if you want 
        jp.add(new JLabel("<HTML><center>left</center></HTML>"));
        JPanel jp2 = new JPanel();
        jp2.setLayout(new BorderLayout());
        jp2.setBackground(Color.red)

        
        jp2.add(new JLabel("North"), BorderLayout.NORTH);
        jp2.add(new JLabel("South"), BorderLayout.SOUTH);
        jp2.add(new JLabel("East"), BorderLayout.EAST);
        jp2.add(new JLabel("West"), BorderLayout.WEST);
        jp2.add (jb, BorderLayout.CENTER)
        jp.add(jp2);
        // jp.add(jb);
        jp.add(new JLabel("right"));

        jp.add(new JLabel("7", SwingConstants.CENTER));
        jp.add(new JLabel("8", SwingConstants.CENTER));
        jp.add(new JLabel("9", SwingConstants.CENTER));

        jf.add(jp);
        jf.setVisible(true);
        return ; 
    }
    // running main ended returned
    // but frame is still running
    // main is a stub -- sets up ends after finishing setting up environment
    // main's job is to set up the windows, set up the buttons, set up the ways the event handlers users will interact with program after main is gone 

    // Jpanel button etc -- container objects 
    // add button to panell or frame -- occupies the full space given 
    // layouts
    

    // put jpanel on jframe -- jpanel is container holds the other properties
    // can hodl textboxes another panel, buttons
    // fill space of what is offered
    // sequential layout (object gets added next to an object next to an object etc )


    // if you dont want sequential layout -- can have a grid layout 
    // allows you to define (rows, cols, hspace, vspace)
    // still fill the space from top left to bottom right (look at documentation )
    // can create a little better environment for rows cols hgap and vgpa
    // 

    // border layout environemnt
    // 
}

class ButtonListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvnt arg0){
        CSUY3913Spring2020Window1.val += 1;
        CSUY3913Spring2020Window1.text.setTest ("" + CSUY3913Spring2020Window1.val);

    }
}


package com.mycompany.csuy3913spring2020window2; 

public class MyClass{
    public static int main (String[] args){
        Jframe jf = new JFrame("Mw second application!")
    }
}