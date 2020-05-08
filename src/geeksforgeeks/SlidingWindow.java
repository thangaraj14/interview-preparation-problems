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
        maxSlidingWindow(arr, k);
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return new int[0];
        }
        if (nums.length == 1) {
            return nums;
        }

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

    void maxSlidingVicky(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            while (!queue.isEmpty() && queue.peek() < i - k + 1) {
                queue.poll();
            }
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.pollLast();
            }

            queue.offer(i);

            if (i >= k - 1) {
                result.add(nums[queue.peekFirst()]);
            }
        }
    }
}