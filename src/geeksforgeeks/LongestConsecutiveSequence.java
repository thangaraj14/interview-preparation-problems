package geeksforgeeks;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/longest-consecutive-sequence/
 */
class LongestConsecutiveSequence {

    public static int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int max = 1;
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            set.add(i);
        }

        for (Integer i : nums) {
            int num = i;
            int count = 1;

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

    public static void main(String[] args) {
        System.out.println(longestConsecutive(new int[] { 100, 4, 200, 1, 3, 2 }));
    }
}