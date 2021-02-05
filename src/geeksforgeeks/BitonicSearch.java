package geeksforgeeks;

/**
 * https://www.geeksforgeeks.org/find-element-bitonic-array/
 */
/* A Bitonic Sequence is a sequence of numbers which is first strictly increasing then after a point strictly decreasing.*/
public class BitonicSearch {

    static int ascendingBinarySearch(int arr[], int low, int high, int key) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == key) {
                return mid;
            }
            if (arr[mid] > key) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    static int descendingBinarySearch(int arr[], int low, int high, int key) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == key) {
                return mid;
            }
            if (arr[mid] < key) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    public static int peakIndexInMountainArray(int[] A) {

        int left = 0;
        int right = A.length - 1;
        int mid;

        while (left < right) {
            mid = (left + right) / 2;
            if (A[mid] < A[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    static int searchBitonic(int arr[], int n, int key, int index) {
        if (key > arr[index]) {
            return -1;
        } else if (key == arr[index]) {
            return index;
        } else {
            int temp = ascendingBinarySearch(arr, 0, index - 1, key);
            if (temp != -1) {
                return temp;
            }
            return descendingBinarySearch(arr, index + 1, n - 1, key);
        }
    }

    public static void main(String args[]) {
        int arr[] = { -3, 3, 9, 8, 20, 17, 5, 3, 1 };
        int key = 3;
        int n = arr.length;
        int index = peakIndexInMountainArray(arr);

        int x = searchBitonic(arr, n, key, index);

        if (x == -1) {
            System.out.println("Element Not Found");
        } else {
            System.out.println("Element Found at index " + x);
        }

    }
}