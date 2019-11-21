package geeksforgeeks;

import java.util.Map;
import java.util.TreeMap;

/**
 * https://www.geeksforgeeks.org/minimum-number-swaps-required-sort-array/
 *
 */
class MinimumSwapSortArray {

	public int minSwaps(int[] A, int N) {
		int i = 0;
		// Tree map sorts the order
		Map<Integer, Integer> resMap = new TreeMap<>();
		for (; i < N; i++) {
			System.out.print(A[i] + " --> " + i + " ");
			resMap.put(A[i], i);

		}
		System.out.println();
		i = 0;
		for (Map.Entry<Integer, Integer> entry : resMap.entrySet()) {
			System.out.print(entry.getKey() + " --> " + i + " ");
			entry.setValue(i++);
		}
		int swap = 0;
		for (i = 0; i < N;) {
			System.out.println(A[i] + "--" + resMap.get(A[i]) + "--" + i);
			// check if array position is same as treeMap's index
			if (resMap.get(A[i]) != i) {
				int temp = A[i];
				A[i] = A[resMap.get(temp)];
				A[resMap.get(temp)] = temp;
				swap++;
			} else {
				i++;
			}
		}
		return swap;
	}

	public static void main(String[] args) {
		int[] a = { 1, 5, 4, 3, 2 };
		MinimumSwapSortArray g = new MinimumSwapSortArray();
		System.out.println(g.minSwaps(a, a.length));
	}
}