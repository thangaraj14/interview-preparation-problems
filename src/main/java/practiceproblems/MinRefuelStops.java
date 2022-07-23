package practiceproblems;

import java.util.PriorityQueue;

/**
 * tricky priority queue
 * revise
 */
public class MinRefuelStops {

    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        if (startFuel >= target) return 0;
        int curFarthest = startFuel, refuel = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int[] station : stations) {
            // check if we can reach next station
            // if we cannot reach this station, refuel the gas from the previous station with most gas
            // redo the operation until we get enough gas to reach this station
            while (curFarthest < station[0]) {
                if (pq.isEmpty())
                    return -1; // if we refuel in each station but still cannot reach this station, return -1
                curFarthest += pq.poll();
                refuel++;
            }
            pq.offer(station[1]);
        }
        // now we have reached the last station, check if we can reach the target
        while (curFarthest < target) {
            if (pq.isEmpty()) return -1;
            curFarthest += pq.poll();
            refuel++;
        }
        return refuel;
    }
}
