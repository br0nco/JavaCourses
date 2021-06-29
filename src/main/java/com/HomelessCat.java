/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

/**
 *
 * @author vinny
 */
@Blohable
public class HomelessCat extends Cat{
    private String name;
    
    HomelessCat(String name, double weight){
        super(weight);
        this.name = name;
        System.out.println("Cat was created. Name: " + name + ". Weight: " + weight);
    }

    private String getCatInfo(){
        return "the cat's name is: " + name + ". It's weight: " + weight; 
    }

}
