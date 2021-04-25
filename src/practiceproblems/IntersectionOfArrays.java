package practiceproblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * https://leetcode.com/problems/intersection-of-two-arrays-ii/discuss/82241/AC-solution-using-Java-HashMap
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2,2]
 * Example 2:
 * <p>
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [4,9]
 */
public class IntersectionOfArrays {
    public static void main(String[] args) {
        IntersectionOfArrays intersectionOfArrays = new IntersectionOfArrays();
        int[] nums1 = { 4, 4, 9, 5 };
        int[] nums2 = { 9, 4, 9, 4, 2, 3 };
        System.out.println(Arrays.toString(intersectionOfArrays.intersect(nums1, nums2)));
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        //The first question is relatively easy, create a hashmap base on number frequency of nums1(whichever one is longer).

        // Then for every element of nums2, look upon the hashmap. If we found an intersection, deduct by 1 to avoid duplicate.
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            map.put(nums1[i], map.getOrDefault(nums1[i], 1));
        }

        for (int i = 0; i < nums2.length; i++) {
            if (map.containsKey(nums2[i]) && map.get(nums2[i]) > 0) {
                result.add(nums2[i]);
                map.put(nums2[i], map.get(nums2[i]) - 1);
            }
        }

        int[] r = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            r[i] = result.get(i);
        }

        return r;
    }

    //What if the given array is already sorted? How would you optimize your algorithm?
     // Classic two pointer iteration, i points to nums1 and j points to nums2.
    // Because a sorted array is in ascending order, so if nums1[i] > nums[j], we need to increment j, and vice versa.
    // Only when nums1[i] == nums[j], we add it to the result array. Time Complexity O(max(N, M))
    public int[] intersectSorted(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int n = nums1.length, m = nums2.length;
        int i = 0, j = 0;
        List<Integer> list = new ArrayList<>();
        while(i < n && j < m){
            int a = nums1[i], b= nums2[j];
            if(a == b){
                list.add(a);
                i++;
                j++;
            }else if(a < b){
                i++;
            }else{
                j++;
            }
        }
        int[] ret = new int[list.size()];
        for(int k = 0; k < list.size();k++) ret[k] = list.get(k);
        return ret;
    }
}