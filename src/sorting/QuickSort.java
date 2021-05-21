package sorting;

import java.util.Random;

public class QuickSort {

	public int[] sortArray(int[] nums) {

	quickSort(nums, 0, nums.length-1);

	return nums;
}

	public void quickSort(int[] nums, int start, int end){
		if(start<end){
			int pivot= findPivot(nums, start, end);
			quickSort(nums,start,pivot-1);
			quickSort(nums, pivot+1, end);
		}
	}

	public int findPivot(int[] nums, int start, int end){
		Random rand= new Random();
		//Get a random pivot between beg and end
		int pivotIndex=  start+rand.nextInt(Math.abs(end-start));

		//storing last index since New position of pivot element is at last
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

	public void swap(int[] nums, int i, int j){
		int temp=nums[i];
		nums[i]=nums[j];
		nums[j]=temp;
	}
}





