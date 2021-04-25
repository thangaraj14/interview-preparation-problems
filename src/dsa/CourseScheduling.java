package dsa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/course-schedule/
 */
public class CourseScheduling {

    Map<Integer, List<Integer>> graph = new HashMap<>();
    int[][] p;

    public boolean canFinishI(int numCourses, int[][] prerequisites) {
        p = prerequisites;
        for (int i = 0; i < p.length; i++) {
            List<Integer> l = graph.getOrDefault(p[i][1], new ArrayList<>());
            l.add(p[i][0]);
            graph.put(p[i][1], l);
        }
        for (int i = 0; i < numCourses; i++) {
            if (!vis.contains(i)) {
                if (cycleExist(i)) {
                    return false;
                }
            }
        }
        return true;
    }

    Set<Integer> vis = new HashSet<>();
    Set<Integer> recur = new HashSet<>();

    public boolean cycleExist(int u) {
        if (!graph.containsKey(u)) {
            return false;
        }
        if (recur.contains(u)) {
            return true;
        }
        if (vis.contains(u)) {
            return false;
        }
        recur.add(u);
        for (int i : graph.get(u)) {
            if (!vis.contains(i)) {
                if (cycleExist(i)) {
                    return true;
                }
            }
        }
        recur.remove(u);
        vis.add(u);
        return false;
    }

    public static void main(String[] args) {

    }
}
