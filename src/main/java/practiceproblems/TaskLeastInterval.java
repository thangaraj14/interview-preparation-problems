package practiceproblems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/task-scheduler/
 *
 * tricky priority queue
 */


public class TaskLeastInterval {

    public static int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> cache = new HashMap<>();

        for (char c : tasks) {
            cache.put(c, cache.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Pair> queue = new PriorityQueue<>((a, b) -> Integer.compare(b.freq, a.freq));

        for (char key : cache.keySet()) {
            queue.offer(new Pair(key, cache.get(key)));
        }
        int result = 0;
        // At each iteration, we process at most 'n' elements,
        // and move forwards exactly n+1 in time (regardless of how many elements we processed:
        // Read the topmost from the queue and increment the time. Add it to a temp list to be added later.
        // Add the element back to the queue from the temp list if count is > 0.
        // if al elements are done, we're done too.
        // Move time forward by n + 1
        // Return time in the end.
        while (!queue.isEmpty()) {
            int k = n + 1;  //n slots for the gap and 1 for the task itself, At each iteration, we process at most 'n' elements,
                            // and move forwards exactly n+1 in time
            List<Pair> tempList = new ArrayList<>();

            while (k > 0 && !queue.isEmpty()) {
                Pair temp = queue.poll();  // most frequency task
                temp.freq -= 1; // decrease frequency, meaning it got executed
                tempList.add(temp); // collect task to add back to queue
                result++; //successfully executed task
                k--;
            }

            for (Pair t : tempList) {
                if (t.freq > 0) queue.offer(t); // add valid tasks
            }

            if (queue.isEmpty()) break;

            result += k; // if k > 0, then it means we need to be idle

        }
        return result;
    }

    public static void main(String[] args) {
        char[] arr = "A".toCharArray();
        System.out.println(leastInterval(arr, 2));
    }

    static class Pair {
        char task;
        int freq;

        public Pair(char task, int freq) {
            this.task = task;
            this.freq = freq;
        }
    }
}
