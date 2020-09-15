package practice;

import java.util.concurrent.Callable;

public class OddEven {

    public static void main(String[] args) {

        Printer p= new Printer();
        Thread odd= new Thread(new Odd(p));
        Thread even= new Thread(new Even(p));

        odd.start();
        even.start();

    }


}

class Even implements Runnable{
    Printer print;

    public Even(Printer p) {
        this.print=p;
    }

    @Override
    public void run() {
        try {
            print.printEven();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
 class Odd implements Runnable{

    Printer print;

     public Odd(Printer p) {
         this.print=p;
     }

     @Override
    public void run() {
        try {
            print.printOdd();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
  class Printer {
    boolean isOdd;
    int max=20;
    int start=1;

    public synchronized void  printEven() throws InterruptedException {
        while(start<=max){
            if(start%2!=0){
                wait();
            }
            System.out.println("From thread "+Thread.currentThread().getName()+" value "+start++);
            notify();
        }
    }

    public synchronized void printOdd() throws InterruptedException {
        while(start<=max){
            if(start%2==0){
                wait();
            }
            System.out.println("From thread "+Thread.currentThread().getName()+" value "+start++);
            notify();
        }
    }
}
