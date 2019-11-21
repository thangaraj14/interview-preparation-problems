package geeksforgeeks;

/*
https://leetcode.com/discuss/interview-question/383669/
*/

public class UniquePathMaximum {

    public static void main(String[] args) {
        int[][] matrix = {{6, 7, 8}, {5, 4, 2}, {8, 7, 6}};
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
                matrix[i][j] = Math.max(Math.min(matrix[i - 1][j], matrix[i][j]), Math.min(matrix[i][j - 1], matrix[i][j]));
            }
        }
        return matrix[matrix.length - 1][matrix[0].length - 1];
    }


}