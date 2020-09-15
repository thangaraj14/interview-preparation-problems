package com.safecabs.app;

import com.safecabs.Constants;
import com.safecabs.Gender;
import com.safecabs.passenger.Passenger;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class CustomCyclicBarrier {

    Callable requestToAssignCab;
    int malePassengerRequestCount = 0;
    int femalePassengerRequestCount = 0;
    CyclicBarrier barrier;
    Semaphore maleWaitSemaphore = new Semaphore(0);
    Semaphore femaleWaitSemaphore = new Semaphore(0);
    ReentrantLock lock = new ReentrantLock();

    public CustomCyclicBarrier(int cabCapacity, Callable<Integer> requestToAssignCab) {
        this.requestToAssignCab = requestToAssignCab; // Allocate Cab Request once passengers are ready
        barrier = new CyclicBarrier(cabCapacity); // wait till cab capacity full
    }

    public void await(Passenger passenger) throws Exception {
        if (passenger.getGender() == Gender.MALE) {
            seatMale();
        } else {
            seatFemale();
        }
    }

    private void seatFemale() throws Exception {

        lock.lock();

        boolean leaderThread = false;
        femalePassengerRequestCount++;

        if (femalePassengerRequestCount == Constants.CAB_CAPACITY) {
            System.out.println("All Female Condition Meet");
            femaleWaitSemaphore.release(Constants.CAB_CAPACITY - 1);
            femalePassengerRequestCount -= Constants.CAB_CAPACITY;
            leaderThread = true;
        } else if (femalePassengerRequestCount == Constants.HALF_CAB_CAPACITY && malePassengerRequestCount >= Constants.HALF_CAB_CAPACITY) {
            System.out.println(Constants.HALF_CAB_CAPACITY+" Female And "+Constants.HALF_CAB_CAPACITY+" Male Condition Meet");
            femaleWaitSemaphore.release(Constants.HALF_CAB_CAPACITY - 1);
            maleWaitSemaphore.release(Constants.HALF_CAB_CAPACITY);
            malePassengerRequestCount -= Constants.HALF_CAB_CAPACITY;
            femalePassengerRequestCount -= Constants.HALF_CAB_CAPACITY;
            leaderThread = true;
        } else {
            lock.unlock();
            femaleWaitSemaphore.acquire();
        }
        if (leaderThread) {
            requestToAssignCab.call();
            lock.unlock();
        }
        barrier.await();
    }

    private void seatMale() throws Exception {
        lock.lock();
        boolean leaderThread = false;
        malePassengerRequestCount++;

        if (malePassengerRequestCount == Constants.CAB_CAPACITY) {
            System.out.println("All Male Condition Meet");
            maleWaitSemaphore.release(Constants.CAB_CAPACITY - 1);
            malePassengerRequestCount -= Constants.CAB_CAPACITY;
            leaderThread = true;
        } else if (malePassengerRequestCount == Constants.HALF_CAB_CAPACITY && femalePassengerRequestCount >= Constants.HALF_CAB_CAPACITY) {
            System.out.println(Constants.HALF_CAB_CAPACITY+" Male and "+Constants.HALF_CAB_CAPACITY+" Female Condition Meet");
            maleWaitSemaphore.release(Constants.HALF_CAB_CAPACITY - 1);
            femaleWaitSemaphore.release(Constants.HALF_CAB_CAPACITY);
            malePassengerRequestCount -= Constants.HALF_CAB_CAPACITY;
            femalePassengerRequestCount -= Constants.HALF_CAB_CAPACITY;
            leaderThread = true;
        } else {
            lock.unlock();
            maleWaitSemaphore.acquire();
        }
        if (leaderThread) {
            requestToAssignCab.call();
            lock.unlock();
        }
        barrier.await();

    }
}
