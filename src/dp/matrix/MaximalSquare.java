package dp.matrix;

/**
 * https://leetcode.com/problems/maximal-square/
 * <p>
 * https://www.youtube.com/watch?v=_Lf1looyJMU&ab_channel=TusharRoy-CodingMadeSimple
 */
public class MaximalSquare {

    public static int maximalSquare(char[][] a) {
        if (a.length == 0) {
            return 0;
        }
        int m = a.length;
        int n = a[0].length;
        int result = 0;
        int[][] b = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (a[i - 1][j - 1] == '1') {
                    b[i][j] = Math.min(Math.min(b[i][j - 1], b[i - 1][j - 1]), b[i - 1][j]) + 1;
                    result = Math.max(b[i][j], result); // update result
                }
            }
        }
        return result * result;
    }

    public static void main(String[] args) {
        int i = maximalSquare(
                new char[][] { { '1', '0', '1', '0', '0' }, { '1', '0', '1', '1', '1' }, { '1', '1', '1', '1', '1' },
                        { '1', '0', '0', '1', '0' } });
        System.out.println(i);
    }
}
