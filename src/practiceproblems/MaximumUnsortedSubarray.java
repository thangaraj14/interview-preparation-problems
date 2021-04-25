package practiceproblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
/**
 * Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.
 *
 * You need to find the shortest such subarray and output its length.
 *
 * Example 1:
 * Input: [2, 6, 4, 8, 10, 9, 15]
 * Output: 5
 * Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
 */
public class MaximumUnsortedSubarray {

    public static ArrayList<Integer> subarraySort(final ArrayList<Integer> A) {

        final ArrayList<Integer> list = new ArrayList<>();
        int start = -1;
        int end = -1;

        // from left
        for (int i = 1; i < A.size(); ++i) {
            if (A.get(i) < A.get(i - 1)) {
                start = i - 1;
                break;
            }
        }

        // fully sorted
        if (start == -1) {
            list.add(-1);
            return list;
        }

        // from right
        for (int i = A.size() - 2; i >= 0; --i) {
            if (A.get(i) > A.get(i + 1)) {
                end = i + 1;
                break;
            }
        }
        //  [1, 3, 2, 0, -1, 7, 10] 
        // the initial finding gives you 3 and -1 however the original sort array is
        // [1, -1, 0, 2, 3, 7, 10], 
        //The problem here is that the smallest number of our subarray is ‘-1’
        // which dictates that we need to include more numbers from the beginning of the array
        // We will have a similar problem 
        //if the maximum of the subarray is bigger than some elements at the end of the array
        // find min and max in the range [start, end]
        int min = A.get(start);
        int max = A.get(start);
        for (int i = start; i <= end; ++i) {
            min = Math.min(min, A.get(i));
            max = Math.max(max, A.get(i));
        }

        for (int i = 0; i < start; ++i) {
            if (A.get(i) > min) {
                start = i;
                break;
            }
        }

        for (int i = A.size() - 1; i >= end + 1; --i) {
            if (A.get(i) < max) {
                end = i;
                break;
            }
        }

        list.add(start);
        list.add(end);

        return list;
    }

    public static void main(final String[] args) {
        //1, 1, 10, 10, 15, 10, 15, 10,10, 15, 10, 15
        //(1, 3, 2, 4, 5);
        //4, 15, 4, 4, 15, 18, 20
        //2, 6, 1, 8, 10, 9, 15
        final List<Integer> result = subarraySort(new ArrayList<>(Arrays.asList(4, 15, 4, 4, 15, 18, 20)));
        result.stream().forEach(System.out::println);
    }
}
