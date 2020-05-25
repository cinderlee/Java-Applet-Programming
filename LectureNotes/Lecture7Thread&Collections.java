public class CS3913Spring2020Thread2 {
    public static void main(String[] args){
        Print.o = new Object();
        for (int i = 0; i < 5; i++){
            new Print(i).start();
        }
    }
}

class Print extends Thread{
    int id; 
    // static Object o; 

    Print(int newid){
        id = newid;
    }

    public void run(){
        for (int i = 0; i < 1000; i++){
            // synchronize (this)
            // two diff objects -- then synchronize doesnt prohibit us from accessing a synchronized thing
            // two threads on two diff objects are not prohibited from working simultaneously
            // two threads on the same object won't have problem synchronizing 
            // one will be paused the other will continue to run 
            // problem is there are 5 diff print objects created 
            // only useful if we have more functions that needed to be synced on the same object 
            // instead SYNC ON A SHARED OBJECT
            // synchronize(o);

            printline(id);
            // for (int j = 0; j < id; j++){
            //     System.out.println("\t");
            // }
            // System.out.println(id);
        }
    }

    // sync the class object itself! 
    // of type class 
    // calls wait on type class object called Print 
    static synchronized void printline(int id){
        // implict call to Class.wait()
        // doesnt have access to id because its static (id is instance )
        //Static synchronized means we're using the class as a semaphore!
        for (int j = 0; j < id; j++){
            System.out.print("\t")
        }
        System.out.println(id);
        // implicit call to Class.notify();
    }
}

/*

From last time, output was disjointed

threads are dynamically started and stopped and random intervals, dont have control over when the threads start, paused (block state), or when thread is stopped (done)

Print can print number of tab characters -- but interrupted before it can print its own id

// A   1
// B   B   2
// A   B   B   2

Solution in OS -- need some sort of lock or notifcation system to allow
lock to prevent anyone else from printing to screen when printing tab character but have yet to print id 

mutually exclusive lock -- mutex 
Data type - semaphore

A semaphore - simply a flag/signal/notification mechanism 
Having a queue of threads that are waiting for the semaphore
Number of available signals
- signals that are sent on the semaphore - beep 
When youre using a semaphore -- need to recognize what are critical sections
portion of code that have to run automatically
once started -- have to be allowed to finish 
either you can't be interrupted at all 
or must continue before anyone else can run 
agreement between all of the threads that are passing messages via the semaphore
change to a shared variable or resource -- that we are going to wait for a signal on the sempahore before proceeding to make any changes 

printing to the screen and possibility chance of interruption 
interrupted potentially -- really can't let that happen
can't allow interruption ot occur 
can't allow any other thread to print to screen before finish printing with id 
need ot be able to print 1 line - not 1000 before being interrupted 


Interrupt can occur at any point in the situation in for loop 
Utilize a semaphore to wait to request a signal 
a wait operation is a blocking system call that will not proceed until a signal has been sent 
Start by sending a signal - then the first thread that wants to enter its CR sends wait and consumes it signal
no signals remaining to be consumed
any other thread that calls wait -- waiting for signal to be sent -- will be blocked (what the queue is for)
once first thread finished critcial sectin -- send signal -- releasing next thread in the sequence 

semaphore keeps a count (positive) of how many signals are unconsumed 
can have a negative count if queue has any remaining threads on it 
threads each of them wil decrement hte counter -- check if counter is a negative number 
if is a negative number -- goes onto the queue 

signal and wait functions in java 

*/


/*
Producer-Consumer Problem
 */


 public class CS3224Fall2014Thread2


 class Buffer {
     static boolean fail = false;
     final int MAX_SIZE = 100;
     ArrayList <Integer> ar; 


     
 }

 synchronized on the buffer object -- not on class 

 only one buffer - need to make sure not have two producers adding ot buffer at same time 

//  thread code - wind blowing in wrong direction 
// have to consider concurrency immediately 

/** 
Storage and storage mechanisms

We've been working with static arrays
mechanisms for storage: there are a set of java containers that we can use to store things 
covered this in c++, gotten the idea of how the stl classes work
close to the same idea, except we call them collections in java
probably familiar with an ArrayList
store items in array ever increasing
.add (to end of list )
.get to retrieve element from list, cant use [] to overload
isEmpty()
iterator()
Sounds like a vector in c++

*/

import java.util.ArrayList;
import java.util.Iterator;

public class GeneratorClass{
    public static void main(String[] args){
        ArrayList al = new ArrayList();
        // we store objects in an ArrayList 

        for (int i = 0; i < 5; i++){
            al.add(new Print(i)); // autobox into Integer
            // can't add integers to arraylist 
        }

        for (int i = 0; i < 10; i++){
            al.add(i); // autobox into Integer
            // can't add integers to arraylist 
        }

        for (int i = 0 ; i < al.size(); i++){
            // because all of the elements in the arrayList are objects
            // we need to typecast to Print object
            Print temp = (Print)al.get(i);
            // al.get(i).start()
            temp.start();
        }

        for (Object o: al){
            Print temp = (Print) o;
            temp.start();
        }

        /*
        USE A GENERIC
        C++ - templates, store any data type in the collection generically 
        The things in the <> are the generics in Java
        */

        ArrayList<Print> al = new ArrayList<Print>();
        for (int i = 0; i < 5; i ++){
            al.add(new Print(i));
        }

        for (Print p: al){
            p.start();
        }

        // iterators are built into the class 

        Iterator<Print> i = al.iterator();
        while (i.hasNext()) {// as long as anything remains to iterate over
            i.next().start() // get the next element and advance the iterator 
        }

        // CANNOT STORE PRIMITIVES IN AN ARRAYLIST/COLLECTION CLASSES! MUST BE AN OBJECT
        // CAN STORE THE WRAPPER CLASS VERSION OF THE PRIMITIVE
    }
}

/*
Collection Classes that we have
- ArrayList
- LinkedList (Doubly)
    - can get first, get last
    - remove 
- HashSet (HashTable)
- HashMap (Dictionary in Python and Map in C++)
    - key is hashed and gets a value
- TreeSet
- LinkedHashMap
- TreeMap
- PriorityQueue

can create these generically
*/