package multithreading.practice;

import java.util.concurrent.Semaphore;

class Foo {
    Semaphore first= new Semaphore(0);
    Semaphore second= new Semaphore(0);
    Semaphore third= new Semaphore(0);
    
    public Foo() {
        
    }

    public void first(Runnable printFirst) throws InterruptedException {
        first.acquire();
        
    
        printFirst.run();
        
        second.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        first.release();
        second.acquire();
       
        printSecond.run();
        third.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        first.release();
        third.acquire();
       
        printThird.run();
        
        
    }
}