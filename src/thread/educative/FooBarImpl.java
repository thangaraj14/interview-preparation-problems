package thread.educative;

class FooBar {

    int n;
    int flag = 0;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo() {

        for (int i = 1; i <= n; i++) {
            synchronized (this) {
                while (flag == 1) {
                    try {
                        this.wait();
                    } catch (Exception e) {

                    }
                }
                System.out.print("Foo");
                flag = 1;
                this.notifyAll();
            }
        }
    }

    public void bar() {

        for (int i = 1; i <= n; i++) {
            synchronized (this) {
                while (flag == 0) {
                    try {
                        this.wait();
                    } catch (Exception e) {

                    }
                }
                System.out.println("Bar");
                flag = 0;
                this.notifyAll();
            }
        }
    }
}

public class FooBarImpl {

    public static void main(String[] args) {

        FooBar fooBar = new FooBar(3);

        Thread t1 = new Thread(fooBar::foo);
        Thread t2 = new Thread(fooBar::bar);

        t2.start();
        t1.start();

    }
}