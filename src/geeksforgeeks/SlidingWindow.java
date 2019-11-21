package geeksforgeeks;

import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindow {

    public static void main(String[] args) {
        int arr[] = {8, 5, 10, 7, 9, 4, 15, 12, 90, 13};
        int k = 3;
        printMax(arr, arr.length, k);
    }

    private static void printMax(int[] arr, int length, int k) {
        Deque<Integer> deque = new LinkedList<>();
        int i = 0;
        for (; i < k; i++) {
            while (!deque.isEmpty() && arr[i] >= arr[deque.peekLast()])
                deque.removeLast();
            deque.addLast(i);
        }
        for (; i < length; i++) {
            System.out.println(arr[deque.peekFirst()]);

            while (!deque.isEmpty() && deque.peekFirst() <= i - k)
                deque.removeFirst();

            while (!deque.isEmpty() && arr[i] >= arr[deque.peekLast()])
                deque.removeLast();

            deque.addLast(i);
        }
        System.out.println(arr[deque.peekFirst()]);
    }

}