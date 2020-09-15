package strings.common.sorting;

import java.util.*;

public class KthLargestElement {
    class Solution {
        public int findKthLargest(int[] nums, int k) {

            return quickSelect(nums, 0, nums.length-1,nums.length-k);
        }

        public  void swap(int[] A, int i, int j) {
            int temp = A[i];
            A[i] = A[j];
            A[j] = temp;
        }

        public  int partition(int[] nums, int start, int end) {
            Random rand= new Random();
            //Get a random pivot between beg and end
            int pivotIndex=  start+rand.nextInt(Math.abs(end-start));

            //New position of pivot element
            int last=end;

            //Move the pivot element to right edge of the array
            swap(nums,pivotIndex,end);

            end--;

            while(start<=end){
                if(nums[start]<nums[last])start++; //Accumulate smaller elements to the left
                else{
                    swap(nums,start,end);
                    end--;
                }
            }
            //Move pivot element to its correct position
            swap(nums, start,last);
            return start;
        }

        public  int quickSelect(int[] A, int left, int right, int k) {
            if (left == right) {
                return A[left];
            }

            int pivotIndex = partition(A, left, right);
            if (k == pivotIndex) {
                return A[k];
            } else if (pivotIndex<k) {
                return quickSelect(A, pivotIndex + 1, right, k);

            } else {
                return quickSelect(A, left, pivotIndex - 1, k);
            }
        }
    }


}

