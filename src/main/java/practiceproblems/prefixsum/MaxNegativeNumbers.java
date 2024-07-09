package practiceproblems.prefixsum;

import java.util.Collections;
import java.util.PriorityQueue;

public class MaxNegativeNumbers {


    /**
     * We add each number to the priority queue and increment ans, assuming we can keep it negative.
     * If the sum becomes non-positive, we need to make some numbers positive to keep the overall sum positive.
     * We choose the largest available number (top of the priority queue) to make positive, as this allows us to keep more numbers negative overall.
     * When we make a number positive, we add twice its value to the sum (reversing the initial subtraction and then adding it).
     * We decrease ans each time we make a number positive.
     * @param numbers
     * @return
     */
    public static int maxNegativeSigns(int[] numbers) {
        int sum = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int ans = 0;

        for (int val : numbers) {
            sum -= val;
            pq.offer(val);
            ans++;
            while (!pq.isEmpty() && sum <= 0) {
                int top = pq.peek();
                sum += 2 * top; // make it positive by adding twice its value
                pq.poll();
                ans--;
            }
        }
        return ans;

    }

    public static void main(String[] args) {
        System.out.println(maxNegativeSigns(new int[]{3, 2, 1, 1, 1,1}));
        System.out.println(maxNegativeSigns(new int[]{1, 2, 3, 4, 5}));
    }
}
