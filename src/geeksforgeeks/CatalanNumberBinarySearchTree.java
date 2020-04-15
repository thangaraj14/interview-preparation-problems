package geeksforgeeks;

/**
 * https://www.youtube.com/watch?v=YDf982Lb84o&t=277s
 * <p>
 * https://leetcode.com/problems/unique-binary-search-trees/
 */
public class CatalanNumberBinarySearchTree {

    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.println("dp[" + i + "] +=  dp[" + (j - 1) + "] * dp[" + (i - j) + "]");
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    public static void main(String args[]) {
        CatalanNumberBinarySearchTree cnt = new CatalanNumberBinarySearchTree();
        System.out.println(cnt.numTrees(3));
    }

  /*  public int numTrees(int n) {
        return trees(1, n);
    }*/

    int trees(int lo, int hi) {
        if (lo >= hi) {
            return 1;
        }
        int total = 0;
        for (int i = lo; i <= hi; i++)
            total += trees(lo, i - 1) * trees(i + 1, hi);
        return total;
    }
}
