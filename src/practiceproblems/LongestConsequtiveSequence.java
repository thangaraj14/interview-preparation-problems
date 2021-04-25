package practiceproblems;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given an unsorted array of integers,
 * find the length of the longest consecutive elements sequence.
 *
 * Your algorithm should run in O(n) complexity.
 * Input: [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4].
 * Therefore its length is 4.
 */
class LongestConsequtiveSequence {

    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int max = 1;
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            set.add(i);
        }
        // have a set, go backwards and remove entries, go forward remove entries and calculate Max
        // without removing entries, runtime would be too much
        for (Integer i : nums) {
            int num = i;
            int count = 1;
            // looking left
            while (set.contains(--num)) {
                count++;
                set.remove(num);
            }
            num = i;
            while (set.contains(++num)) {
                count++;
                set.remove(num);
            }

            max = Math.max(max, count);
        }

        return max;
    }

    public int longestConsecutiveSorting(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        Arrays.sort(nums);

        int longestStreak = 1;
        int currentStreak = 1;

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] != nums[i+1]) { // avoid duplicate
                if (nums[i] + 1 == nums[i+1]) { // if increasing increase streak count else reset
                    currentStreak += 1;
                }
                else {
                    longestStreak = Math.max(longestStreak, currentStreak);
                    currentStreak = 1;
                }
            }
        }

        return Math.max(longestStreak, currentStreak);
    }
}