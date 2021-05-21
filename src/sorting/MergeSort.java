package sorting;

import java.util.Arrays;

class MergeSort {

	static void mergeSort(int arr[], int temp[], int left, int right) {
		if (left < right) {
			int mid = ((right - left) / 2) + left;

			mergeSort(arr, temp, left, mid);
			mergeSort(arr, temp, mid + 1, right);

			merge(arr, temp, left, mid, right);
		}
	}

	static void merge(int arr[], int temp[], int left, int mid, int right) {

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
		int arr[] = new int[] { 8, 4, 1, 2 };
		int temp[] = new int[arr.length];
		mergeSort(arr, temp, 0, arr.length - 1);
		System.out.println(Arrays.toString(arr));
	}
}
