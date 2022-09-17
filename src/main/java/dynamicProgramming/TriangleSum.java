package dynamicProgramming;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/triangle/
 */
public class TriangleSum {
    static Integer[][] cache = null;

    public static int minimumTotal(List<List<Integer>> triangle) {
        cache = new Integer[triangle.size()][triangle.size()];

        return recursionUtil(triangle, 0, 0);
    }

    public static int recursionUtil(List<List<Integer>> triangle, int triangleIndex, int subIndex) {
        if (triangleIndex >= triangle.size()) return 0;

        if (cache[triangleIndex][subIndex] != null) return cache[triangleIndex][subIndex];

        // recursively take from next rom same column
        int left = recursionUtil(triangle, triangleIndex + 1, subIndex);

        // recursively take for next row, next column
        int right = recursionUtil(triangle, triangleIndex + 1, subIndex + 1);

        cache[triangleIndex][subIndex] = Math.min(left, right) + triangle.get(triangleIndex).get(subIndex);

        return cache[triangleIndex][subIndex];
    }

    public static int minimumTotalBottomUp(List<List<Integer>> triangle) {

        int[][] dp = new int[triangle.size()][triangle.size()];
        // the commented code is to optimise the O(N^2) space
        //  int[]dp = new int[triangle.size()];
        //    int[]dp1 = new int[triangle.size()];

        //Remember base case is just returning leaf nodes
        for (int i = 0; i < triangle.size(); i++) {
            dp[triangle.size() - 1][i] = triangle.get(triangle.size() - 1).get(i);
        }

        for (int row = triangle.size() - 2; row >= 0; row--) {
            for (int pos = 0; pos < triangle.get(row).size(); pos++) {
                //dp1[pos] = triangle.get(row).get(pos) + Math.min(dp[pos+1], dp[pos]);
                dp[row][pos] = triangle.get(row).get(pos) + Math.min(dp[row + 1][pos], dp[row + 1][pos + 1]);
            }
            //dp = dp1;
        }

        return dp[0][0];
    }

    public static void main(String[] args) {
        List<List<Integer>> triangle = Arrays.stream(new Integer[][]{{2}, {3, 4}, {6, 5, 7}, {4, 1, 8, 3}})
                .map(Arrays::asList)
                .collect(Collectors.toList());
        minimumTotalBottomUp(triangle);
    }
}
