package dp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * https://leetcode.com/problems/unique-binary-search-trees/discuss/1071904/optimal-binary-search-tree
 */
class Solution_2 {
    List<Element> elementList = new ArrayList<>();
    int[][][] dp;

    private int binaryTree(int[] nodes, int[] freq) {
        dp = new int[nodes.length][nodes.length][nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            elementList.add(new Element(nodes[i], freq[i]));
        }
        elementList.sort(Comparator.comparing(Element::getVal));
        return binaryTree(0, nodes.length - 1, 1);
    }

    private int binaryTree(int left, int right, int level) {
        if (left > right) {
            return 0;
        }
        if (left == right) {
            return level * elementList.get(left).freq;
        }
        if (dp[left][right][level] != 0) {
            return dp[left][right][level];
        }
        int min = Integer.MAX_VALUE;
        for (int mid = left; mid <= right; mid++) {
            int leftValue = binaryTree(left, mid - 1, level + 1);
            int rightValue = binaryTree(mid + 1, right, level + 1);
            min = Math.min(min, (elementList.get(mid).freq * level) + leftValue + rightValue);
        }
        return dp[left][right][level] = min;
    }

    public static void main(String[] args) {
        int[] input = { 10, 12, 20, 35, 46 };
        int[] freq = { 34, 8, 50, 21, 16 };
        Solution_2 sol = new Solution_2();
        int i = sol.binaryTree(input, freq);
        System.out.println(i);
    }
}

class Element {
    int val;
    int freq;

    public int getVal() {
        return val;
    }

    public Element(int node, int i) {
        val = node;
        freq = i;
    }
}