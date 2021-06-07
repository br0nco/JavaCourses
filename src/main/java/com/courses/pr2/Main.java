/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.courses.pr2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;


/**
 *
 * @author vinny
 */
public class Main {
    
    public static void printHelp(){
        System.out.println("dir                    - lists contetn of a current directory");
        System.out.println("mkdir                  - creates a directory");
        System.out.println("cd <path|..>           - changes directory");
        System.out.println("cat <file>             - reads data from file and gives its content as output");
        System.out.println("    > <file>           - creates a new file");
        System.out.println("    >> <file>          - appends a data user write on console to a file");
        System.out.println("    <file1> >> <file2> - appends a data from file1 to file2");                
        System.out.println("copy <file1> <file2>   - copies file1 to file2");
        System.out.println("exit                   - quit the program");
    }
    
    
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
      
        String path = "C:/Users/vinny/Documents/NetBeansProjects/pr2";
        Path ppath = Paths.get(path);
        
        System.out.println("type \"help\" to look through available commands");
        while(true){
            System.out.println(">> ");
            String line = scanner.nextLine();
            if(line.startsWith("exit"))
                break; 
            else if(line.startsWith("help")){
                printHelp();
            }
            else if(line.startsWith("dir")){
                System.out.println("current folder: " + ppath.toAbsolutePath().toString()); 
                try {
                    Files.list(ppath).map(Path::getFileName).forEach(System.out::println);
                } catch (IOException ex) {
                    System.out.println("cannot access directory or it's not a directory");
                }
            }
            else if(line.startsWith("mkdir")){
                if(line.split(" ").length != 2){
                    System.out.println("wrong number of parameters for \"mkdir\" command");
                }
                else{
                    String splitted = (line.split(" ")[1]).replaceAll("\\\\","/");
                    Path dirToCreate = ppath.resolve(splitted);
                        try {
                            Files.createDirectories(dirToCreate);
                        } catch (IOException ex) {
                            System.out.println("some errors occured while creating directory");
                        }
                }
            }
            else if(line.matches("cat\\s+>>\\s*(\\S+)")){
                Path pathToWrite = ppath.resolve(line.substring(line.indexOf(">>")+2).replaceAll("\\\\","/").trim());
                try(BufferedWriter bw = Files.newBufferedWriter(pathToWrite,StandardOpenOption.APPEND);) {
                    System.out.println("Enter a content to append to a file:");
                    bw.write(scanner.nextLine());
                } catch (IOException ex) {
                   System.out.println("some errors occured while writing to a file");
                }
            }
            else if(line.matches("cat\\s+>\\s*(\\S+)")){
                Path pathToCreate = ppath.resolve(line.substring(line.indexOf(">")+1).replaceAll("\\\\","/").trim());
                try {
                    Files.createFile(pathToCreate);
                    System.out.println(pathToCreate + " was created successfully");
                } catch (IOException ex) {
                    System.out.println("can't create file");
                }
                
            }
            else if(line.matches("cat\\s+(\\S+)\\s*>>\\s*(\\S+)")){
                Path pathToRead = ppath.resolve(line.substring(line.indexOf("cat")+3,line.indexOf(">>")).replaceAll("\\\\","/").trim());
                Path pathToWrite = ppath.resolve(line.substring(line.indexOf(">>")+2).replaceAll("\\\\","/").trim());

                try(BufferedReader br = Files.newBufferedReader(pathToRead);
                    BufferedWriter bw = Files.newBufferedWriter(pathToWrite, StandardOpenOption.APPEND);){
                    String buf = br.readLine();
                    while(buf != null){
                        bw.write(buf);
                        buf = br.readLine();
                    }
                }catch(IOException ex){
                    System.out.println("some errors occured while appending data");
                }
            }
            else if(line.matches("cat\\s+(\\S+)")){ 
                String splitted = (line.split(" ")[1]).replaceAll("\\\\","/");
                Path fileToRead = ppath.resolve(splitted);
                if(Files.exists(fileToRead) && Files.isRegularFile(fileToRead)){ 
                    try{ 
                        List<String> fileLines = Files.readAllLines(fileToRead);
                        for(String fLine : fileLines)
                            System.out.println(fLine);
                    }catch(IOException ex){
                        System.out.println("some errors occured while reading the file");
                    }
                }
                else
                    System.out.println("file does not exist or file name is incorrect");
            }
            else if(line.startsWith("cd ")){
                if(line.contains(".."))
                    ppath  = ppath.getParent();
                else
                {   
                    String splitted = (line.split(" ")[1]).replaceAll("\\\\","/");
                    Path nPath = ppath.resolve(splitted);
                    if(Files.exists(nPath) && Files.isDirectory(nPath))
                        ppath = nPath;
                    else
                       System.out.println(splitted + " does not exist or is not a directory");
                }
            } 
            else if(line.startsWith("copy ")){
                if(line.split(" ").length != 3)
                    System.out.println("wrong number of parameters for copying file");
                else{
                    Path pathSource = Paths.get(line.split(" ")[1].replaceAll("\\\\", "/"));
                    String target = line.split(" ")[2].replaceAll("\\\\", "/");
                    if(Files.exists(pathSource) && Files.isRegularFile(pathSource)){
                        Path pathTarget = (Files.isDirectory(Paths.get(target))) ? Paths.get(target +"/"+ pathSource.getFileName().toString()) : Paths.get(target);
                        try {
                            Files.copy(pathSource, pathTarget, StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException ex) {
                            System.out.println("some errors occured");
                            ex.printStackTrace();
                        }
                    }
                    else 
                        System.out.println("source file does not exist or is corrupted");
                }
            }
        }
        scanner.close();
    }
}
