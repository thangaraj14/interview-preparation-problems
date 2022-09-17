package practiceproblems;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/find-median-from-data-stream/
 */
public class MedianOfRunningIntegers {

    PriorityQueue<Integer> upperHalf = new PriorityQueue<>();
    PriorityQueue<Integer> lowerHalf = new PriorityQueue<>(Collections.reverseOrder());

    // 6,8,1,4,9,2,3,5
    // median is a middle element in sorted array
    // in a sorted array if we choose a point the immediate left to that point is maxLeft (max of all left)
    // the immediate right to that point is minRight (min of all right)
    // to mimic that here the right(max) values are stored in min heap
    // the left(min) values are stored in maxheap
    //  maxHeap.. i.. minHeap
    // if odd return from maxHeap
    public void addNum(int num) {
        // Insert in lowerHalf if it's empty or
        // if number being inserted is less than the peek of lowerHalf otherwise insert in upperHalf
        if (lowerHalf.isEmpty() || num <= lowerHalf.peek()) {
            lowerHalf.add(num);
        } else {
            upperHalf.add(num);
        }

        // We also need to ensure that the halves are balanced i.e.
        // there is no more than a difference of 1 in size of both halves
        // Let lowerHalf be the one to hold one extra element if the size of total
        // data stream is odd otherwise be equal to upperHalf
        if (upperHalf.size() > lowerHalf.size()) { // If an element added above made upperHalf have one more element than lowerHalf then we poll it and put it into lowerHalf
            lowerHalf.add(upperHalf.poll());
        } else if (lowerHalf.size() > upperHalf.size() + 1) {
            // If an element added above, made lowerHalf have 2 more elements then upperHalf then we put one into upperHalf from lowerHalf
            upperHalf.add(lowerHalf.poll());
        }
    }

    public double findMedian() {
        if (lowerHalf.size() == upperHalf.size()) {
            return (lowerHalf.peek() + upperHalf.peek()) / 2.0;
        } else {
            return lowerHalf.peek();
        }
    }

    public static void main(String[] args) {
        MedianOfRunningIntegers median = new MedianOfRunningIntegers();
        int A[] = {5, 15, 1, 3, 2, 8, 7, 9, 10, 6, 11, 4};
        for (int num : A) {
            median.addNum(num);
            System.out.println(median.findMedian());
        }
    }

}
