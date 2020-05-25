/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.homework4client;

import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author cindylee
 */
public class Homework4Client {

    static JTextField username;
    static JTextField server;
    static JTextField message;
    static JTextArea chatMessageRoom;
    static JPanel welcome;
    static JPanel chatRoom;
    static JPanel messageBar;
    static String chatMessages;
    static Socket s;
    static PrintStream sout;
    static Scanner sin;

    public static void main(String[] args) {
        JFrame jf = new JFrame("Homework 4 Chat");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(650, 650);

        JPanel jp = new JPanel();
        jp.setLayout(new BorderLayout());

        // initial panel to ask user for server and username
        welcome = new JPanel();
        welcome.setLayout(new GridLayout(3, 1));

        // user prompt
        JPanel userPrompt = new JPanel();
        JLabel usernameLabel = new JLabel("Username: ");
        username = new JTextField(40);
        userPrompt.add(usernameLabel);
        userPrompt.add(username);

        // server prompt
        JPanel serverPrompt = new JPanel();
        JLabel serverLabel = new JLabel("Server: ");
        server = new JTextField(40);
        serverPrompt.add(serverLabel);
        serverPrompt.add(server);

        // connect button
        JPanel connectStart = new JPanel();
        JButton connect = new JButton("Connect");
        connect.addActionListener(new ButtonListener("connect"));
        connectStart.add(connect);

        welcome.add(userPrompt);
        welcome.add(serverPrompt);
        welcome.add(connectStart);
        jp.add(welcome, BorderLayout.NORTH);
        
        // chat panel 
        chatRoom = new JPanel();
        chatRoom.setLayout(new GridLayout(1, 1));
        chatMessages = "";
        chatMessageRoom = new JTextArea(chatMessages);
        chatMessageRoom.setEditable(false);
        // make it a scrollable so it's easier to see long messages
        // and to see messages past the screen
        JScrollPane messagePane = new JScrollPane(chatMessageRoom);
        chatRoom.add(messagePane);
        
        
        // message panel where user types in text and the send button
        messageBar = new JPanel();
        message = new JTextField(40);
        JButton send = new JButton("SEND");
        send.addActionListener(new ButtonListener("send"));
        messageBar.add(message);
        messageBar.add(send);

        jp.add(chatRoom, BorderLayout.CENTER);
        chatRoom.setVisible(false);
        jp.add(messageBar, BorderLayout.SOUTH);
        messageBar.setVisible(false);
        jf.add(jp);

        jf.setVisible(true);
    }
}

class ButtonListener implements ActionListener {

    String buttonType;
    int portNum = 5190;

    ButtonListener(String type) {
        buttonType = type;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (buttonType.equals("connect")) {
            handleConnect();
        } else {
            handleSend();
        }
    }

    void handleConnect() {
        String username = Homework4Client.username.getText();
        String serverInfo = Homework4Client.server.getText();
        if (!username.equals("") && !serverInfo.equals("")) {
            try {
                Homework4Client.s = new Socket(serverInfo, portNum);
                if (Homework4Client.s.isConnected()){
                    Homework4Client.sout = new PrintStream(Homework4Client.s.getOutputStream());
                    Homework4Client.sin = new Scanner(Homework4Client.s.getInputStream());
                    Homework4Client.sout.print(username + "\r\n");
                    new ProcessIncoming().start();
                    Homework4Client.welcome.setVisible(false);
                    Homework4Client.chatRoom.setVisible(true);
                    Homework4Client.messageBar.setVisible(true);
                }
            } 
            catch (IOException e) {}
        }
    }

    void handleSend() {
        String messageData = Homework4Client.message.getText();
        if (!messageData.equals("") && Homework4Client.s.isConnected()) {
            Homework4Client.sout.print(messageData + "\r\n");
            Homework4Client.message.setText("");
        }
    }
}

class ProcessIncoming extends Thread {

    @Override
    public void run() {
        try{
            while(true){
                if (Homework4Client.s.isConnected()){
                    Homework4Client.chatMessages += Homework4Client.sin.nextLine() + "\r\n";
                    Homework4Client.chatMessageRoom.setText(Homework4Client.chatMessages);
                }
            } 
        }
        catch (Exception e){}
    }
}
