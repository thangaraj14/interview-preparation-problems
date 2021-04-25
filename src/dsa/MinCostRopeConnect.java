package dsa;

import java.util.PriorityQueue;

/**
 * https://www.geeksforgeeks.org/connect-n-ropes-minimum-cost/
 */
public class MinCostRopeConnect {

    // 6 + 4 = 10 .. 10 + 3 = 13 .. 13 +2 = 15

    // 2+3 = 5 .. 5 + 4 = 9 .. 9 + 6 = 15 ..

    public static void main(String[] args) {
        int arr[] = { 4, 3, 2, 6 };

        MinCostRopeConnect rope = new MinCostRopeConnect();
        rope.connectRopes(arr);
    }

    private void connectRopes(int[] arr) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i : arr)
            pq.add(i);

        while (pq.size() > 1) {
            Integer remove = pq.remove();
            Integer remove2 = pq.remove();

            System.out.println("cost" + (remove + remove2));
            pq.add(remove + remove2);
        }
    }
}
