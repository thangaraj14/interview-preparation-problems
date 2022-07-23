package practiceproblems;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianOfKWindow {
    public double[] medianSlidingWindow(int[] nums, int k) {
        MedianQueue medianHeap = new MedianQueue();

        double[] result = new double[nums.length - k + 1];

        int resultIndex = 0;

        for (int i = 0; i < nums.length; i++) {
            medianHeap.offer(nums[i]);
            if (medianHeap.size() == k) {
                result[resultIndex++] = medianHeap.median();
                medianHeap.remove(nums[i + 1 - k]);
            }

        }

        return result;
    }

    static class MedianQueue {
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Collections.reverseOrder());

        public void offer(int x) {
            maxQueue.offer(x);
            minQueue.offer(maxQueue.poll());
            if (maxQueue.size() < minQueue.size()) {
                maxQueue.offer(minQueue.poll());
            }

        }

        public double median() {
            return maxQueue.size() > minQueue.size() ? maxQueue.peek() : ((long) maxQueue.peek() + minQueue.peek()) * 0.5;
        }

        public int size() {
            return minQueue.size() + maxQueue.size();
        }

        public boolean remove(int x) {
            return minQueue.remove(x) || maxQueue.remove(x);
        }
    }
}