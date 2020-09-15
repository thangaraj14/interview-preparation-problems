package thread;

public class Tesst {

    static class ThreadA {

        public static void main(String [] args) {
            ThreadB b = new ThreadB();
            b.setName("test1");
            b.start();

            synchronized(b) {
                System.out.println("inside sync");
                try {
                    b.wait();
                    System.out.println("Waiting for b to complete...");

                    System.out.println("after "+"  "+b.getState());
                } catch (InterruptedException e) {}
                System.out.println("Total is: " + b.total);
            }
        }
    }

    static class ThreadB extends Thread {
        int total;

        public void run() {
            System.out.println("total"+total);
            synchronized(this) {
                for(int i = 0; i < 100; i++) {
                    total += i;
                }
                notify();
            }
        }
    }
}
