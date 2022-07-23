package practiceproblems.design;

public class MyCircularQueue {

    int[] arr;
    int headIndex = 0;

    int length;
    int count;

    public MyCircularQueue(int k) {

        this.arr = new int[k];
        this.length = k;
        this.count = 0;
    }

    public boolean enQueue(int value) {
        if (count == length) return false;

        int index = (headIndex + count) % length;
        arr[index] = value;

        count++;
        return true;
    }

    public boolean deQueue() {
        if (count == 0) return false;

        headIndex = (headIndex + 1) % length;
        count -= 1;
        return true;
    }

    public int Front() {
        if (count == 0) return -1;
        return arr[headIndex];
    }

    public int Rear() {
        if (count == 0) return -1;

        int tailIndex = (headIndex + count - 1) % length;
        return arr[tailIndex];

    }

    public boolean isEmpty() {
        return count <= 0;
    }

    public boolean isFull() {
        return count == length;
    }


}