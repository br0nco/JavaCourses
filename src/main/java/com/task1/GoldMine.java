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
public class GoldMine {
 
    private static final int CAPACITY = 1000;
    private int goldQnty = CAPACITY;
    
    public synchronized int decrease(int load){
        System.out.println("Available gold in the mine:" + goldQnty);
        int takeAway = load;
        if(goldQnty < load)
            takeAway = goldQnty;
        
        goldQnty -= takeAway;
        return takeAway;
    }
    
    public boolean isEmpty(){
        return goldQnty == 0;
    }
}
