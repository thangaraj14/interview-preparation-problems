package thread.educative;

class MultiThreadedFizzBuzz {

    int n;
    int num = 1;

    public MultiThreadedFizzBuzz(int n) {
        this.n = n;
    }

    public void fizz() throws InterruptedException {
        synchronized (this) {
            while (num <= n) {
                if (num % 3 == 0 && num % 5 != 0) {
                    System.out.println("Fizz");
                    num++;
                    notifyAll();
                } else {
                    wait();
                }
            }
        }
    }

    public void buzz() throws InterruptedException {
        synchronized (this) {
            while (num <= n) {
                if (num % 3 != 0 && num % 5 == 0) {
                    System.out.println("Buzz");
                    num++;
                    notifyAll();
                } else {
                    wait();
                }
            }
        }
    }

    public void fizzbuzz() throws InterruptedException {
        synchronized (this) {
            while (num <= n) {
                if (num % 15 == 0) {
                    System.out.println("FizzBuzz");
                    num++;
                    notifyAll();
                } else {
                    wait();
                }
            }
        }
    }

    public void number() throws InterruptedException {
        synchronized (this) {
            while (num <= n) {
                if (num % 3 != 0 && num % 5 != 0) {
                    System.out.println(num);
                    num++;
                    notifyAll();
                } else {
                    wait();
                }
            }
        }
    }
}

class MultiThreadedFizzBuzzImpl {

    public static void main(String[] args) {

        MultiThreadedFizzBuzz obj = new MultiThreadedFizzBuzz(15);

        Thread t1 = new Thread(() -> {
            try {
                obj.fizz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                obj.buzz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                obj.fizzbuzz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t4 = new Thread(() -> {
            try {
                obj.number();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t2.start();
        t1.start();
        t4.start();
        t3.start();

    }

}