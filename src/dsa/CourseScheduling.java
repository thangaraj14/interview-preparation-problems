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

    Map<Integer, List<Integer>> graphMap = new HashMap<>();
    Set<Integer> visited = new HashSet<>();
    Set<Integer> recur = new HashSet<>();

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        for (int i = 0; i < prerequisites.length; i++) {
            List<Integer> list = graphMap.getOrDefault(prerequisites[i][1], new ArrayList<>());
            list.add(prerequisites[i][0]);
            graphMap.put(prerequisites[i][1], list);
        }
        for (int i = 0; i < numCourses; i++) {
            if (!visited.contains(i)) {
                if (cycleExist(i)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean cycleExist(int u) {
        if (!graphMap.containsKey(u) || visited.contains(u)) {
            return false;
        }
        if (recur.contains(u)) {
            return true;
        }
        recur.add(u);
        for (int i : graphMap.get(u)) {
            if (!visited.contains(i)) {
                if (cycleExist(i)) {
                    return true;
                }
            }
        }
        recur.remove(u);
        visited.add(u);
        return false;
    }

    public static void main(String[] args) {
        CourseScheduling courseScheduling = new CourseScheduling();
        boolean b = courseScheduling.canFinish(4, new int[][] { { 0, 1 }, { 0, 2 }, { 0, 3 }, { 2, 3 } });
        System.out.println(b);
    }
}
