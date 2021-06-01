/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.courses.pr1;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class Main {
    
    
    final static int PHRASE_COUNT = 3;
    final static String PUDGE_FILE = "pudge";
    
    public static void main(String[] args){ //0-lang, 1-country
        
        String[] phrases = new String[PHRASE_COUNT];
        String pudgeName="";
        int pudgeLevel;
        
        
        Locale locale = (args.length > 1)? new Locale(args[0].toLowerCase(),args[1].toUpperCase()) : new Locale("en","US");
        ResourceBundle bundle = ResourceBundle.getBundle("lang", locale);

        for(int i=0; i<PHRASE_COUNT; i++ ){
            phrases[i] = bundle.getString("phrase" + (i+1)); 
        }
        
        Scanner scanner = new Scanner(System.in);
        
        File f = new File(PUDGE_FILE);
        if(f.exists()) { 
            System.out.println("Pudge file exists, read name from it");
            try(DataInputStream dis = new DataInputStream(new FileInputStream(PUDGE_FILE));) {
                pudgeName = dis.readUTF();
                pudgeLevel = dis.readInt();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else{
            while(true){
                System.out.println("please, enter a pudge name(starts with the letter): ");
                if(scanner.hasNext("[a-zA-Z].*")){
                    pudgeName = scanner.next();
                    break;
                }
                else{
                    scanner.next();
                }
            }
        }
        
        while(true){
            System.out.println("please, enter a pudge level: ");
            if(scanner.hasNextInt()){
                pudgeLevel = scanner.nextInt();
                break;
            }
            else{
                scanner.next();
            }
        }
        scanner.close();
        
        Pudge pudge = new Pudge(pudgeName,pudgeLevel,phrases);
        
        try{
            pudge.write(PUDGE_FILE);
        }catch(FileNotFoundException ex){
            System.out.println("file corrupted or cannot be created");
        }catch(IOException ex){
            System.out.println("can't write data to file");
        }
        System.out.println("current Pudge: " + pudge.print());
    }
}
