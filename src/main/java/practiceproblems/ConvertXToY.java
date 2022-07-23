package practiceproblems;
// Java program to find minimum
// number of steps needed to 
// convert a number x into y 
// with two operations allowed : 
// (1) multiplication with 2 
// (2) subtraction with 1. 

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * https://www.geeksforgeeks.org/minimum-number-operation-required-convert-number-x-y/
 */

public class ConvertXToY {

    private static int minOperations(int src, int target) {

        Set<Steps> visited = new HashSet<>();
        LinkedList<Steps> queue = new LinkedList<>();

        Steps node = new Steps(src, 0);

        queue.offer(node);
        visited.add(node);

        while (!queue.isEmpty()) {
            Steps temp = queue.poll();
            visited.add(temp);

            if (temp.val == target) {
                return temp.steps;
            }

            int mul = temp.val * 2;
            int sub = temp.val - 1;

            // given constraints
            if (mul > 0 && mul < 1000) {
                Steps nodeMul = new Steps(mul, temp.steps + 1);
                queue.offer(nodeMul);
            }
            if (sub > 0 && sub < 1000) {
                Steps nodeSub = new Steps(sub, temp.steps + 1);
                queue.offer(nodeSub);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        // int x = 2, y = 5;
        int x = 4, y = 7;
        System.out.println(minOperations(x, y));
    }

    static class Steps {
        int val;
        int steps;

        public Steps(int val, int steps) {
            this.val = val;
            this.steps = steps;
        }
    }
}


