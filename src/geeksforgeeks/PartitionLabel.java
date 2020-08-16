package geeksforgeeks;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/partition-labels/
 * A string S of lowercase English letters is given.
 * We want to partition this string into as many parts as possible
 * so that each letter appears in at most one part, and return a list of integers representing the size of these parts.
 *
 * Input: S = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 */
class PartitionLabel {

    public List<Integer> partitionLabels(String str) {

        int[] last = new int[26];
        for (int i = 0; i < str.length(); ++i)
            // find last appearance of the char
            last[str.charAt(i) - 'a'] = i;

        int j = 0;
        int anchor = 0;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < str.length(); ++i) {
            // find the max of j and last appearance of str[i]
            j = Math.max(j, last[str.charAt(i) - 'a']);
            if (i == j) {
                // it is (1+i-anchor)
                int length = i - anchor + 1;
                ans.add(length);
                anchor = i + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String S = "ababcbacadefegdehijhklij";
        PartitionLabel partition = new PartitionLabel();
        System.out.println(partition.partitionLabels(S));

    }
}