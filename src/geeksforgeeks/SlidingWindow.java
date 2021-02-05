package geeksforgeeks;

import java.util.Deque;
import java.util.LinkedList;
import java.util.*;

/**
 * https://leetcode.com/problems/sliding-window-maximum/
 */
public class SlidingWindow {

    public static void main(String[] args) {
        int arr[] = { 8, 5, 10, 7, 9, 4, 15, 12, 90, 13 };
        int k = 3;
        System.out.println(Arrays.toString(maxSlidingWindow(arr, k)));
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {

        List<Integer> list = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();
        int i = 0;

        while (i < k) {

            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.removeLast();
            }
            deque.addLast(i);
            i++;
        }

        for (; i < nums.length; i++) {

            list.add(nums[deque.peekFirst()]);

            if (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.removeFirst();
            }

            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i])
                deque.removeLast();

            deque.addLast(i);
        }
        list.add(nums[deque.peekFirst()]);

        return list.stream().mapToInt(Integer::new).toArray();
    }

}