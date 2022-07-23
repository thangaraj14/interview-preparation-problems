package multithreading.practice;

import java.util.concurrent.Semaphore;

class DiningPhilosophers {

    Semaphore[] forks = new Semaphore[5];
    Semaphore eating = new Semaphore(4);

    public DiningPhilosophers() {

        for (int i = 0; i < 5; i++) {
            forks[i] = new Semaphore(1);
        }

    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int i,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {

        eating.acquire();
        forks[i].acquire();
        forks[(i + 1) % 5].acquire();
        pickLeftFork.run();
        pickRightFork.run();

        eat.run();
        putLeftFork.run();
        putRightFork.run();
        forks[i].release();
        forks[(i + 1) % 5].release();
        eating.release();
    }
}