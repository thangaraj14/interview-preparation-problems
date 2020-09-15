package multithreading.educative;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// Problem
// Design and implement a thread-safe class that allows registration of callback methods that are executed after a user specified time interval in seconds has elapsed.

public class DeferredCallbackExecutor {

    PriorityQueue<CallBack> q = new PriorityQueue<CallBack>(new Comparator<CallBack>() {
        public int compare(CallBack o1, CallBack o2) {
            return (int) (o1.executeAt - o2.executeAt);
        }
    });

    ReentrantLock lock = new ReentrantLock();
    Condition newCallbackArrived = lock.newCondition();

//    whenever a consumer thread requests a callback be registered, the caveat is to wake up the execution thread
//    and recalculate the minimum duration it needs to sleep for before the earliest callback becomes due for execution.
//    Consider this example: initially, the execution thread is sleeping for 30 mins before any callback in the min-heap is due.
//    A consumer thread comes along and adds a callback to be executed after 5 minutes.
//    The execution thread would need to wake up and reset itself to sleep for only 5 minutes instead of 30 minutes.
//    Once we find an elegant way of capturing this logic our problem is pretty much solved.
    public void registerCallback(CallBack callBack) {
        lock.lock();
        q.add(callBack);
        newCallbackArrived.signal();
        lock.unlock();
    }
    private long findSleepDuration() {
        long currentTime = System.currentTimeMillis();
        return q.peek().executeAt - currentTime;
    }
    public void start() throws InterruptedException {
        long sleepFor = 0;
        int lastSeenQSize = 0;
        while (true) {

            lock.lock();

            while (q.size() == 0) {
                newCallbackArrived.await();
            }

            while (q.size() != 0) {
                sleepFor = findSleepDuration();

                if(sleepFor <=0)
                    break;

                newCallbackArrived.await(sleepFor, TimeUnit.MILLISECONDS);
            }

            CallBack cb = q.poll();
            System.out.println(
                    "Executed at " + System.currentTimeMillis()/1000 + " required at " + cb.executeAt/1000
                            + ": message:" + cb.message);

            lock.unlock();
        }
    }

    static class CallBack {

        long executeAt;
        String message;

        public CallBack(long executeAfter, String message) {
            this.executeAt = System.currentTimeMillis() + executeAfter * 1000;
            this.message = message;
        }
    }
}
