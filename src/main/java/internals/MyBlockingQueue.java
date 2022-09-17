package internals;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Reference: http://docs.oracle.com/javase/7/docs/api/java/util/concurrent/locks/Condition.html
public final class MyBlockingQueue<T> {
    private final Lock lock = new ReentrantLock();
    private final Condition notFull  = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    private final Deque<T> queue = new LinkedList<T>();
    private final int limit;

    public MyBlockingQueue(int limit) {
        if (limit < 1) throw new IllegalStateException("limit should be greater than 0");
        this.limit = limit;
    }

    public synchronized void enqueue(T item) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == limit) {
                notFull.await();
            }
            queue.addLast(item);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public synchronized T dequeue() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                notEmpty.await();
            }
            final T x = queue.removeFirst();
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }
}