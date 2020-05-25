/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cafequeue;

/**
 *
 * @author cindylee
 */

import java.util.LinkedList;

public class CafeQueue {
    LinkedList<String> queue;
    
    CafeQueue(){
        queue = new LinkedList<String>();
    }
    
    synchronized void enterQueue(String name){
        queue.add(name);
    }
    
    synchronized String serveCustomer(){
        if (!queue.isEmpty()){
            return queue.remove();
        }
        return "";
    }
}
