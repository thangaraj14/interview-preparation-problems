package geeksforgeeks;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        Map<Integer, List<Integer>> map = new HashMap<>(); // Courses that depend on the key
        int[] degrees = new int[numCourses]; //  # of prerequisites for course i
        Queue<Integer> queue = new ArrayDeque<>(); // Used to find dependants and decrease their outdegree

        for (int[] pre : prerequisites) {
            List<Integer> tempList = map.getOrDefault(pre[1], new ArrayList<>());
            tempList.add(pre[0]);
            degrees[pre[0]]++;
            map.put(pre[1], tempList);
        }

        for (int i = 0; i < degrees.length; i++) {
            if (degrees[i] == 0) {
                queue.offer(i);
            }
        }

        int count = 0;
        while (!queue.isEmpty()) {
            int temp = queue.poll();
            if (degrees[temp] == 0) {
                count++; // if cond for duplicates
            }
            if (!map.containsKey(temp)) {
                continue;
            }
            for (int i : map.get(temp)) {
                if (--degrees[i] == 0) {
                    queue.offer(i);
                }
            }
        }

        return count == numCourses;
    }
}