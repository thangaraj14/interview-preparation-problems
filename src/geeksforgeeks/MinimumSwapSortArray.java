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
			resMap.put(A[i], i);
		}
		System.out.println("After first loop end");
		System.out.println();
        for (Map.Entry<Integer, Integer> entry : resMap.entrySet()) {
			System.out.println(entry.getKey() + " --> " + entry.getValue() + ",  ");
		}
		System.out.println("second loop start");
		i = 0;
		for (Map.Entry<Integer, Integer> entry : resMap.entrySet()) {
			//System.out.print(entry.getKey() + " --> " + i + ",  ");
			entry.setValue(i++);
		}

		System.out.println("second loop end");

		for (Map.Entry<Integer, Integer> entry : resMap.entrySet()) {
			System.out.println(entry.getKey() + " --> " + entry.getValue() + ",  ");
		}
		System.out.println();
		int swap = 0;
		for (i = 0; i < N;) {
			//System.out.println(A[i] + "--" + resMap.get(A[i]) + "--" + i);
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