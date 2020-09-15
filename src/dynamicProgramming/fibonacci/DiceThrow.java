package dynamicProgramming.fibonacci;

/**
 * Time Complexity: O(m * n * x)
 *
 */
class DiceThrow {
	// m-> faces
	// n->dices
	// x->sum
	public static long findWays(int m, int n, int x) {

		long[][] T = new long[n + 1][x + 1];

		for (int j = 1; j <= m && j <= x; j++)
			T[1][j] = 1;

		for (int i = 2; i <= n; i++) {
			System.out.println("i=" + i);
			for (int j = 1; j <= x; j++) {
				System.out.println("j =" + j);
				for (int k = 1; k < j && k <= m; k++) {
					System.out.println("T[" + (i - 1) + "][" + (j - k) + "]");
					T[i][j] += T[i - 1][j - k];
				}
			}
		}

		for (int i = 0; i < n + 1; i++) {
			for (int j = 0; j < x + 1; j++)
				System.out.print(T[i][j] + " ");
			System.out.println();
		}
		return T[n][x];
	}

	public static void main(String[] args) {
		System.out.println(findWays(6, 3, 6));
	}
}