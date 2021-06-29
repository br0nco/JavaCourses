/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import static com.App.round;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

/**
 *
 * @author vinny
 */
public class CatFarschFactory {
    
    private double farschTotal = 0.00;
    
    
    public double getFarsch(){
        return round(farschTotal,2);
    }
    
    private boolean isBlohable(Cat cat){
        boolean isBlohable = false;
        
        for (Annotation annotation : cat.getClass().getAnnotations()) {
            if (annotation instanceof Blohable) {
                isBlohable = true;
                System.out.println("Blohable!! doesnt suit for the Farsch");
            }
        }
        return isBlohable;
    }
    
    private boolean isBlack(Cat cat) throws NoSuchFieldException{
        boolean isBlack = false;
        
        Field field = cat.getClass().getDeclaredField("name");
        Color colorAnnotation = field.getAnnotation(Color.class);
        if(colorAnnotation != null && colorAnnotation.color().equalsIgnoreCase("black")){
            isBlack = true;
            System.out.println("Cat is black!! probability to go to the Farsch is 50/50");
        }
        return isBlack;
    }
    
    private boolean isBlackCatSafe(){
        boolean b = (Math.round(Math.random()) == 0);
        if(b)
            System.out.println("The Black Cat is safe from Farsch");
        else 
            System.out.println("The Black Cat goes to Farsch");
        return b;
    }
     
    private boolean hasPublic(Cat cat){   
        boolean hasPublic = false;

        Method[] methods = cat.getClass().getDeclaredMethods();
        for(Method method : methods){
            if(!Modifier.toString(method.getModifiers()).equalsIgnoreCase("private")){
                hasPublic = true;
                System.out.println(cat.getClass()+" contains not only private methods. Doesn't suit for the Farsch");
                break;
            }

        }
        return hasPublic;
    }
    
    public void makeFarsch(List<Cat> cats) throws IllegalArgumentException,  NoSuchFieldException,   IllegalAccessException{
        
        for(Cat cat : cats){
            
            Field nameField = cat.getClass().getDeclaredField("name");
            nameField.setAccessible(true);
            System.out.println("Checking cat " + nameField.get(cat).toString() + " ...");
            
            if(!hasPublic(cat) && !isBlohable(cat) && (!isBlack(cat) || !isBlackCatSafe())){
                Field field = Cat.class.getDeclaredField("weight");
                field.setAccessible(true);
                double farsch = round(field.getDouble(cat)*0.6,2);
                System.out.println("Farsch obtained: " + farsch);   
                farschTotal += farsch;
            }
        }
    }
}
