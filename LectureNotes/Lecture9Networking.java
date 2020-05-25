package cs3913spring2020net1;

import java.io.*;
import java.net.*;
import java.util.*;

public classCS3913Spring2020Net1{
    public static void main(String[] args){
        try{
            // Socket s = new Socket("www.google.com", 80);
            // establish connection from my computer to google on port 80
            // port 80 is http port 

            // Socket s = new Socket ("smtp.gmail.com", 25);
            // professor's verizon network doesn't like port 25 with the gmail so here's a trick:

            Socket s = new Socket("192.169.10.4", 25);
            if (s.isConnected()){
                // can start to send and receive data 
                // socket could fail after connection
                PrintStream sout = new PrintStream(s.getOutputStream());
                // sout is output of program to google
                Scanner sin = new Scanner(s.getInputStream());
                // in order to get input and parse - need it centered around scanner
                // have a way of connecting two sockets - one on one side of connection, other side
                // flipping, input of one is output of another and vice versa 
                // server is piece of software that is running that listens for connections
                // establishing a socket between me and google's software (google web server since it's port 80)
                // one needs to be the server, the other needs to be the client (listener and connector)
                // one is gonna wait for a connection and then the other is going to make the connection
                // network sockets -- never good idea to use print ln
                
                // protocols will have some mechanism for ending a line - indicating that one party is done speaking 
                // generally I send info, I get info, there's a back and forth to most of the network protocols
                // not continuous - rarely a time where I'm not sure where I'll be sending or receiving
                // conversation very simple - send info, receive some more info 
                // needs to be abundantly clear when my sending is finished and when I should start receiving, vice versa 
                // I know based on the protocol that when I connect ot a http server, the very first thing I need to send -- what webpage I am getting 
                PrintStream outFile = new PrintStream("outfile.txt");
                sout.print("GET / HTTP/1.0\r\n\r\n"); 
                // CRLF carriage return and line feed <- needs to end with this 
                // Do a GET, on the page that I am requesting, followed by protocol (HTTP/1.0) 
                // Needs to end with r\n\r\n
                // never wait for result to come back to me which is not good -- so instead:
                while(sin.hasNext()){
                    // http protocol says that after the commmand - server will send back the google webpage
                    outFile.println(sin.nextLine());
                    // save google's webpage to a file 

                }

                // for smtp.gmail.com
                // SMTP specifies that the server sends first!
                String line = sin.nextLine();
                System.out.println("Server Said: " + line);

                sout.print("HELO dkatz\r\n");
                line = sin.nextLine();
                System.out.println("Server Said: " + line);
                
                sout.print("MAIL FROM:<dkatz@nyu.edu>\r\n");
                line = sin.nextLine();
                System.out.println("Server Said: " + line);

                sout.print("RCPT TO:<profcs4793@gmail.com\r\n");
                line = sin.nextLine();
                System.out.println("Server Said: " + line);

                sout.print("DATA\r\n");
                line = sin.nextLine();
                System.out.println("Server Said: " + line);
                
                sout.print("Subject: This is a test!\r\n\r\n How did it go???\r\n\r\n");
                line = sin.nextLine();
                System.out.println("Server Said: " + line);
            }else{
                System.out.println("Socket connection failed");
            }
        }
        catch(UnknownHostException e){
            System.out.println("No idea what website you're asking me to conect to!");
        }
        catch (IOException ex){
            // catches both exception since UnknownHost is under IOException
            // System.out.println("Welp, couldn't get to Google, the world must be ending!");
            System.out.println("IOError occurred");
            // could be that something interrupted the connection 
        }
    }
}

/*
Socket class 
- for network programming and it's in java.net 
- simple easy way to create a socket where you're connecting the computer to another computer
- idea of a socket: have a bidirectional communication mechanism between two hosts: in and out 
- can create a socket, creates a stream-based socket by default
- Normal socket is created by using the internet address and port number 
- string address and port 
- doesnt have mechanism for sending and receiving (no method to send or receive data???)
- can be a loopback address or an ip address 
- has get output and get input stream 


getOuputStream() -- goes directly to the server or whatever that is connected to the other side 

A few info 
important to know the protocol that we are dealing with 
some servers require you to send a greeting prior to actually sending data
need to acquire what the welcome message is and do that appropriately

connecting to smtp.gmail.com

*/

/*Create a server socket*/
// listening socket - create the capability to have other clients to us 
// allow 

import java.io.*;
import java.net.*;
import java.util.*;
public class CS3913Spring2020Net2{
    static int portNum = 6543;
    public static void main (String[] args){
        ServerSocket ss = null;
        int id = 0;
        try{
            ss = new ServerSocket(portNum);
            // port numbers between 0 and 65535
            // need to know on client and server side what the port num is that the server will be listening on

            // need to listen for connections
            System.out.println("Waiting for connections on port number: " + portNum);
            while(true){
                Socket client = ss.accept();
                // call accept function - Program will wait her for a LONG time!

                // this only handles one connection at a time! 
                // want to handle multiple clients at the same time 
                // use threads!!!!

                new ProcessConnection(id++, client).start();

                // once i get a connection 
                // System.out.println("Connection from: " + client.getInetAddress().toString());
                // PrintStream sout = new PrintStream(client.getOutputStream());
                // Scanner sin = new Scanner(client.getInputStream());
                
                // // creating something like an echo server
                // sout.print("Welcome to my echo server!\r\n");
                // String line = "";
                // while(!line.equalsIgnoreCase("EXIT")){
                //     line = sin.nextLine();
                //     System.out.println("Client (" + client.getInetAddress().toString() + ") Said: " + line);
                //     sout.print(line + "\r\n");
                // }
                // sout.print("Goodbye!\r\n");
                // System.out.println("Client (" + client.getInetAddress().toString() + ") Disconnected");
                // client.close();
            }
        }
        catch (IOException e){
            System.out.println("IOError: " + e.toString());
        }
    }
}

class ProcessConnection extends Thread{
    int id;
    Socket client;

    ProcessConnection(int newid, Socket newclient){
        id = newid;
        client = newclient; 
    }
    public void run(){
        try{
            System.out.println("Connection from: " + client.getInetAddress().toString() + " client " + id);
            PrintStream sout = new PrintStream(client.getOutputStream());
            Scanner sin = new Scanner(client.getInputStream());
            
            // creating something like an echo server
            sout.print("Welcome to my echo server!\r\n");
            String line = "";
            while(!line.equalsIgnoreCase("EXIT")){
                line = sin.nextLine();
                System.out.println("Client (" + id + ") Said: " + line);
                sout.print(line + "\r\n");
            }
            sout.print("Goodbye!\r\n");
            System.out.println("Client (" + id + ") Disconnected");
            client.close();
        }
        catch (IOException e){

        }
    }
}

// Professor uses program called telnet 

// telnet 127.0.0.1 6543