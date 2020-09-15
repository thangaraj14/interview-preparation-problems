package dynamicProblems;

public class DynamicIntegerGenerator {

	public static void main(String[] args) {

		int[] arr = { 3, 4, 5, 6, 7, 8 };

		for (int i = 1; i <= arr.length; i++) {
			printDynamicIntegers(i, arr);
			System.out.println();
		}

	}

	private static void printDynamicIntegers(int i, int[] arr) {
		if (arr.length == i) {
			for (int u = 0; u < arr.length; u++) {
				System.out.print(arr[u]);
			}
		}
		int[] intArr = new int[i];
		for (int j = 0; j < arr.length; j++) {
			intArr[j] = arr[j];
			printDynamicIntegers(i, intArr);
		}
	}

}
