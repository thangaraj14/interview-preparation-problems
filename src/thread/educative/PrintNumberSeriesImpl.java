package thread.educative;

import java.util.concurrent.*;

class PrintNumberSeries {
    private final int n;
    private Semaphore zeroSem, oddSem, evenSem;

    public PrintNumberSeries(int n) {
        this.n = n;
        zeroSem = new Semaphore(1);
        oddSem = new Semaphore(0);
        evenSem = new Semaphore(0);
    }

    public void PrintZero() {
        for (int i = 0; i < n; i++) {
            try {
                zeroSem.acquire();
            } catch (Exception e) {
            }
            System.out.print("0");
            (i % 2 == 0 ? oddSem : evenSem).release();
        }
    }

    public void PrintEven() {
        for (int i = 2; i <= n; i += 2) {
            try {
                evenSem.acquire();
            } catch (Exception e) {
            }
            System.out.print(i);
            zeroSem.release();
        }
    }

    public void PrintOdd() {
        for (int i = 1; i <= n; i += 2) {
            try {
                oddSem.acquire();
            } catch (Exception e) {
            }
            System.out.print(i);
            zeroSem.release();
        }
    }
}

public class PrintNumberSeriesImpl {

    public static void main(String[] args) {

        PrintNumberSeries series = new PrintNumberSeries(5);

        Thread t1 = new Thread(series::PrintZero);
        Thread t2 = new Thread(series::PrintEven);
        Thread t3 = new Thread(series::PrintOdd);

        t2.start();
        t1.start();
        t3.start();

    }
}
