package geeksforgeeks;

/*https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/*/
public class FindNumberOfOccurrences {

    public static void main(String[] args) {
        int arr[] = { 1, 1, 2, 2, 2, 2, 3, 3 };
        System.out.println(
                findLastOccurrence(arr, 0, arr.length - 1, 2) - findFirstOccurrence(arr, 0, arr.length - 1, 2) + 1);

    }

    private static int findFirstOccurrence(int[] arr, int left, int right, int key) {
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;
        if (arr[mid] == key && (mid == 0 || arr[mid - 1] != key)) {
            return mid;
        } else if (arr[mid] >= key) {
            return findFirstOccurrence(arr, left, mid - 1, key);
        } else {
            return findFirstOccurrence(arr, mid + 1, right, key);
        }
    }

    private static int findLastOccurrence(int[] arr, int left, int right, int key) {
        if (left > right) {
            return 0;
        }

        int mid = (left + right) / 2;
        if (arr[mid] == key && (mid == right || arr[mid + 1] != key)) {
            return mid;
        } else if (arr[mid] <= key) {
            return findLastOccurrence(arr, mid + 1, right, key);
        } else {
            return findLastOccurrence(arr, left, mid - 1, key);
        }
    }

}
