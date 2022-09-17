package dynamicProgramming;

import java.util.Arrays;

/**
 * Date 05/09/2015
 * 
 * @author tusroy
 * 
 *         1) Create all rotations of boxes such that length is always greater
 *         or equal to width 2) Sort boxes by base area in non increasing order
 *         (length * width). This is because box with more area will never ever
 *         go on top of box with less area. 3) Take T[] and result[] array of
 *         same size as total boxes after all rotations are done 4) Apply
 *         longest increasing subsequence type of algorithm to get max height.
 * 
 *
 *         References
 *         http://www.geeksforgeeks.org/dynamic-programming-set-21-box-stacking-problem/
 *         http://people.cs.clemson.edu/~bcdean/dp_practice/
 */
public class BoxStacking {
	//We can use multiple instances of boxes. What it means is, we can have two different rotations of a box as part of our maximum height stack.

	/**
	 * You can rearrange any cuboid's dimensions by rotating it to put it on another cuboid.
	 * So for each cuboid, we sort its length in three dimension.
	 * <p>
	 * You can place cuboid i on cuboid j,
	 * we have
	 * width[i] <= width[j] and length[i] <= length[j] and height[i] <= height[j].
	 * <p>
	 * This condition will hold, after we sort each cuboid length,
	 * that is,
	 * small[i] <= small[j] and mid[i] <= mid[j] and big[i] <= big[j].
	 */
	public int maxHeight(int[][] cuboids) {
		for (int[] cube : cuboids) Arrays.sort(cube);
		Arrays.sort(cuboids, (a, b) -> (a[0] + a[1] + a[2]) - (b[0] + b[1] + b[2]));
		int N = cuboids.length, res = 0;
		int[] dp = new int[N];
		for (int i = 0; i < N; i++) {
			dp[i] = cuboids[i][2];
			res = Math.max(res, dp[i]);
		}
		for (int i = 1; i < N; i++)
			for (int j = 0; j < i; j++)
				if (cuboids[j][0] <= cuboids[i][0] &&
						cuboids[j][1] <= cuboids[i][1] &&
						cuboids[j][2] <= cuboids[i][2]) {
					dp[i] = Math.max(dp[i], dp[j] + cuboids[i][2]);
					res = Math.max(res, dp[i]);
				}
		return res;
	}

	public int maxHeight(Dimension[] input) {
		Dimension[] allRotationInput = new Dimension[input.length * 3];
		createAllRotation(input, allRotationInput);

		Arrays.sort(allRotationInput);

		System.out.println(Arrays.toString(allRotationInput));

		int[] T = new int[allRotationInput.length];
		int[] result = new int[allRotationInput.length];

		for (int i = 0; i < T.length; i++) {
			T[i] = allRotationInput[i].height;
			result[i] = i;
		}

		for (int i = 1; i < T.length; i++) {
			for (int j = 0; j < i; j++) {
				if (allRotationInput[i].length < allRotationInput[j].length
						&& allRotationInput[i].width < allRotationInput[j].width) {
					if (T[j] + allRotationInput[i].height > T[i]) {
						T[i] = T[j] + allRotationInput[i].height;
						result[i] = j;
					}
				}
			}
		}

		int max = Integer.MIN_VALUE;
		for (int j : T) {
			if (j > max) {
				max = j;
			}
		}

		return max;
	}

	private void createAllRotation(Dimension[] input, Dimension[] allRotationInput) {
		int index = 0;
		for (int i = 0; i < input.length; i++) {
			allRotationInput[index++] = Dimension.createDimension(input[i].height, input[i].length, input[i].width);
			allRotationInput[index++] = Dimension.createDimension(input[i].length, input[i].height, input[i].width);
			allRotationInput[index++] = Dimension.createDimension(input[i].width, input[i].length, input[i].height);

		}
	}

	public static void main(String args[]) {
		BoxStacking bs = new BoxStacking();
		Dimension[] input = {new Dimension(3, 2, 5), new Dimension(1, 2, 4)};
		int maxHeight = bs.maxHeight(input);
		System.out.println("Max height is " + maxHeight);
		assert 11 == maxHeight;
	}


	static class Dimension implements Comparable<Dimension> {
		int height;
		int length;
		int width;

		Dimension(int height, int length, int width) {
			this.height = height;
			this.length = length;
			this.width = width;
		}

		Dimension() {
		}

		static Dimension createDimension(int height, int side1, int side2) {
			Dimension d = new Dimension();
			d.height = height;
			if (side1 >= side2) {
				d.length = side1;
				d.width = side2;
			} else {
				d.length = side2;
				d.width = side1;
			}
			return d;
		}

		@Override
		public int compareTo(Dimension d) {
			if (this.length * this.width >= d.length * d.width) {
				return 1;
			} else {
				return -1;
			}
		}

		@Override
		public String toString() {
			return "Dimension [height=" + height + ", length=" + length + ", width=" + width + "]";
		}
	}
}