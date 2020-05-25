// background color on button
// setopaque -- see through the button
// random numbers to create random colors 
// button listener -- actionlistener 
// create  button listener object 
// add action listener on the button for the object that you created
// button.addactionlistener (object itself)
// action performed function parameter -- actionevent -- can create a different action listener 
// tie each one to the button 
// entire hw - created only one action listener object
// actionevent that passed in as parameter -- get source on action event 
// get source returns an object -- can be typed cast to something 
// in scale -- own action listener or do 1 action listener

// color class - color constructor can take 3 ints between 0 and 255
// random function can get you a random int 
// BUTTONS SHOULD NOT HAVE LABELS

// one button listner get source action 



package com.mycompany.csuy3913spring2020classes;

import java.awt.*;
import javax.swing.*;

public class csuy3913pring2020classes{
    // int w; 
    public static void main(String[] args){
        A a =  new A();
        a.myfunc();
        a.C item = new A.C();
        // can always create a static item even if its inside of the instance class

        // on another profs midterm -- change value of w to 100 
        // w = 100
        // can't access from a static mainn!
        // correct answer: 
        // csuy3913spring2020classes var = new csuy3913spring2020classes();
        // var.w = 100;
        // nothings stopping main from creating an object of your own class
        // has access to own instance variables of own class then


        JButton jb = new JButton("PRESS ME");
        jb.addactionlistener(new ActionListener(){
            public void actionPerformed (ActionEvent e){
                b = (JButton) e.getSource();
                b.setText("I was pressed!");
                // System.out.println("I was pressed");
            }
        });
        // can derive from action listener class (not a class, its just derived from actionlistener class)

    }
}

class A{
    private int x; 
    static int y; 
    B thing;

    A() {
        x = 0;
    }

    static class C{ // static 
        // alwaays going to be available
        A myA;
        C(A newa) {
            y = 100; 
            myA = newA;
        }
    }
    // iterator can be staticly independent of B for the vector
    

    class B{ // inner class
        int s ; 
        final static int t = 100; 
        B(){
            x++ ;
            // x is accessible because is nested
            System.out.println(y);
        }
    }


    B getB() {
        return new B();
    }

    public static void otherFunc(){
        // otherfunc is a static func -- cant access x 
        // can access y
        // B b = new B();
        // DONT DO THIS 
        // B ACCESSES X BUT OTHERFUNC DOESNT 
        // CAN BE CALLED WITHOUT HAVING INSTANTIATED ONE OF THESE OBJECTS
        // NO X'S ANYWHERE IN SYSTEM
        // CAN HAVE STATIC FUNCTION STATIC VARIABLE 
        // B object -- granted access to nested functions and instance variables to parents class
        // static function cannot access a nonstatic class

        // create an instance of an A to then access the instance of b 
        A a = new A();
        B b = a.getB();
    }

    void myfunc(){
        B b;
        int var = 10;
        for (int i = 0; i < 100; i++){
            b = new B();
        }
        System.out.println(x);
        class D{ // local inner class
            public void innerfunc(){
                System.out.println ("inside the function myfunc");
                System.out.println("Var="+ var);
            }
        }
        D d - new D();
        d.innerfunc();
        /// this works 
        // D has access to var
        // cant have class name beginning with integer

        // technically dont need to give d a name 

    }

    abstract class Thing{
        abstract void thingFunc();
    }

