package geeksforgeeks;

import java.util.ArrayList;
import java.util.List;

class PartitionLabel {
    public List<Integer> partitionLabels(String S) {
        int[] last = new int[26];
        for (int i = 0; i < S.length(); ++i)
            last[S.charAt(i) - 'a'] = i;

        int j = 0;
        int anchor = 0;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < S.length(); ++i) {
            j = Math.max(j, last[S.charAt(i) - 'a']);
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