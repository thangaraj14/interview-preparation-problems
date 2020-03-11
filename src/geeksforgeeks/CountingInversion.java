package geeksforgeeks;

/**
 * https://www.geeksforgeeks.org/counting-inversions/
 */
class CountingInversion {

    static int mergeSort(int[] arr, int arrSize) {
        int[] temp = new int[arrSize];
        return mergeSort(arr, temp, 0, arrSize - 1);
    }

    static int mergeSort(int[] arr, int[] temp, int left, int right) {
        int mid;
        int invCount = 0;
        if (left < right) {
            mid = ((right - left) / 2) + left;

            invCount = mergeSort(arr, temp, left, mid);
            invCount += mergeSort(arr, temp, mid + 1, right);

            invCount += merge(arr, temp, left, mid + 1, right);
        }
        return invCount;
    }

    static int merge(int[] arr, int[] temp, int left, int mid, int right) {
        int invCount = 0;

        int i = left;
        int j = mid;
        int k = left;
        while ((i <= mid - 1) && (j <= right)) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];

                invCount = invCount + (mid - i);
            }
        }

        while (i <= mid - 1)
            temp[k++] = arr[i++];
        while (j <= right)
            temp[k++] = arr[j++];

        for (i = left; i <= right; i++)
            arr[i] = temp[i];

        return invCount;
    }

    public static void main(String[] args) {
        int arr[] = new int[] { 4, 6, 2, 1, 9, 7 };
        System.out.println("Number of inversions are " + mergeSort(arr, arr.length));
    }
}
