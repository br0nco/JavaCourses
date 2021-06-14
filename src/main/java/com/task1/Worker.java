/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.task1;

/**
 *
 * @author vinny
 */
public class Worker implements Runnable{
    private String workerName;
    private int goldQnty = 0;
    private final static int LOAD = 3;
    private Thread thread;
    private GoldMine mine;
    
    public Worker(String name){
        workerName = name;
        thread = new Thread(this);
        System.out.println(name + " was created");
    }
    
    public String getName(){
        return workerName;
    }
    
    public void setMine(GoldMine mine){
        this.mine = mine;
        thread.start();
        System.out.println(workerName + " started mining");
    }
    
    public Thread getThread(){
        return thread;
    }

    @Override
    public void run()  {
        while(true){
            int goldReceived = mine.decrease(LOAD);
            if (goldReceived == 0 ) break;//thread.interrupt();
            goldQnty += goldReceived;
            System.out.println(this.workerName + " gets " + goldReceived + " gold. Total qnty of his gold: " + goldQnty);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println(workerName + " is dead");
    }

}
