package practiceproblems.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class Pattern132 {

    // record minimum till j and iterate from the end to find a max value which is
    // greater the minimum(i) and smaller than j
    // O(N^2)
    public boolean find132pattern(int[] nums) {
        int min = Integer.MAX_VALUE; // stores the min value till j
        for (int j = 0; j < nums.length; j++) {
            min = Math.min(nums[j], min);
            if (min == nums[j]) continue; // if min and j are same move to next element

            for (int k = nums.length - 1; k > j; k--) {
                if (min < nums[k] && nums[k] < nums[j]) return true; // condition to be satisfied
            }
        }
        return false;
    }

    /**
     * Create a stack and initialize a variable second with INT_MIN value.
     * Start traversing from the end of array.
     * Check if the current number is lesser than second. If it is, then it means our 132 pattern is satisfied as the stack is taking care of the 32 pattern and the current number satisfies the entire pattern. So return true.
     * If the above is not true, update the peak value in the stack. Keep popping from the stack until stack is empty OR the top value is greater than the current value.
     * Push the current number into the stack.
     * If the loop terminates, it means that the pattern was not found in the array. So, return false.
     * <p>
     * Take the sample input as [3, 6, 4, 1, 2]
     * Basically, the top of stack is containing the highest number so far, i.e. 3 and
     * second is containing the second highest number after the highest number, i.e. 2.
     * So, this satisfies the 32 pattern. Now, we will just keep updating second and stack top when we encounter a number
     * which is greater than the highest number.
     */
    public static boolean find132patternEffective(int[] nums) {
        Deque<Integer> maxStack = new ArrayDeque<>();
        int minValue = Integer.MIN_VALUE;

        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] < minValue) return true;
            while (!maxStack.isEmpty() && maxStack.peek() < nums[i]) {
                minValue = maxStack.pop();
            }
            maxStack.push(nums[i]);
        }

        return false;
    }

    public boolean find132patternMinArr(int[] nums) {
        int n = nums.length;
        int[] mins = new int[n];
        mins[0] = nums[0];
        for (int i = 1; i < n - 1; i++) {
            mins[i] = Math.min(nums[i], mins[i - 1]);
        }
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = n - 1; i > 0; i--) {
            while (!stk.isEmpty() && nums[i] > stk.peek()) {
                if (stk.peek() > mins[i - 1]) return true;
                stk.pop();
            }
            stk.push(nums[i]);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(find132patternEffective(new int[]{3, 5, 0, 3, 4}));
    }
}
