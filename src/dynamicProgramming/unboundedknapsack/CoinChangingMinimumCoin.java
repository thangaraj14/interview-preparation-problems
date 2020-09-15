package dynamicProgramming.unboundedknapsack;

import java.util.Arrays;

/**
 * 
 *
 * Time complexity - O(coins.size * total) Space complexity - O(coins.size *
 * total)
 *
 * https://www.youtube.com/watch?v=NJuKJ8sasGk&t=324s
 */
public class CoinChangingMinimumCoin {

	public int minimumCoinBottomUp(int total, int coins[]) {
		// where we calculate results
		int T[] = new int[total + 1];
		// to extract combination
		int R[] = new int[total + 1];
		T[0] = 0;
		for (int i = 1; i <= total; i++) {
			T[i] = Integer.MAX_VALUE - 1;
			R[i] = -1;
		}
		for (int j = 0; j < coins.length; j++) {
			for (int i = 1; i <= total; i++) {
				if (i >= coins[j]) {
					if (T[i - coins[j]] + 1 < T[i]) {
						T[i] = 1 + T[i - coins[j]];
						R[i] = j;
					}
				}
			}
		}
		System.out.println(Arrays.toString(T));
		System.out.println(Arrays.toString(R));
		printCoinCombination(R, coins);
		return T[total];
	}

	private void printCoinCombination(int R[], int coins[]) {
		if (R[R.length - 1] == -1) {
			System.out.print("No solution is possible");
			return;
		}
		int start = R.length - 1;
		System.out.print("Coins used to form total ");
		while (start != 0) {
			int j = R[start];
			System.out.print(coins[j] + " ");
			start = start - coins[j];
		}
		System.out.print("\n");
	}

	public static void main(String args[]) {
		int total = 11;
		int coins[] = { 1, 5, 6, 8 };
		CoinChangingMinimumCoin cc = new CoinChangingMinimumCoin();
		int bottomUpValue = cc.minimumCoinBottomUp(total, coins);

		System.out.print(bottomUpValue);

	}
}