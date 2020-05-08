package geeksforgeeks;

import java.util.Arrays;
import java.util.Random;

/**
 * https://leetcode.com/problems/shuffle-an-array/
 */
public class ShuffleArray {

    private int[] nums;
    private Random random;

    public ShuffleArray(int[] nums) {
        this.nums = nums;
        random = new Random();
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {
        return nums;
    }

    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() {
        if (nums == null) {
            return null;
        }
        int[] a = nums.clone();
        for (int j = 1; j < a.length; j++) {
            int i = random.nextInt(j + 1);
            swap(a, i, j);
        }
        return a;
    }

    private void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3 };
        ShuffleArray sa = new ShuffleArray(arr);
        System.out.println("After shuffling :" + Arrays.toString(sa.shuffle()));
        System.out.println("Reset : " + Arrays.toString(sa.reset()));
    }
}
