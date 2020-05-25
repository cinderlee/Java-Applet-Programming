package com.mycompany.cs3913spring2020thread1;

public class CS3913Spring2020Thread1{
    public static void main(String[] args){

        for (int i = 0; i < 5; i++){
            new Printer(i).start();
            new Thread (new B(i));
        }


        // Thread t = new MyThread();
        // t.start();
        // System.out.println("Thread started....");
        
        // for (int i = 0; i < 100; i++){
        //     System.out.println("Main=" + i);
        // }
        // System.out.println("Main is done....");
    }
    // each time the thread ran is totally random
    // we dont have control 
    // processor does

}

class MyThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++){
            System.out.println("Thread="+ 1);
        }
    }
}

class A{

}

// still a thread

//currentt thread function allows you to get the thread that is curerntly running
class B extends A implements Runnable{
    int id;
    B(int newid){
        id = newid;
    }
    public void run(){
        for (int i = 0; i < 100; i ++){
            System.out.println(id);
            Thread.currentThread().yield();
        }
        // cant yield as easly 

    }
}

class Printer extends Thread{
    int myId; 
    Printer(int id){
        myId = id;
    }

    public void run(){
        for (int i = 0; i < 1000 ; i++){
            for (int j = 0; j < myId; j++){
                System.out.println("\t"); // tabs don't really line up -- really its because the threads are being blocked at random times so the numbers don't line up 
            }
            // contention for a resource
            // multiple threads access the same stuff - 1 screen, 5 threads


            System.out.println(myId);
            // yield(); // stops for a moment to see who needs to run, if someone with higher priority of someone that wants to go, that one will run

            // try {
            //     sleep(200);
            // }catcb(Interrupted Exception ex){} // can be interestupted fromm its sleep cycle by another thread
            // // the thread never owns its resources -- can use, but never truly owns them
            // // can have concurrency control issues
            // sleep 1000 <_ MINIMUM IS 1000
        }
    }
}

// interface -- abstract class where all the functions are abstract 
// need to implement all of the functions associated with interfaces
// implementing interface -- can do polymorphism

// multiple threads inside same process 
// has a task that its performing 
// without process - no threads

// Thread class -- function tthat needs to be overwritten -- run 
// never call thread's run 
// main for your thread

// threads 

// want main to start a new thread -- and go back to what it was doing before

 // call startt instrad -- will call run 
 // if you call run -- thread calling run is calling run -- so only one thread
 // start -- second thread 

// OS short term scheduling algorithm of what to run next
// operating system sets a timer to go off and allows the thread to run -- when timer goes off, thread is preempted
// preemption -- take thread off despite it not being done with its work
// check to see -- if this is gonna take a while, task you ask for is gonna take a long whiel
// make a function call and that function call is gonna take a really long tme 
// don't let your code continue to execute, block thread -- blocking system call 
// instead of thread being ready to run -- blocked state
// blocked state -- don't have everything to run 
// set timeer to go to sleep -- thread is blocked for that period of time 
// timer expired -- OS can't run your thread immediately 
// thread -- goes to ready state -- ready to be run -- but cpu not yet available in case of 
// ready -- everything you need to go to cpu
// blocked -- don't have everything you need
// running 

// move back to ready state when preempted 
// lag between  when you get to ready state and when you are actually running 
// 


// interrupted in sleep 


// midterm 

// 6 years ago midterm 
// fix the code 
// fill in blank -- whats missing 
// each thread has its own timer
// can access static nested class without instantiating parent class <- benefit of static nested class
// static function -- inside a class, only has access to static items
// if static inside the class -- access only to static stuff inside the class
// same for function or whole class
// static function - only have static

// throws ____ if you throw an exception