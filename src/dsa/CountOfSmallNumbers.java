package dsa;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 * https://leetcode.com/problems/count-of-smaller-numbers-after-self/discuss/76583/11ms-JAVA-solution-using-merge-sort-with-explanation
 */
class CountOfSmallNumbers {
    int[] count;

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> result = new ArrayList<>();

        count = new int[nums.length];
        int[] indexes = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            indexes[i] = i;
        }
        mergesort(nums, indexes, 0, nums.length - 1);
        for (int i = 0; i < count.length; i++) {
            result.add(count[i]);
        }
        return result;
    }

    private void mergesort(int[] nums, int[] indexes, int start, int end) {
        if (end <= start) {
            return;
        }
        int mid = (start + end) / 2;
        mergesort(nums, indexes, start, mid);
        mergesort(nums, indexes, mid + 1, end);

        merge(nums, indexes, start, mid + 1, end);
    }

    private void merge(int[] nums, int[] indexes, int start, int mid, int end) {
        int i = start;
        int j = mid;
        int rightCount = 0;
        int[] temp = new int[end - start + 1];

        int k = 0;
        while (i <= mid - 1 && j <= end) {
            if (nums[indexes[i]] < nums[indexes[j]]) {
                temp[k] = indexes[i];
                count[indexes[i]] += rightCount;
                i++;
            } else {
                temp[k] = indexes[j];
                rightCount++;
                j++;
            }
            k++;
        }
        while (i <= mid - 1) {
            temp[k++] = indexes[i];
            count[indexes[i]] += rightCount;
            i++;
        }
        while (j <= end) {
            temp[k++] = indexes[j++];
        }
        for (int ii = start; ii <= end; ii++) {
            indexes[ii] = temp[ii - start];
        }
    }

    public static void main(String[] args) {
        CountOfSmallNumbers csn = new CountOfSmallNumbers();
        int[] arr = { 5, 2, 6, 1 };
        System.out.println(csn.countSmaller(arr));
    }
}