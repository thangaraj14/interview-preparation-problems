package multithreading.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    private static int bankMoney = 0;
    private volatile int account;
    private Lock lock;
    private Condition condition;
    // write your code

    public Bank(int account) {
        this.account = account;
        // write your code

        lock = new ReentrantLock();
        condition = lock.newCondition();
    }

    public void saveMoney(int amount) throws Exception {

        try {
            lock.lock();
            account = saveOperation(account, amount);
            if (account >= amount) {
                condition.signalAll();
            }

        } finally {
            lock.unlock();
        }

    }

    public void withdrawMoney(int amount) throws Exception {
        // write your code

        try {
            lock.lock();
            while (amount > account) {
                condition.await();
            }
            account = withdrawOperation(account, amount);

        } finally {
            lock.unlock();
        }
    }

    public int checkAccount() {
        return account;
    }

    public static int saveOperation(int account, int amount) throws Exception {
        if (bankMoney != account) {
            throw new Exception("Don't cheat!\nYour money is " + account + ". The real money is " + bankMoney + ".");
        }
        bankMoney += amount;
        return bankMoney;
    }

    public static int withdrawOperation(int account, int amount) throws Exception {
        if (bankMoney != account) {
            throw new Exception("Don't cheat!\nYour money is " + account + ". The real money is " + bankMoney + ".");
        }
        if (bankMoney < amount) {
            throw new Exception("Money" + bankMoney + " in bank is lowwer than what you want to withdraw(" + account + ").");
        }
        bankMoney -= amount;
        return bankMoney;
    }
}