    void thirdFunc(){
        int var = 30;
        Thing t; 
        t = new Thing(){
            void thingFunc(){
                System.out.println("Im a thing");
                System.out.println("Var" + var);
            }
        }; // impossible to create object of an abstract class
        t.thingFunc(();
        // what is being created?
        // class that has no name 
        // class derived from a base class yet has no name 
        // created for a single use, used and then thrown away 
        // anonymous class (official name of it)
        // base class is Thing

        new Thing(){
            void thingFunc(){
                System.out.println("I am also a thing");
            }
        }.thingFunc();
        // never need a variable and dont need to give the thing a name
        // use this to derive from an actionlistener

    }
}

class Z {
    class B {}
    class C{}
}

// quick class that you iwll ever use in a single function 


//action listener is an interface 
// 

class Vehicle implements Refuelable{
    double tankCapacity;
    double currentFuelLevel;
    double tankCapacity() {return tank;}
    double getFuelLevel(){return currentFuelLevel;}
    void addGas(double gallons){currentFuelLevel+=gallons;}
}

interface Refuelable{
    double tankCapacity();
    double getFuelLevel();
    void addGas(double gallons);
}

interface Crashable{
    void crash();
}

interface Stealable{
    void steal();
}

// use commas to implement several interfaces
class Car extends Vehicle implements Crashable, Stealable{
    public void crash(){System.out.println("ouch");}
    public void steal(){System.out.println("Damn boy, it's GONEEEEEE")}
}


class Trunk extends Vehicle{

}

class ChainSaw implements Refuelable{
    @Override
    public double tankCapacity(){
        
    }
}

// can extend only one class 
// can implement as many interfaces needed

class Test{
    void myfunc(){
        Car c = new Car();
        GasStation g - new GasStation();
        g.fillErUp(c);
    }
}

class GasStation{
    public double fillErUp(Refuelable e){
        double tankCapacity = e.tankCapacity();
        int currentFuelLevel = e.getFuelLevel();
        double added = tankCapacity-currentFuelLevel;
        e.addGas(added);
        return added;
    }
}


// operating systems lesson
/*

multiple copies of a program 
OS cant track program by name -- needs more info -- needs to track via process
double click chrome -- process identifier generated -- keep track of process with other componenst


imagine just 1 cpu 
how to run two versions of chrome (copies) when we really have one cpu 
in all reality theres 100-200 processes running on system right now 

cpu switching between processes quickly
not all processes need to run in any given second 
some of them are sitting there doing nothing

some programs that are running that want to do their job
OS - switches between those running processes very quickly

realize that some of them are trying to run
running state
not ready to run -- waiting -- blocked (needs something to run)
ready, blocked, running - states

no one can occupy the cpu for longer than we allow them to - theres a time limit (preemption)
even if you want to continue running perpetually we will sttop you 
the whole computer has 1 cpu, if we allow you to run for 20 ms, after 20 the process will be stopped, another processs will be allowed to run

what does a process do 

microsoft word - standard processor - has tasks to do 
take in key strokes
displaying the document on the screen, going in and checking words and grammar etc 

every 10 min , word auto saves document 
code runs in lock step - function call has to end before coming back to running that piece of code
call function from main - until function ends 

process some data bring it in 
output results
word has parallel tasks that are waiting for key strokes, updating etc saving every 10 min
not dependent on one another -- key stroke function isnt dependent on grammar function, etc
run continuously -- concept of threads -- units of execution inside a program
main started running -- calls other functions, call other functions, all of it done - main is done and program is done
// clear single thread through program coding 

but now come up with idea to do parallel tasks - kick off another thread and let thread run in parallel with my thread
there's only one cpu - so nothings running literally in parallel
what cpu doing is switching between threads 

can have as many threads of execution of a process as i want 
done in parallel versus original one 
fire up jframe window and ended -- there were threads maintaining jframe window
threads to manage window
press button -- code from actionlistener being run -- in a thread

press a button new thread created, run listener function, function and thread ends
thread easy to create and destroy -- all share memory associated with process/program 

create new thread -- not allocating much memory for it -- not like starting a new program 
codes already been loaded 

start a new thread -- tell java -- where to begin execution of that thread -- thread begins running and return immediately back to main 
main continues runnning before thread finished running 

DOWNSIDES
all of the threads use the same memory 
most potentially dangerous
use same memory 
whatever objects are created when thread was started -- still there and accessible by thread


*/