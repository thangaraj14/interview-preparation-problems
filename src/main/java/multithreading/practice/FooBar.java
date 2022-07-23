package multithreading.practice;

import java.util.concurrent.Semaphore;

class FooBar {
    private int n;
    Semaphore one= new Semaphore(0);
    Semaphore two= new Semaphore(1);
    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        
        for (int i = 0; i < n; i++) {
            
        	two.acquire();
        	printFoo.run();
            one.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        
        for (int i = 0; i < n; i++) {
            
            one.acquire();
        	printBar.run();
            two.release();
        }
    }
}