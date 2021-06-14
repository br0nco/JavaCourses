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
public class Cashier {
    
    private boolean isClosed = false;
    
    public boolean checkIfClosed(){
        return isClosed;
    }
    
    public synchronized void serve(Buyer buyer){
        if(isClosed) return;
        int timeToServe = (int)(Math.random()*8+3);
        System.out.println("cashier started serving " + buyer.getName() + ". Serving time: " + timeToServe + "sec");
        try {
            Thread.sleep(timeToServe*1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("cashier ended serving " + buyer.getName());
        isClosed = Math.round(Math.random()) == 1;
        if(isClosed) System.out.println("Cashier is closed");
    }
    
}
