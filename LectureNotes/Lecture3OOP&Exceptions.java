// OOP and Exceptions

// class Thing{

//     int val;
//     Thing(){
//         val = 100;
//     }
//     public int getVal(){
//         return val;
//     }

//     public void setVal(int newval){
//         val = newval;
//     }

//     @override
//     public String toString(){
//         return "" + val;
//     }
// }

package cs3913spring2020oop;

public class cs3913spring2020oop{
    public static void main(String[] args){
        A a1 = new A();
        A a2 = new A();
        
        a1.setFirst(100);
        a1.setSecond(200);

        a2.setFirst(50);
        a2.setSecond(75);

        // a1.second = 200; 
        // a2.second = 200; warnings sicne these are statics

        // better way: 
        A.second = 75 // use name of the class to modify the static directly



        System.out.println("Object 1:");
        System.out.println("First=" +a1.getFirst());
        System.out.println("Second="+a1.getSecond());
        System.out.println("Object 2:");
        System.out.println("First=" +a2.getFirst());
        System.out.println("Second="+a2.getSecond());

        // first = 100, second = 75.0, first = 50, second = 75.0

        // obj1's second is changed?
        // no way one object change the stuff of another object -- no way to access
        // cannot manipulate the stuff of another object
        // purpose of static - tells you the variable is a member of the class but is not stored in the class

        // static variable 
        // one instance of first for each object
        // one instance of second for all objects 
        // not stored per object
        // nothing is stored outside of an object
        // one object can't access another stuff 

        // what object stores the second? 
        // java creates a new object for every class that you make 
        // the object's datatype is class 
        // there is a class in java called class
        // every class that you make -- java makes a new object for your datatype that you are creating and that object of type class stores all the static variables
        // thats where the magic happens

        // gets one instance of that class class for every class that you create -- where static variables are stored 

        // inherit the statisfication -- static modifier into your new class 
        // second objecgt in a new class and the rest of the instantions willbe inherited 

        // first is stored in the instance object, second will be stored in the class object

        // all share a second variable 

        // you can directly access the instance variables

        System.out.println("First" + a2.first);
        System.out.println("Second=" + a2.second);


        B.third = 9;

        // never once was an object of B class created
        // can still access third because its static
        // class is instantiated
        // accessing third via class is acceptable 

        C.printSomething();
        // can call static without instantiating

        Thing t1 = new Thing();
        try {
            t1.runme(); 
            // never gives back myexception because of the divide by 0 (function is terminated)-- but the final part is done 
            // if no divide by 0, both catch and finally are done
        } catch (AssertionError e){
            System.out.println("Assertion:" + e.toString())
        } catch (MyException e){
            // for the assertion error -- you can have catch Exception e (not my exception)
            System.out.println(e.toString())
        }
    }
}

class Thing{
    // needs to declare that it willl throw myException explicitly
    public void func() throws MyException{
        // throw new A(); // A needs to be throwables

        // int x = 5/ 0;
        // throw new MyException();
        int numerator = 5;
        int divisor = 0;
        assert divisor != 0: "Divisor cannot be 0"; 
        //assertion error exception -- text is the divsiro cannot be zero 

    }
    // needs to catch the exception!!! 
    // if u you did throws myexception next to runme --> main also need throws then
    public void runme() {
        try{
            func();
        }

        // some of the things that are thrown might not be caught
        catch(MyException ex){
            System.out.println("I caught that exception you were asking about!");
        }

        finally{
            System.out.println("This will ALWAYS be done!");
            // for other exceptions that aren't cauhgt but you dont want your function to end 
            // can't be skipped 
        }
    }
}

//exceptions are system defined or user defined
// user defined are class throwable type 

// class throwable - will not allow you to throw something if not throwable
// exceptions java can throw which you would not have created -- integer divide by 0 

class MyException extends Throwable{}

// must report all exceptions 

class C {
    int first; 
    static int second;
    static void printSomething() {
        System.out.println("Hello World!");
        // System.out.println("Second=" + first);
        System.out.println("Second=" + second);
        // static function is inside the class
        // trying to access instance variables...
        // which first?

        // static functions may not access instance variables!!!!
    }
}

class B {
    static int third;
}

class A{
    // first and second are not private although the methods are declared oublic
    int first;
    static double second;

    // default 

    public void setFirst(int n){
        first = n;
    }
    public int getFirst(){
        return first;
    }
    public void setSecond(double n){
        second = n;
    }
    public double getSecond() {
        return second;
    }
}

// class is a design idea - something you use to create an object
// object is instantiation of a class
/*

creating an instance of a class that is an object
every object is the creation of or instance of a class

class -- designing a blueprint

== one blueprint

can build many objects 


public private protected default 

protected = derived classes have access
public - have acces
private = no access
default -- package private 

group classes together into a package 

have access to package private material from all the classes and objects inside the material
can create classes that are associated with one another via their package

in same file -- in same package 

only one package listed per file 
you dont need to list a package or indicate that its in a pacakge and all of the classes in the same package have access to same stuff package private 


System has a captial s -- capitalize first letter of class but lowercase for the first letter of a variable

camelCase 

data from java -- using System.out.println 

dont need to instatiation system object -- out is static 

really need only one out for the whole program 

- so why create any more work 



*/

/*

Math class - final

stopped inheritance at that level 

abstract -- must list the class as being abstract 

pure virtual function -- java mandates that you list that class as abstract 

*/

// mandated to call it abstract 
abstract class D{
    abstract public void someFunc(); 
}

//constructors in class for java -- no destructor tho 
// however every once in a while there is the necessity to call some code when the object is destroyed 
// but java has a finalizer
// finalize function 

// finalize function -- called when object is destroyed from memory
// not up to us for when that happens 

// still have try throw catch 

// no operator overloading 

/* Exceptions
cases outside of the norm 

recognize how the stack works how activation records get push on and when exception occurs -- peel off record until u find a handler that deals with whatever exception you are throwing


*/

