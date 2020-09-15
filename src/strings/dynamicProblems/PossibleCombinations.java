package strings.dynamicProblems;

public class PossibleCombinations {

	public static void main(String[] args) {
		char[] arr = { 'a', 'b', 'c' };
		combinations(arr, arr.length);
	}

	private static void combinations(char[] arr, int length) {
		char[] duplicate = new char[length];
		for (int r = 1; r <= length; r++) {
			printCombinations(arr, duplicate, 0, length, 0, r);
		}

	}

	private static void printCombinations(char[] arr, char[] data, int start, int end, int index, int r) {
		if (index == r) {
			for (int i = 0; i < r; i++)
				System.out.print(data[i] + " ");
			System.out.println();
		}
		for (int i = start; i < end; i++) {
			data[index] = arr[i];
			printCombinations(arr, data, i + 1, end, index + 1, r);
		}
	}
}
