package multithreading.thread;

class Counter  implements Runnable{
    private  int c = 0;

    public  void increment() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        c++;
    }

    public  void decrement() {
        c--;        
    }

    public  int getValue() {
        return c;
    }
    
    @Override
    public void run() {
        //synchronized(this) {
            // incrementing
        for(int i=0;i<100;i++) {
            this.increment();
//            System.out.println("Value for Thread After increment "
//                    + Thread.currentThread().getName() + " " + this.getValue());
            //decrementing
            this.decrement();
//            System.out.println("Value for Thread at last " + Thread.currentThread().getName()
//                    + " " + this.getValue());
            //}
        }
    }
}

public class RaceConditionExample {
    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread t1 = new Thread(counter, "Thread-A");
        Thread t2 = new Thread(counter, "Thread-B");
        Thread t3 = new Thread(counter, "Thread-C");
        t1.start();
        t2.start();
        t3.start();



        System.out.println("####  "+counter.getValue());
    }    
}