package practiceproblems.mergesort;

import java.util.Arrays;

class MergeSort {

	static void mergeSort(int[] arr, int left, int right) {
		if (left < right) {
			int mid = ((right - left) / 2) + left;

			mergeSort(arr, left, mid);
			mergeSort(arr, mid + 1, right);

			merge(arr, left, mid, right);
		}
	}

	static void merge(int[] arr, int left, int mid, int right) {
		int[] temp = new int[arr.length];
		int i = left;
		int j = mid + 1;
		int k = left;
		while ((i <= mid) && (j <= right)) {
			if (arr[i] < arr[j]) {
				temp[k++] = arr[i++];
			} else {
				temp[k++] = arr[j++];
			}
		}

		while (i <= mid)
			temp[k++] = arr[i++];
		while (j <= right)
			temp[k++] = arr[j++];

		for (i = left; i <= right; i++)
			arr[i] = temp[i];

	}

	public static void main(String[] args) {
		int []arr = new int[] { 8, 4, 1,6,6,9,2, 2 };

		mergeSort(arr, 0,arr.length - 1);
		System.out.println(Arrays.toString(arr));
	}
}
