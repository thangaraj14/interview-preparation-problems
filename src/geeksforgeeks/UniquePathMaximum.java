package geeksforgeeks;

/**
 * find the maximum score of a path starting at [0, 0] and ending at [r-1, c-1]. The score of a path is the minimum value in that path.
 * <p>
 * Input:
 * [[1, 2, 3]
 * [4, 5, 1]]
 * <p>
 * Output: 4
 * Explanation:
 * Possible paths:
 * 1-> 2 -> 3 -> 1
 * 1-> 2 -> 5 -> 1
 * 1-> 4 -> 5 -> 1
 * So min of all the paths = [2, 2, 4]. Note that we don't include the first and final entry.
 * https://leetcode.com/discuss/interview-question/383669/
 */
public class UniquePathMaximum {

    public static void main(String[] args) {
        int[][] matrix = { { 6, 7, 8 }, { 5, 4, 2 }, { 8, 7, 6 } };
        System.out.println(findMaximumOfUniquePath(matrix));
    }

    private static int findMaximumOfUniquePath(int[][] matrix) {
        for (int i = 1; i < matrix[0].length; i++) {
            matrix[0][i] = Math.min(matrix[0][i], matrix[0][i - 1]);
        }

        for (int j = 1; j < matrix.length; j++) {
            matrix[j][0] = Math.min(matrix[j][0], matrix[j - 1][0]);
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                matrix[i][j] = Math.max(Math.min(matrix[i - 1][j], matrix[i][j]),
                        Math.min(matrix[i][j - 1], matrix[i][j]));
            }
        }
        return matrix[matrix.length - 1][matrix[0].length - 1];
    }
}