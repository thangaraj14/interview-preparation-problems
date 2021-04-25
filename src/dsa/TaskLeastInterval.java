package dsa;

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
            List<Map.Entry<Character, Integer>> tempList = new ArrayList<>();
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
        char[] arr = "AAABBB".toCharArray();
        System.out.println(leastInterval1(arr, 2));
    }

    public static int leastInterval1(char[] tasks, int n) {

        PriorityQueue<Task> pq = new PriorityQueue<>(Comparator.comparingInt(Task::getLength));


        Map<Character, Integer> map = new HashMap<>();

        for (char ch : tasks) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        for (Map.Entry<Character, Integer> mEntry : map.entrySet()) {
            pq.offer(new Task(mEntry.getKey(), mEntry.getValue()));
        }

        int k = n + 1;
        int count = 0;

        while (true) {
            List<Task> taskList = new ArrayList<>();

            while (k > 0 && !pq.isEmpty()) {

                Task task = pq.remove();
                task.length = task.length - 1;
                taskList.add(task);
                k--;
                count++;
            }

            for (Task tsk : taskList) {
                if (tsk.length > 0) {
                    pq.offer(tsk);
                }
            }
            if (!pq.isEmpty()) {
                count = count + k;
                k = n + 1;
            } else {
                return count;
            }
        }
    }
}

class Task {

    char ch;
    int length;

    public Task(char ch, int length) {
        this.ch = ch;
        this.length = length;
    }

    public int getLength() {
        return length;
    }
}
