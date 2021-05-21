package sorting;

import java.util.Arrays;

public class PractiseMergeSort {

	public static void main(String[] args) {
		int[] arr = { 9, 3, -1, 5, 2, 7 };
		int n = arr.length;
		int[] temp = new int[n];
		mergeSort(arr, temp, 0, n - 1);
		System.out.println(Arrays.toString(arr));
	}

	private static void mergeSort(int arr[], int temp[], int left, int right) {
		while (left < right) {
			int mid = ((right - left) / 2) + left;
			mergeSort(arr, temp, left, mid);
			mergeSort(arr, temp, mid + 1, right);
			mergeUtil(arr, temp, left, mid, right);
		}
	}

	private static void mergeUtil(int[] arr, int[] temp, int left, int mid, int right) {
		int i = left;
		int j = mid + 1;
		int k = left;

		while (i <= mid && j <= right) {
			if (arr[i] < arr[j]) {
				temp[k++] = arr[i++];
			} else {
				temp[k++] = arr[j++];
			}
		}

		while (i <= mid) {
			temp[k++] = arr[i++];
		}
		while (j <= right) {
			temp[k++] = arr[j++];
		}

		for (int index = left; index <= right; index++) {
			arr[index] = temp[index];
		}
	}
}
