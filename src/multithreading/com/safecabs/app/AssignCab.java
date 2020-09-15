package com.safecabs.app;

import com.safecabs.cab.Cab;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

public class AssignCab implements Callable {

    private ArrayBlockingQueue<Cab> cabQueue;

    public AssignCab(ArrayBlockingQueue<Cab> cabQueue) {
        this.cabQueue = cabQueue;
    }

    public Integer call() {
        while (cabQueue.isEmpty()) {
            try {
                //All the cab requests should wait until a new cab appears
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Cab assignedCab = cabQueue.poll();
        System.out.println("Assigned cab " + assignedCab.getId());
        return assignedCab.getId();
    }

}
