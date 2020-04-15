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

    // -3,1,-2,-3,-4,-5,-6
    static int findBitonicPoint(int arr[], int n, int l, int r) {
        int mid = ((r + l) / 2) + l;
        if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
            return mid;
        } else {
            // towards right if next number is greater
            if (arr[mid] > arr[mid - 1] && arr[mid] < arr[mid + 1]) {
                mid = findBitonicPoint(arr, n, mid, r);
            } else {
                // towards left if next number is smaller
                if (arr[mid] < arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                    mid = findBitonicPoint(arr, n, l, mid);
                }
            }
        }
        return mid;
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
        int l = 0;
        int r = n - 1;
        int index = findBitonicPoint(arr, n, l, r);

        int x = searchBitonic(arr, n, key, index);

        if (x == -1) {
            System.out.println("Element Not Found");
        } else {
            System.out.println("Element Found at index " + x);
        }

    }
}