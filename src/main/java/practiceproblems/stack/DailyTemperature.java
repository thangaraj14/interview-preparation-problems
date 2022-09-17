package practiceproblems.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class DailyTemperature {
    public static int[] dailyTemperatures(int[] temperatures) {
        if (temperatures.length == 1) return new int[]{0};
        Deque<Pair> stack = new ArrayDeque<>();
        int[] result = new int[temperatures.length];
        stack.push(new Pair(temperatures[temperatures.length - 1], 0));
        result[temperatures.length - 1] = 0;
        for (int i = temperatures.length - 2; i >= 0; i--) {
            int count = 0;
            while (!stack.isEmpty() && stack.peek().temp <= temperatures[i]) {
                count += stack.pop().count;
            }
            if (!stack.isEmpty() && stack.peek().temp > temperatures[i]) count++;

            // for case [55,38,53,81,61,93,97,32,43,78] 97 comes in middle then we have to eliminate all entries
            // but the count is zero
            if (stack.isEmpty()) count = 0;
            result[i] = count;
            stack.push(new Pair(temperatures[i], count));
        }

        return result;
    }

    public int[] dailyTemperaturesOptimised(int[] temperatures) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] ret = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int idx = stack.pop();
                ret[idx] = i - idx;
            }
            stack.push(i);
        }
        return ret;
    }

    public static void main(String[] args) {
        int[][] inputs = new int[][]{{73, 74, 75, 71, 69, 72, 76, 73,
                30, 40, 50, 60,
                30, 60, 90,
                55, 38, 53, 81, 61, 93, 97, 32, 43, 78}};
        for (int[] input : inputs)
            System.out.println(Arrays.toString(dailyTemperatures(input)));
    }

    private static class Pair {
        int temp;
        int count;

        public Pair(int temp, int count) {
            this.temp = temp;
            this.count = count;
        }
    }
}
