package dsa;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/partition-labels/
 */
class PartitionLabel {

    public List<Integer> partitionLabels(String str) {

        int[] lastAppearance = new int[26];
        for (int i = 0; i < str.length(); ++i)
            // find lastAppearance appearance of the char
            lastAppearance[str.charAt(i) - 'a'] = i;

        int j = 0;
        int anchor = 0;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < str.length(); ++i) {
            // find the max of j and lastAppearance appearance of str[i]
            j = Math.max(j, lastAppearance[str.charAt(i) - 'a']);
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