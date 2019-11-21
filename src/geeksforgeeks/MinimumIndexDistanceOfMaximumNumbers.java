package geeksforgeeks;

/**
 * https://www.geeksforgeeks.org/minimum-distance-between-two-occurrences-of-maximum/
 *
 */
class MinimumIndexDistanceOfMaximumNumbers {

	static int minDistance(int arr[], int n) {
		int maximumElement = arr[0];
		int minDist = n;
		int index = 0;

		for (int i = 1; i < n; i++) {
			if (maximumElement == arr[i]) {
				minDist = Math.min(minDist, (i - index));
				index = i;
			} else if (maximumElement < arr[i]) {
				maximumElement = arr[i];
				minDist = n;
				index = i;
			}
		}
		return minDist - 1;
	}

	public static void main(String[] args) {
		int arr[] = { 6, 3, 1, 3, 6, 5, 4, 1 };
		int n = arr.length;
		System.out.print("Minimum distance = " + minDistance(arr, n));
	}
}