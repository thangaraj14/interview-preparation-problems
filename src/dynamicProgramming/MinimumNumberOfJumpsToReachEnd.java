package dynamicProgramming;

/**
 * Date 06/12/2014
 * 
 * @author tusroy
 * 
 *         Space complexity O(n) to maintain result and min jumps Time
 *         complexity O(n^2)
 * 
 *         http://www.geeksforgeeks.org/minimum-number-of-jumps-to-reach-end-of-a-given-array/
 *         https://www.youtube.com/watch?v=cETfFsSTGJI
 */
public class MinimumNumberOfJumpsToReachEnd {

	public int minJump(int arr[], int result[]) {

		int[] jump = new int[arr.length];
		jump[0] = 0;
		for (int i = 1; i < arr.length; i++) {
			jump[i] = Integer.MAX_VALUE - 1;
		}

		for (int i = 1; i < arr.length; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[j] + j >= i) {
					if (jump[i] > jump[j] + 1) {
						result[i] = j;
						jump[i] = jump[j] + 1;
					}
				}
			}
		}

		return jump[jump.length - 1];
	}

	public static void main(String args[]) {
		MinimumNumberOfJumpsToReachEnd mj = new MinimumNumberOfJumpsToReachEnd();
		int arr[] = { 2, 3, 1, 1, 2, 4, 2, 0, 1, 1 };
		int r[] = new int[arr.length];
		int result = mj.minJump(arr, r);
		System.out.println(result);
	}


	public int jump(int[] A) {
		int jumps = 0, curEnd = 0, curFarthest = 0;
		for (int i = 0; i < A.length - 1; i++) {
			curFarthest = Math.max(curFarthest, i + A[i]);
			// for this input { 1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9 }
			// when i is at 1[3] farthest one can jump is till 4(farthest) and end is also updated, means till i reaches 4 if the farthest reaches the array end we break out of loop;
			if (i == curEnd) {
				jumps++;
				curEnd = curFarthest;

				if (curEnd >= A.length - 1) {
					break;
				}
			}
		}
		return jumps;
	}
}