public class MyClass{
    public static void main (String args[]){
        System.out.println("Hello World!")
    }
}

/*

Java mandates that a public class be put into a file with the same name as the name of the class. 
So this file needs to be called MyClass.java

Run: javac MyClass.java
Creates a file called MyClass.class -- produced byte code 

Run: java MyClass
Prints out Hello World!


*/

/*
Steps in NetBeans

1. Create a new Project - Do not put spaces in the name for the project
2. Go to Source Packages and Open a Java Class -- give it the same name as Project Name
*/

public class CSUYSpring2020Intro1{
    // psvm tab
    public static void main(String[] args){
        System.out.println("Hello World!");

        double d = 5/2;
        System.out.print("5/2=");
        System.out.println(d);
        // d is regardless a 2.0 despite it being a double because an integer / integer = integer
        // need to tell variables what data types they are -- not like in Python
        int x = 2.5;
        // line above has an error -- incompatible types because of loss of conoversion from double to int

        // typecast because saying you understand there will be loss of precision from larger domain to smaller domain
        int x = (int)2.5;

        // int float double char
        // semicolons are mandatory

        d = 5.;
        d = 5d;

        String s; // this is a class -- capitalized
        // this doesnt create a string at all, this is just a reference 
        // creating storage that can reference a string 
        // didn't initialize string 

        String s = new String("Hello World!"); // explicitly states

        String s = "Hello World!"; // implies using constructor

        // Java has a garbage collector -- don't need to worry about what we create on heap 
        // objects get deleted automatically 
        // periodically JRE will pause your program and looks through every variable that exists 
        // checks off whether the variable is still accessible -- deallocates any unticked variable
        // impossible to have a memory leak in a Java program 
        System.out.print(s);

        System.out.println("Hello World " + "Daniel" + 5); 
        /* Not always clear what addition operator is being used  */

        System.out.println("5+3="+5+3); // 5+3=53
        // order of operations and precedence -- the first + is for string concatenation -- so it will convert the rest into strings 
        System.out.println("5+3="+(5+3)); // force addition first, and then string concatenation
    }
}

/* print different from println */
// based on your OS, your endline character is different -- JVM interprets it 
// Apple - \n, Windows - \r\n

// print ln only takes one string -- if you want to print multiple things --> you need to use several prints
// println can print concatenated strings + 


// java homework given
// int 32 bits 
// byte - 
// long 
// char - 16 bit structure
// processing byte by byte of unicode of data 


// java import -- not necessary at all 
// import sufficient - that any time - shortcut 
// packaged into libraries -- can import packages and save having
// import all those util 



import java.io.*;

public class Two{

    public static void main(String[] args){
        // it was there before -- just use Scanner as a shortcut in stead of type java.util. 
        Scanner cin = new Scanner(System.in);
        // print whatever prompt you want and then input
        // not the same as python anymore - you cant combine
        System.out.print("What is your name?");
        String s = cin.nextLine();
        System.out.print("What is your age?: ");
        int age = cin.nextInt();
        // exception
        
        // int float double char boolean are the primitives
        // primitive and equivalent class
        // int and Integer
        Integer i = new Integer(age); // gotta use new 
        Integer i2 = age; // shortcut from calling new 
        // i and i2 are references to integers 
        // for primitives int i is actually an int
        // autobox - autounbox solution -- can just use shortcut

        Two obj = new Two();
        Object o = obj; 
        // this does not create an object, creates a reference to an object 
        // can use that reference ot reference the object created line prior
        // polymorphism rules apply

        // args = array of strings 
        // c++ argc = how many, argv = array
        // args = array of strings
         // length of array is:
        int argc = args.length; 
        // string length = s.len() 
        // array ISNT an object - has a property built into it called length of array 
        // java will store only as much as u ask it to store 
        // arrays are non dynamic -- like arrays in c++ - static size 
        int[] arr = new int[100];
        // arr = reference to array of integer 
        // assign that reference to array of 100 integers 

        for (int index = 0; index < 100; index++){
            arr [index] = index * 10;
        }
        // array wont be enlarged 

        // arraylists = dynamic arrays
        // heaps, hashmaps 
        // do not accept partially used arrays

        // c++ - create something as const -- must define value immediately
        // const int x = 4; 
        // manifest constantsknow the value when u compile it 
        // value also never changes 
        // don't need to wait until runtime -- value of all constants are known 

        // java version 
        //final int USER_AGE = age; 
        // works exactly like a constant in c++ but more
        // final is actually a variable whose value can change onlly once 
        // const is never changing
        // final - variable whose value changes only once when set
        final int USER_AGE;

        USER_AGE = age; 
        // can only be set once; 
        // assign it only once 
        // finals can change by only once! 
        // initialization includes assignment - thats your oNLY chance
        

        // obj has a lot of methods -- clone equals finalize getClass hashCode etc 

        Thing t1 = new Thing();
        //System.out.println ("T1=" + t1.getVal());
        // t1 is a reference an object
        System.out.println ("T1=" + t1);
       // this is actually calling hte object's toString() -- not just printing a reference point

        Thing[] tarr = new Thing[100]
        for (int i = 0; i< 100; i++){
            tarr[i] = new Thing(); 
            // must call NEW to actually have an object
        }
        for (int i = 0; i < 100; i++)
            System.out.print(i+"="+ tarr[i]);
        // this will CRASH
        // created 100 references to Things -- didn't even create 1 Thing 
        // everything is null in there

        for (Thing t: tarr){
            System.out.println(t);
            // treat each thing as object -- can iterate over array as an object 
            // t.setVal(t.getVal()+1) // doesnt work -- creation of a reference
        }

        for (Object o: tarr){
            System.out.println(o);
        }
    }
    // diff between object and class
    // class is a framework - idea, build to develop and object
    // object is instantiation of class 
    // blueprint of how to construct -- class
    // from blueprint -- can define lots of objects 

}

// String class 
// java.lang is automatically imported -- hence have direct access to String, Integer, double

// java.lang.object -- methods inside the class 
// compareTo(string)
// compare
// .equals to compare strings
// split the string
// get a substring using beginning and end in index
// 

// java.lang.Object --> indentation -- java.lang.String
// base class is going to be Object of every class in java 

// inside main -- can instantiate object of the class 

// int doesnt derive from anything because it's not a class 


// if you create function inside main class -- should be public static
// will create other classes for hw 
// should not have static functions 
//  only one public class has to be same name as file 

// other classes 


class Thing{
    // no big 3 in java
    int val;
    Thing(){
        val = 100;
    }
    int getVal(){
        return val;
    }
    void setVal(int newval){
        val = newval;
    }
    // cant separate the function from class 

    // letting people know its overriding the object's tostring
    @Override
    public String toString(){
        "" + val; 
    }
}

Storage class 
ingredient class 
