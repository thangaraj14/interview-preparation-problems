package multithreading.practice;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLongArray;

public class RealTimeCounter {

    private final static int GRANULARITY = 300;
    private AtomicLongArray counter = new AtomicLongArray(GRANULARITY);
    private volatile int pos = 0;

    private RealTimeCounter() {
        PositionUpdater positionUpdater = new PositionUpdater(this);
        positionUpdater.start();
    }

    private static volatile RealTimeCounter INSTANCE;

    public static RealTimeCounter getInstance() {
        if (INSTANCE == null) {
            synchronized (RealTimeCounter.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RealTimeCounter();
                }
            }
        }
        return INSTANCE;
    }

    public long getTotalEvents() {
        int total = 0;
        for (int i = 0; i < GRANULARITY; i++) {
            total += counter.get(i);
        }
        return total;
    }

    public void addEvent() {
        counter.getAndIncrement(pos);
    }

    void incrementPosition() {
        //first reset the value to 0 at next counter location.
        counter.set((pos + 1) % GRANULARITY, 0);
        pos = (pos + 1) % GRANULARITY;
    }

    public static void main(String args[]) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        RealTimeCounter realTimeCounter = new RealTimeCounter();
        final Random random = new Random();
        final int TOTAL_EVENTS = 10000;
        CountDownLatch countDownLatch = new CountDownLatch(TOTAL_EVENTS);
        for (int i = 0; i < TOTAL_EVENTS; i++) {
            executor.execute(() -> {
                        realTimeCounter.addEvent();
                        try {
                            Thread.sleep(random.nextInt(10));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        countDownLatch.countDown();
                    }
            );
        }
        try {
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(realTimeCounter.getTotalEvents());
        executor.shutdownNow();
    }
}

class PositionUpdater extends TimerTask {

    private final RealTimeCounter realTimeCounter;
    private final Timer timer = new Timer(true);
    private static final int DELAY = 1000;

    PositionUpdater(RealTimeCounter realTimeCounter) {
        this.realTimeCounter = realTimeCounter;
    }

    public void start() {
        timer.schedule(this, DELAY);
    }

    @Override
    public void run() {
        realTimeCounter.incrementPosition();
    }
}