package practice;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class OddEvenSemaphore {
    public static void main(String[] args) {
        //Odd odd= new Odd()
        PrinterSemaphore print= new PrinterSemaphore();
//        Thread even= new Thread(new EvenSemaphore(print));
//        even.setName("From Even Thread");
//        Thread odd= new Thread(new OddSemaphore(print));
//        odd.setName("From odd Thread");
//        odd.start();
//        even.start();


        ExecutorService service= Executors.newFixedThreadPool(10);
        service.submit(new OddSemaphore(print));
        service.submit(new EvenSemaphore(print));
    }
}

class OddSemaphore implements Callable<Boolean> {

    PrinterSemaphore print;

    public OddSemaphore(PrinterSemaphore p) {
        this.print=p;
    }

    @Override
    public Boolean call() throws Exception {
        for(int i=1;i<20;i+=2){
            print.printOdd(i);
        }
        return true;
    }
}

class EvenSemaphore implements Callable<Boolean>{

    PrinterSemaphore print;

    public EvenSemaphore(PrinterSemaphore p) {
        this.print=p;
    }

    @Override
    public Boolean call() throws Exception {
        for(int i=2;i<20;i+=2){
            try {
                print.printEven(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return true;
    }
}

class PrinterSemaphore {
    Semaphore semOdd= new Semaphore(1);
    Semaphore semEven= new Semaphore(0);

    public void printEven(int val) throws InterruptedException {
        try{
            semEven.acquire();
            System.out.println(Thread.currentThread().getName()+" value :"+val);
        } finally {
            semOdd.release();
        }


    }

    public void printOdd(int val){
        try {
            semOdd.acquire();
            System.out.println(Thread.currentThread().getName()+" value :"+val);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semEven.release();
        }




    }
}