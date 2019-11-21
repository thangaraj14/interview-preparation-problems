package geeksforgeeks;

/*https://leetcode.com/problems/unique-paths-ii/
  https://leetcode.com/problems/unique-paths/*/
public class UniquePath {

    public static void main(String[] args) {
        System.out.println(uniquePathI(3, 2));

        int[][] matrix = {{0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}};

        System.out.println(uniquePathII(matrix));

    }

    private static int uniquePathI(int row, int col) {
        int[][] dp = new int[row][col];

        for (int i = 0; i < col; i++) {
            dp[0][i] = 1;
        }

        for (int j = 0; j < row; j++) {
            dp[j][0] = 1;
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[row - 1][col - 1];
    }


    private static int uniquePathII(int[][] mat) {
        int[][] dp = new int[mat.length][mat[0].length];
        for (int i = 0; i < mat[0].length; i++) {
            if (mat[0][i] != 1) {
                dp[0][i] = 1;
            }
        }

        for (int i = 0; i < mat.length; i++) {
            if (mat[i][0] != 1) {
                dp[i][0] = 1;
            }
        }

        for (int i = 1; i < mat.length; i++) {
            for (int j = 1; j < mat[0].length; j++) {
                if (mat[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }

            }
        }
        return dp[mat.length - 1][mat[0].length - 1];
    }
}
