package practiceproblems.jumpGame;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * https://leetcode.com/problems/jump-game-iv/
 */
public class JumpGameV {

    public int minJumps(int[] arr) {
        int n = arr.length;

        if (n == 1) return 0;

        // craeted map holding integer & list
        Map<Integer, List<Integer>> map = new HashMap<>();
        int step = 0; // intial step is 0

        // Our 1st job is "fill the map"
        for (int i = 0; i < n; i++) {
            // so, using this function it will check is arr[i] is present or not, if it's not present it would create a new arraylist
            // and if it's already present we will add index in it
            map.computeIfAbsent(arr[i], v -> new LinkedList<>()).add(i);
        }

        // next we need a queue.
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);// in queue we will add our 1st index which is 0

        while (!q.isEmpty()) { // looping until queue is not empty
            step++; // incrementing our step
            int size = q.size(); // taking queue size
            for (int i = 0; i < size; i++) { // now for each element in this queue for this particulart size running a loop
                // so, here we will perform 3 steps
                int j = q.poll(); // getting element from queue

                // Jump to j - 1
                if (j - 1 >= 0 && map.containsKey(arr[j - 1])) {
                    q.offer(j - 1);
                }

                // Jump to j + 1
                if (j + 1 < n && map.containsKey(arr[j + 1])) {
                    // there could be 2 conditions
                    if (j + 1 == n - 1) return step; // if j+1 is equals to last element
                    q.offer(j + 1); // otherwise add in queue
                }

                // Jump to k --> arr[j] == arr[k]
                if (map.containsKey(arr[j])) { // if this particular element hasn't processed
                    for (int k : map.get(arr[j])) { // so, we will iterate over each k
                        if (k != j) { // in this we first check if they are not equal, positions are not same
                            if (k == n - 1) return step;
                            q.offer(k);
                        }
                    }
                }
                map.remove(arr[j]); // removing from map
            }
        }

        return step;
    }
}
