package practiceproblems;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/ipo/
 */


public class IPOMaxProfit {

    // Create (capital, profit) pairs and put them into PriorityQueue pqCap.
    // This PriorityQueue sort by capital increasingly.
    // Keep polling pairs from pqCap until the project out of current capital capability. Put them into
    // PriorityQueue pqPro which sort by profit decreasingly.
    // Poll one from pqPro, it's guaranteed to be the project with max profit and within current capital capability. 
    //Add the profit to capital W.
    //Repeat step 2 and 3 till finish k steps or no suitable project (pqPro.isEmpty()).
    public int findMaximizedCapital(int steps, int initialCapital, int[] profits, int[] capital) {

        PriorityQueue<int[]> minQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        PriorityQueue<int[]> maxQueue = new PriorityQueue<>((a, b) -> Integer.compare(b[1], a[1]));

        for (int i = 0; i < profits.length; i++) {
            minQueue.offer(new int[]{capital[i], profits[i]});
        }
        for (int i = 0; i < steps; i++) {
            while (!minQueue.isEmpty() && minQueue.peek()[0] <= initialCapital) {
                maxQueue.offer(minQueue.poll());
            }

            if (maxQueue.isEmpty()) break;

            initialCapital += maxQueue.poll()[1];

        }
        return initialCapital;

    }
}