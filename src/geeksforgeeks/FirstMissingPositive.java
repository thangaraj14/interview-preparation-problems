package geeksforgeeks;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/first-missing-positive/
 * Given an unsorted integer array nums, find the smallest missing positive integer.
 *
 * Follow up: Could you implement an algorithm that runs in O(n) time
 * and uses constant extra space.?
 *
 * Input: nums = [1,2,0]
 * Output: 3
 *
 * Input: nums = [7,8,9,11,12]
 * Output: 1
 */
public class FirstMissingPositive {

    public int firstMissingPositive(int[] A) {
        int i = 0;
        while (i < A.length) {
            // same cyclic sort, as missing numbers
	        if (A[i] == i + 1 || A[i] <= 0 || A[i] > A.length) {
		        i++;
	        } else if (A[A[i] - 1] != A[i]) {
		        swap(A, i, A[i] - 1);
	        } else {
		        i++;
	        }
        }
        i = 0;
        while (i < A.length && A[i] == i + 1)
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

    public List<Integer> findKMissingPositiveNumberOfSizeK(int[] A, int k){
        int i = 0;
        while (i < A.length) {
            // same cyclic sort, as missing numbers
	        if (A[i] == i + 1 || A[i] <= 0 || A[i] > A.length) {
		        i++;
	        } else if (A[A[i] - 1] != A[i]) {
		        swap(A, i, A[i] - 1);
	        } else {
		        i++;
	        }
        }

        List<Integer> missingNumber= new ArrayList<>();
        Set<Integer> additionalNumber= new HashSet<>();

        i=0;
        while(i<A.length && missingNumber.size()<k){
            if(i+1!=A[i]){
                missingNumber.add(i+1);
                additionalNumber.add(A[i]);
            }
        }

        for( i=1;missingNumber.size()<k;i++){
            if(!additionalNumber.contains(i+A.length)){
                missingNumber.add(i+A.length);
            }
        }

        return missingNumber;
    }
    public static void main(String[] args) {
        int[] arr = { 3, 4, -1, 1 };
        FirstMissingPositive fmp = new FirstMissingPositive();
        System.out.println(fmp.firstMissingPositive(arr));
        System.out.println(fmp.firstMissingPositiveWithExtraSpace(arr));
    }
}