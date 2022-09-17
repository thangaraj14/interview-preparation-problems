package dynamicProgramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://www.youtube.com/watch?v=0pTN0qzpt-Y
 */
public class CatalanNumberBinarySearchTree {
    public int countTrees(int n) {
        int[] T = new int[n + 1];
        T[0] = 1;
        T[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                int i1 = T[j] * T[i - j - 1];
                System.out.println("T[" + j + "]" + "*" + "T[" + (i - j - 1) + "] :: " + i1);
                T[i] += i1;
            }
        }
        return T[n];
    }

    Integer[] cache = new Integer[20];

    public int numTrees(int n) {
        if (n < 0) return 0;
        if (n == 0) return 1;
        if (n == 1) return 1;
        int result = 0;
        if (cache[n] != null) return cache[n];
        for (int k = 1; k <= n; k++) {
            result += numTrees(k - 1) * numTrees(n - k);
        }
        return cache[n] = result;
    }

    /**
     * Given a sequence 1…n, to construct a Binary Search Tree (BST) out of the sequence,
     * we could enumerate each number i in the sequence, and use the number as the root,
     * naturally, the subsequence 1…(i-1) on its left side would lay on the left branch of the root,
     * and similarly the right subsequence (i+1)…n lay on the right branch of the root.
     * We then can construct the subtree from the subsequence recursively.
     * Through the above approach, we could ensure that the BST that we construct are all unique,
     * since they have unique roots.
     *
     * G(n) = F(1, n) + F(2, n) + ... + F(n, n).
     *
     *  For example, F(3, 7): the number of unique BST tree with number 3 as its root.
     *  To construct an unique BST out of the entire sequence [1, 2, 3, 4, 5, 6, 7] with 3 as the root,
     *  which is to say, we need to construct an unique BST out of its left subsequence [1, 2]
     *  and another BST out of the right subsequence [4, 5, 6, 7], and then combine them together (i.e. cartesian product).
     *  The tricky part is that we could consider the number of unique BST out of sequence [1,2] as G(2),
     *  and the number of of unique BST out of sequence [4, 5, 6, 7] as G(4). Therefore, F(3,7) = G(2) * G(4).
     *
     *  F(i, n) = G(i-1) * G(n-i)	1 <= i <= n
     * @param n
     * @return
     */
    public int numTreesBottomUp(int n) {
        if (n < 0) return 0;
        int[] cache = new int[20];
        cache[0] = 1;
        cache[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.println(j - 1+" : "+(i - j));
                cache[i] += cache[j - 1] * cache[i - j];
            }
        }
        return cache[n];
    }

    public static void main(String[] args) {
        CatalanNumberBinarySearchTree cnt = new CatalanNumberBinarySearchTree();
        System.out.println();
        System.out.println(cnt.numTreesBottomUp(5));
    }

}
