package practiceproblems;

import java.util.Arrays;

/**
 * TODO
 * https://leetcode.com/problems/count-submatrices-with-all-ones/
 */
public class NumberOfSubMatrices {
    public static int numSubmat(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        int brr[][] = new int[n][m];

        // these are the loops for making a matrix brr[][]
        // where index brr[i][j] will contain continuous number of 1
        //starting from index j to n-1
        for (int i = 0; i < n; ++i) {
            brr[i][0] = arr[i][0];
            for (int j = 1; j < m; ++j) {
                if (arr[i][j] == 1) {
                    brr[i][j] = 1 + brr[i][j - 1];
                }
            }
        }
        // these are the loops for finding number of submatrices of all 1
        // starting from a particular fixed index (i,j) and adding it to answer
        // we do this step for all i,j

        for (int i = 0; i < n; ++i) {
            System.out.println(Arrays.toString(brr[i]));
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                ans += brr[i][j];
                int min = brr[i][j];
                for (int ii = i + 1; ii < n; ++ii) {
                    min = Math.min(min, brr[ii][j]);
                    ans += min;
                }
            }
        }
        return ans;

    }

    public static void main(String[] args) {
        numSubmat(new int[][]{{1, 0, 1},
                              {1, 1, 0},
                              {1, 1, 0}});
    }
}
