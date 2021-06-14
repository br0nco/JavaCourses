/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.task1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vinny
 */
public class WorkerDispatcher implements IWorkerDispatcher{
    private GoldMine mine;
    private ArrayList<Worker> workerList;
    
    public WorkerDispatcher(GoldMine mine){
        this.mine = mine;
        workerList = new ArrayList<>();
    }
    
    public List<Worker> getWorkerList(){
        return workerList;
    }

    @Override
    public void workerAssignment(Worker worker) {
        workerList.add(worker);
        worker.setMine(mine);
    }  
}
