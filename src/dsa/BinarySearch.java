package dsa;

public class BinarySearch {

    static int binarySearch(int[] arr, int element) {

        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (arr[mid] == element) {
                return mid;
            }

            if (arr[mid] < element) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    static int binarySearchRecur(int[] arr, int left, int right, int element) {

        if (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == element) {
                return mid;
            }

            if (arr[mid] > element) {
                return binarySearchRecur(arr, left, mid - 1, element);
            }

            return binarySearchRecur(arr, mid + 1, right, element);
        }
        return -1;
    }

    // find the last occurrence of the element 1 : [1,1,0,0,0]
    public int binarySearchUtil(int[] row) {
        int left = 0;
        int right = row.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (row[mid] == 1) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 2, 3, 4, 5, 6, 7 };
        System.out.println(binarySearch(arr, 6));
        System.out.println(binarySearchRecur(arr, 0, arr.length - 1, 6));
    }
}
