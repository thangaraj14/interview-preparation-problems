package practiceproblems;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/k-diff-pairs-in-an-array/
 */
public class FindPairs {

    public static int findPairs(int[] nums, int k) {
        if (k < 0) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        int count = 0;
        for (int m : map.keySet()) {
            if (k == 0) {
                if (map.get(m) >= 2) count++;
            } else {
                if (map.containsKey(m + k))
                    count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(findPairs(new int[]{3, 1, 4, 1, 5}, 0));
    }
}
