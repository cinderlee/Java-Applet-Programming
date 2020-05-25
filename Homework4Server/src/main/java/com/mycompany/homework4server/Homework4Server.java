/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.homework4server;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author cindylee
 */
public class Homework4Server {
    
    static int portNum = 5190;
    static ArrayList<ProcessConnection> clientConnections;
    
    public static void main(String[] args) {
        ServerSocket ss = null;
        clientConnections = new ArrayList<ProcessConnection>();
        try{
            ss = new ServerSocket(portNum);
            while (true){
                Socket client = ss.accept();
                ProcessConnection connection = new ProcessConnection(client, new PrintStream(client.getOutputStream()), new Scanner(client.getInputStream()));
                connection.start();
                clientConnections.add(connection);
            }
            
        }
        catch(IOException e){}
    }
}

class ProcessConnection extends Thread{
    String username;
    Socket client;
    PrintStream sout;
    Scanner sin;
    
    ProcessConnection(Socket newclient, PrintStream newsout, Scanner newsin){
        client = newclient;
        sout = newsout;
        sin = newsin;
    }
    
    @Override
    public void run(){
        try{
            username = sin.nextLine();
            System.out.println(username);
            String message = username + ": ";
            while (true){
                String line = sin.nextLine();
                for (ProcessConnection connection: Homework4Server.clientConnections){
                    connection.sout.print(message + line + "\r\n");
                }
            }
        }
        catch (Exception e){}
    }
}
