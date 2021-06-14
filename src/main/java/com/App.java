package com;

import com.task2.Cashier;
import com.task2.Buyer;
import com.task1.Worker;
import com.task1.GoldMine;
import com.task1.Barrack;
import com.task1.WorkerDispatcher;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void task1() {
        GoldMine mine = new GoldMine();
        WorkerDispatcher wd = new WorkerDispatcher(mine);
        
        for(int i = 0; i < 3; i++){
            wd.workerAssignment(new Worker("worker#" + i));
        }

        Barrack barrack = new Barrack(wd,() -> mine.isEmpty());
        
        
        try {
            barrack.getThread().join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        for(Worker worker : wd.getWorkerList()){
//            System.out.println("Joining " + worker.getName());
            try {
                worker.getThread().join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static void task2(){
        Cashier cashier = new Cashier();
        ArrayList<Buyer> buyerList = new ArrayList<>();

        for(int i = 0; i < 5; i++){
            buyerList.add(new Buyer("buyer#" + i,cashier));
        }
        
        for(Buyer buyer: buyerList){
            try {
                buyer.getThread().join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static void main( String[] args )//task num
    {
        if(args.length > 0){
            switch(args[0]){
                case("1"):
                    task1();
                    break;
                case("2"):
                    task2();
                    break;
                default:
                    System.out.println("task not found");
            }        
        }
        
        System.out.println( "Main ENDS" );
    }
}
