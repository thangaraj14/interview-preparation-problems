package geeksforgeeks;

import java.util.*;

/**
 * https://leetcode.com/problems/task-scheduler/
 */

public class TaskLeastInterval {

    public static int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < tasks.length; i++) {
            map.put(tasks[i], map.getOrDefault(tasks[i], 0) + 1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(
                (a, b) -> Integer.compare(b.getValue(), a.getValue()));

        pq.addAll(map.entrySet());

        int count = 0;
        while (!pq.isEmpty()) {
            int k = n + 1;
            List<Map.Entry> tempList = new ArrayList<>();
            while (k > 0 && !pq.isEmpty()) {
                Map.Entry<Character, Integer> top = pq.poll();
                top.setValue(top.getValue() - 1);
                tempList.add(top);
                k--;
                count++;
            }

            for (Map.Entry<Character, Integer> e : tempList) {
                if (e.getValue() > 0) {
                    pq.add(e); // add valid tasks which is more than 0
                }
            }

            if (pq.isEmpty()) {
                break;
            }
            count = count + k; // if k > 0, then it means we need to be idle
        }
        return count;
    }

    public static void main(String[] args) {
        char[] arr = "AAAAABBBG".toCharArray();
        System.out.println(leastInterval(arr, 3));
    }
}
