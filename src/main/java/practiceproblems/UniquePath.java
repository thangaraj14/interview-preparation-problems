package practiceproblems;

/*https://leetcode.com/problems/unique-paths-ii/
  https://leetcode.com/problems/unique-paths/*/
public class UniquePath {

    public static void main(String[] args) {
        System.out.println(uniquePathI(3, 2));

        int[][] matrix = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};

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

    /**
     * Now consider if some obstacles are added to the grids. How many unique paths would there be?
     * Input:
     * [
     * [0,0,0],
     * [0,1,0],
     * [0,0,0]
     * ]
     * Output: 2
     * Explanation:
     * There is one obstacle in the middle of the 3x3 grid above.
     * There are two ways to reach the bottom-right corner:
     * 1. Right -> Right -> Down -> Down
     * 2. Down -> Down -> Right -> Right
     */
    private static int uniquePathII(int[][] obstacleGrid) {

        if (obstacleGrid[0][0] == 1)
            return 0;
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];

        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                dp[i][0] = 0;
                break;
            } else {
                dp[i][0] = 1;
            }
        }

        for (int j = 0; j < n; j++) {
            if (obstacleGrid[0][j] == 1) {
                dp[0][j] = 0;
                break;
            } else {
                dp[0][j] = 1;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];

    }

    Integer[][] cache;

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        cache = new Integer[obstacleGrid.length][obstacleGrid[0].length];
        return recursionHelper(obstacleGrid, 0, 0);
    }

    public int recursionHelper(int[][] arr, int i, int j) {
        if (i < 0 || j < 0 || i >= arr.length || j >= arr[0].length || arr[i][j] == 1) return 0;

        if (i == arr.length - 1 && j == arr[0].length - 1) return 1;

        if (cache[i][j] != null) return cache[i][j];

        return cache[i][j] = recursionHelper(arr, i, j + 1) + recursionHelper(arr, i + 1, j);

    }
}
