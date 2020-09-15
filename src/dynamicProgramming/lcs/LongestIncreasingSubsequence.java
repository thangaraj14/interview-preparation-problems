package dynamicProgramming.lcs;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Solve the LIS subproblem for each snippet of the array ending between 1, 2,
 * 3, ... and so on until nums.length - 1 (inclusive)
 * 
 * Ex:
 * 
 * [-2, 1, 2, 3]
 * 
 * [-2] from index 0 to index 0 [-2, 1] from index 0 to index 1 [-2, 1, 2] from
 * index 0 to index 2 [-2, 1, 2, 3] from index 0 to index 3
 * 
 * Our answer is the maximum LNDS found between all subproblems we solve along
 * the way.
 *
 * Time complexity is O(n^2).
 */
public class LongestIncreasingSubsequence {

	public static void main(String[] args) {
		int[] nums = { 10, 22, 9, 33, 21, 50, 41, 60, 80 };


		lengthOfLIS(nums);
	}

	private static void lisLength(int[] nums) {
		int[] result = new int[nums.length];
		Arrays.fill(result, 1);

		int maximumSoFar = 1;

		for (int i = 1; i < nums.length; i++) {
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) {
					result[i] = Math.max(result[i], result[j] + 1);
				}
			}
			maximumSoFar = Math.max(maximumSoFar, result[i]);
		}
		System.out.println(maximumSoFar);
	}

	public static int findPositionToReplace(int[] a, int low, int high, int x) {
		int mid;
		while (low <= high) {
			mid = low + (high - low) / 2;
			if (a[mid] == x)
				return mid;
			else if (a[mid] > x)
				high = mid - 1;
			else
				low = mid + 1;
		}
		return low;
	}

	public static int lengthOfLIS(int[] nums) {
		if (nums == null | nums.length == 0)
			return 0;
		//Set<Integer> hash= new HashSet<Integer>(Arrays.asList(nums));
		int n = nums.length, len = 0;
		int[] increasingSequence = new int[n];
		increasingSequence[len++] = nums[0];
		for (int i = 1; i < n; i++) {
			if (nums[i] > increasingSequence[len - 1])
				increasingSequence[len++] = nums[i];
			else {
				int position = findPositionToReplace(increasingSequence, 0, len - 1, nums[i]);
				increasingSequence[position] = nums[i];
			}
		}
		return len;
	}
	public int eraseOverlapIntervals(int[][] intervals) {
		if(intervals.length==0) return 0;

//		PriorityQueue<int[]> queue= new PriorityQueue()<int[]>((a, b)->{
//			if(a[0]==b[0]) return Integer.compare(a[1],b[1]);
//			return Integer.compare(a[0],b[0]);
//		});

		PriorityQueue<int[]> queue = new PriorityQueue<int[]>((a, b) -> {
			if(a[0]==b[0]) return Integer.compare(a[1],b[1]);
			return Integer.compare(a[0],b[0]);
		});

		for(int[] interval: intervals){
			queue.offer(interval);
		}
		int result=0;
		int[] temp= queue.poll();
		int start= temp[0];
		int end= temp[1];

		while(!queue.isEmpty()){
			if(end>queue.peek()[0]) {
				result++;
				queue.poll();
			}else{
				end=Math.max(end,queue.poll()[1]);
			}
		}

		return result;
	}

}
