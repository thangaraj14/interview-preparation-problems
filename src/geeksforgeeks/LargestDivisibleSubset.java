package geeksforgeeks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies:
 * Si % Sj = 0 or Sj % Si = 0.
 * If there are multiple solutions, return any subset is fine.
 * Input: [2,3,4,6,10,8,24]
 * Output: [2,4,8,24] each pair's modulo is 0
 * <p>
 * https://leetcode.com/problems/largest-divisible-subset/
 * https://www.youtube.com/watch?v=8tDM_pfmlrw&ab_channel=KnowledgeCenter
 */
public class LargestDivisibleSubset {

    /**
     * if a%b==0 means a>b, if b>a then the ans is b itself
     * inorder to have that we need to sort the array in increasing order
     * at first each val is ans to itself, then we come from last so a is higher in a%b
     * [2,   3,   4,  6,     8,   10,  24]
     * {2}  {3} {4}  {6}   {8}  {10} {24}
     * {8,24}
     * <p>
     * {6,24}
     * <p>
     * {4,8,24}
     * <p>
     * {3,6,24}
     * <p>
     * {2,4,8,24}
     */
    public static List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }

        Arrays.sort(nums);
        List<Integer>[] result = new ArrayList[nums.length];

        int maxLength = 0;
        int resultIndex = -1;
        List<Integer> list;

        for (int i = nums.length - 1; i >= 0; i--) {
            result[i] = new ArrayList<>(); // every element is an answer to itself
            list = new ArrayList<>();
            result[i].add(nums[i]);
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] % nums[i] == 0) { // when mod is zero, check for greater list size of subset that
                    // position has
                    if (result[j].size() > list.size()) { // this is to take even if 1 element is at j position
                        list = result[j]; // the reason we take list is consider 4,8,24 when i is at 4 and j is 8 mod is 0 means 4%24 is also zero
                    }
                }
            }
            result[i].addAll(list);
            if (result[i].size() > maxLength) {
                maxLength = result[i].size();
                resultIndex = i;
            }
        }
        Collections.sort(result[resultIndex]);
        return result[resultIndex];
    }

    public static void main(String[] args) {
        largestDivisibleSubset(new int[] { 1, 2, 4, 8 }).forEach(System.out::println);
    }
}
