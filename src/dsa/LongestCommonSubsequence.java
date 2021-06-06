package dsa;

public class LongestCommonSubsequence {

    public static void main(String[] args) {
        String str1 = "ABCD";
        String str2 = "AEDB";

        System.out.println(getLCS(str1.toCharArray(), str2.toCharArray()));
    }

    private static int getLCS(char[] str1, char[] str2) {

        int[][] temp = new int[str1.length + 1][str2.length + 1];
        int max = 0;
        for (int i = 1; i < temp.length; i++) {
            for (int j = 1; j < temp[i].length; j++) {
                if (str1[i - 1] == str2[j - 1]) {
                    temp[i][j] = temp[i - 1][j - 1] + 1;
                } else {
                    temp[i][j] = Math.max(temp[i][j - 1], temp[i - 1][j]);
                }
                if (temp[i][j] > max) {
                    max = temp[i][j];
                }
            }
        }
        return max;
    }

    static int lcs(char[] X, char[] Y, int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        if (X[m - 1] == Y[n - 1]) {
            return 1 + lcs(X, Y, m - 1, n - 1);
        } else {
            return Math.max(lcs(X, Y, m, n - 1), lcs(X, Y, m - 1, n));
        }
    }

}
