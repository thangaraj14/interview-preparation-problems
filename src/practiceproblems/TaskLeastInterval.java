package practiceproblems;

import java.util.*;

/**
 * https://leetcode.com/problems/task-scheduler/
 */


public class TaskLeastInterval {

    public static int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        for (char task : tasks) {
            map.put(task, map.getOrDefault(task, 0) + 1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>(
                (a, b) -> Integer.compare(b.getValue(), a.getValue()));

        queue.addAll(map.entrySet());

        int count = 0;
        // At each iteration, we process at most 'n' elements,
        // and move forwards exactly n+1 in time (regardless of how many elements we processed:
        // Read the topmost from the queue and increment the time. Add it to a temp list to be added later.
        // Add the element back to the queue from the temp list if count is > 0.
        // if al elements are done, we're done too.
        // Move time forward by n + 1
        // Return time in the end.
        while (!queue.isEmpty()) {
            int k = n + 1;  //each time fill k elements, if k is not full, that's the idle 
            List<Map.Entry> tempList = new ArrayList<>();
            while (k > 0 && !queue.isEmpty()) {
                Map.Entry<Character, Integer> top = queue.poll();
                top.setValue(top.getValue() - 1);
                tempList.add(top);
                k--;
                count++;
            }

            for (Map.Entry<Character, Integer> e : tempList) {
                if (e.getValue() > 0) queue.add(e); // add valid tasks
            }

            if (queue.isEmpty()) break;
            count = count + k; // if k > 0, then it means we need to be idle
        }
        return count;
    }

    public static void main(String[] args) {
        char[] arr = "A".toCharArray();
        System.out.println(leastInterval(arr, 2));
    }
}
