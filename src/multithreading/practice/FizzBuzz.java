package practice;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

class FizzBuzz {
    private int n;
    Semaphore number= new Semaphore(1);
    Semaphore fizz= new Semaphore(0);
    Semaphore buzz= new Semaphore(0);
    Semaphore fizzbuzz= new Semaphore(0);
    
    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
    for(int i=3;i<=n;i=i+3){
        fizz.acquire();
        if((i+3)%5==0) i=i+3;
        printFizz.run();
        number.release();
    }
}

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for(int i=5;i<=n;i=i+5){
            buzz.acquire();
            if((i+5)%3==0) i=i+5;
            printBuzz.run();
            number.release();
        }
        
        
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for(int i=15;i<=n;i=i+15){
             fizzbuzz.acquire();
             printFizzBuzz.run();
             number.release();
        }
       
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for(int i=1;i<=n;i++){
            number.acquire();
            if(i%3==0 && i%5==0) fizzbuzz.release();
            else if(i%3==0) fizz.release();
            else if(i%5==0) buzz.release();
            else {
                printNumber.accept(i);
                number.release();
            }
        }
    }
}