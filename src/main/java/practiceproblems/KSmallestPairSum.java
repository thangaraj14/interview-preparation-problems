package practiceproblems;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * tricky priority queue
 * https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
 *
 * Basic idea: Use min_heap to keep track on next minimum pair sum, and we only need to maintain K possible candidates in the data structure.
 *
 * Some observations: For every numbers in nums1, its best partner(yields min sum) always strats from nums2[0] since arrays are all sorted;
 * And for a specific number in nums1, its next candidate sould be [this specific number] + nums2[current_associated_index + 1], unless out of boundary;)
 */
public class KSmallestPairSum {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < nums1.length && i < k; i++) {
            pq.add(new int[]{nums1[i] + nums2[0], i, 0});
        }
        List<List<Integer>> ans = new LinkedList<>();
        while (!pq.isEmpty() && k > 0) {
            int[] peek = pq.remove();
            int i = peek[1], j = peek[2];
            ans.add(List.of(nums1[i], nums2[j]));
            if (j < nums2.length - 1)
                pq.add(new int[]{nums1[i] + nums2[j + 1], i, j + 1});
            k--;
        }
        return ans;
    }
}
