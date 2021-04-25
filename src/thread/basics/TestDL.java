package thread.basics;

public class TestDL {

    public static void main(String[] args) {
        final String resource1 = "ratan jaiswal";
        final String resource2 = "ratan jaiswal";
        Runnable runnable = () -> {
            synchronized (resource1) {
                System.out.println(Thread.currentThread().getName() + ":");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                synchronized (resource2) {
                    System.out.println(Thread.currentThread().getName() + ":");
                }
            }


        };

        Runnable runnable1 = () -> {
            synchronized (resource2) {
                System.out.println(Thread.currentThread().getName() + ":");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (resource1) {
                    System.out.println(Thread.currentThread().getName() + ":");
                }
            }


        };

        Thread t2 = new Thread(runnable1);
        Thread t1 = new Thread(runnable);

        t1.start();
        t2.start();

    }
}
