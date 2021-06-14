/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.task2;

/**
 *
 * @author vinny
 */
public class Buyer implements Runnable{
    private String name;
    private Thread thread;
    private Cashier cashier;
    
    public Buyer(String name, Cashier cashier){
        this.name = name;
        this.cashier = cashier;
        thread = new Thread(this);
        thread.start();
        System.out.println(name + " is created");
    }
    
    public String getName(){
        return name;
    }
    
    public Thread getThread(){
        return thread;
    }
    
    @Override
    public void run() {
        //while(!cashier.checkIfClosed()){
            cashier.serve(this);
        //}
      
    }
    
}
