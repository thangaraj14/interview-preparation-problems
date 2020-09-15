package practice;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

class ZeroEvenOdd {
    private int n;
    
    Semaphore zero= new Semaphore(1);
    Semaphore one= new Semaphore(0);
    Semaphore two= new Semaphore(0);
   
    AtomicInteger value= new AtomicInteger(1);
    
    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
       
        for(int i=1;i<=n;i++){
            zero.acquire();
            printNumber.accept(0);
            if(i%2==0) two.release();
            if(i%2!=0) one.release();
        }
      
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        
      for(int i=2;i<=n;i=i+2){
          two.acquire();
          printNumber.accept(i);
          zero.release();
      }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for(int i=1;i<=n;i=i+2){
          one.acquire();
          printNumber.accept(i);
          zero.release();
      }
    }
}