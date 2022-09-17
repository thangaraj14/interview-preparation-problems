package graph.leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/time-needed-to-inform-all-employees/submissions/
 */
public class TimeToInformEmployee {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        Map<Integer, List<Integer>> map = new HashMap<>();  // Build the hierarchical tree
        Deque<int[]> queue = new LinkedList<>();
        int result = Integer.MIN_VALUE;

        for (int i = 0; i < manager.length; i++) {
            if (!map.containsKey(manager[i]))
                map.put(manager[i], new ArrayList<>());

            map.get(manager[i]).add(i);     // Map of manager and its subordinates given by the index i
        }

        queue.offer(new int[]{headID, 0});      // head of organizer and corresponding informing time.

        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            int managerAtGivenLevel = temp[0];
            int cumulativeTime = temp[1];

            result = Math.max(result, cumulativeTime);

            if (map.containsKey(managerAtGivenLevel)) {      // if the manager has subordinates
                List<Integer> subordinates = map.get(managerAtGivenLevel);  // get the list of subordinates

                for (int i : subordinates) {
                    queue.offer(new int[]{i, informTime[managerAtGivenLevel] + cumulativeTime});    // add the subordinates as manager and the time taken to inform each of these subordinates
                }
            }
        }
        return result;
    }
}
