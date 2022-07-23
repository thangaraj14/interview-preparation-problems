package graph.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/employee-importance/
 */
public class EmployeeImportance {

    public int getImportance(List<Employee> employees, int id) {

        Deque<Employee> stack = new ArrayDeque<>();
        int result = 0;
        Set<Integer> set = new HashSet<>();

        Map<Integer, Employee> cache = new HashMap<>();
        for (Employee e : employees) {
            cache.put(e.id, e);
        }

        stack.push(cache.get(id));

        while (!stack.isEmpty()) {

            Employee emp = stack.pop();
            if (set.contains(emp.id)) continue;
            set.add(emp.id);

            result += emp.importance;

            for (Integer e : emp.subordinates) {
                stack.push(cache.get(e));
            }
        }

        return result;
    }

    static class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }
}
