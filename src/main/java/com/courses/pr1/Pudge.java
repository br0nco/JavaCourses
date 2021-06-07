/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.courses.pr1;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author User
 */
public class Pudge {
    
    private String name;
    private int level;
    private String[] phrases;
    
    Pudge(String name, int level, String[] phrases){
        this.name = name;
        this.level = level;
        this.phrases = phrases;
    }
    
    public void write(String fileToWrite) throws FileNotFoundException, IOException  {
        try( DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileToWrite));) {
            dos.writeUTF(this.name);
            dos.writeInt(this.level);
            for (String phrase : phrases) {
                dos.writeUTF(phrase);
            }
            System.out.println("Pudge has been written to a file successfully.");
        } /*catch (IOException ex) {
            ex.printStackTrace();
        }*/
    }   
    
    public String print(){
        StringBuilder sb = new StringBuilder("Pudge [ name: \"" + name + "\", level: " + level + ", phrases: [ " );
        for(int i=0; i<phrases.length; i++){
            sb.append("\"" + phrases[i]+"\"");
            if(i != phrases.length-1)
                sb.append(", ");
        }
        sb.append("]]").trimToSize();
        return sb.toString();
    }
    
}
