package dsa;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/find-median-from-data-stream/
 */
public class MedianOfRunningIntegers {

    //   minimum of maximum
    PriorityQueue<Integer> min = new PriorityQueue<>();
    //  maximum of minimum
    PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());

    // 6,8,1,4,9,2,3,5
    public void addNum(int num) {
        max.offer(num);
        min.offer(max.poll());
        if (max.size() < min.size()) {
            max.offer(min.poll());
        }
    }

    public double findMedian() {
        if (max.size() == min.size()) {
            return (max.peek() + min.peek()) / 2.0;
        } else {
            return max.peek();
        }
    }

    public static void main(String[] args) {
        MedianOfRunningIntegers median = new MedianOfRunningIntegers();
        int[] A = { 5, 15, 1, 3, 2, 8, 7, 9, 10, 6, 11, 4 };
        for (int num : A) {
            median.addNum(num);
            System.out.println(median.findMedian());
        }
    }

}
