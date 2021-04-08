package dynamicProgramming;

/**
 *
 *         https://www.youtube.com/watch?v=YDf982Lb84o&t=277s
 */
public class CatalanNumberBinarySearchTree {
	public int countTrees(int n) {
		int[] T = new int[n + 1];
		T[0] = 1;
		T[1] = 1;
		for (int i = 2; i <= n; i++) {
			for (int j = 0; j < i; j++) {
				System.out.println("T[" + j + "]" + "*" + "T[" + (i - j - 1) + "] :: " + T[j] * T[i - j - 1]);

				T[i] += T[j] * T[i - j - 1];
			}
		}
		return T[n];
	}

	public static void main(String args[]) {
		CatalanNumberBinarySearchTree cnt = new CatalanNumberBinarySearchTree();
		System.out.println();
		System.out.println(cnt.countTrees(3));
	}
}
