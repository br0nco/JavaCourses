/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.task1;

import java.util.function.BooleanSupplier;

/**
 *
 * @author vinny
 */
public class Barrack implements Runnable{

    private IWorkerDispatcher wd;
    private BooleanSupplier shouldStop;
    private Thread thread;
    
    public Barrack(IWorkerDispatcher wd, BooleanSupplier shouldStop){
        this.wd = wd;
        this.shouldStop =  shouldStop;
        thread = new Thread(this);
        thread.start();
    }
    
    public Thread getThread(){
        return thread;
    }
    
    @Override
    public void run() {
        int workerNum = 3;
        while(!shouldStop.getAsBoolean()){
            
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            
            Worker worker = new Worker("worker#" + workerNum);
            wd.workerAssignment(worker);
            workerNum++;
        }
        
        System.out.println("Barrack is done.");
        
    }
    
}
