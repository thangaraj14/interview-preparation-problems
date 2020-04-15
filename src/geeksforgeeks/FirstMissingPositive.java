package geeksforgeeks;

/**
 * https://leetcode.com/problems/first-missing-positive/
 */
public class FirstMissingPositive {

    public int firstMissingPositive(int[] arr) {
        int i = 0;
        while (i < arr.length) {
            if (arr[i] == i + 1 || arr[i] <= 0 || arr[i] > arr.length) {
                i++;
            } else if (arr[arr[i] - 1] != arr[i]) {
                swap(arr, i, arr[i] - 1);
            } else {
                i++;
            }
        }
        i = 0;
        while (i < arr.length && arr[i] == i + 1)
            i++;
        return i + 1;
    }

    private void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public int firstMissingPositiveWithExtraSpace(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        int length = nums.length;
        int[] arr = new int[length + 1];
        for (int i = 0; i < length; i++) {
            if (nums[i] <= length && nums[i] > 0) {
                arr[--nums[i]] = -1;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != -1) {
                return ++i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = { 3, 4, -1, 1 };
        FirstMissingPositive fmp = new FirstMissingPositive();
        System.out.println(fmp.firstMissingPositive(arr));
        System.out.println(fmp.firstMissingPositiveWithExtraSpace(arr));
    }
}