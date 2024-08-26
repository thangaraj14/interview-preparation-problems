package binarysearch;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/magnetic-force-between-two-balls/
 * <p>
 * same as aggressive cows
 */
public class MagneticForceBetweenTwoBalls {


    public static void main(String[] args) {
        new MagneticForceBetweenTwoBalls().maxDistance(new int[]{1, 2, 3, 4, 7}, 3);
    }

    /*minimum magnetic force between any two balls is maximum means
    ex: 1 2 3 4 7
    . . .      min  magnetic force is 1  min is 1
    .   . .    min  magnetic force is 2  min is 1
    .     . .  min  magnetic force is 3 *
    we should maximise the minimum magnetic force
    minimum magnetic must come 1 from all cases and if we have maximise it then it will come as 3 3 6
    */
    public int maxDistance(int[] position, int m) {
        int n = position.length;
        Arrays.sort(position);
        int left = 1, right = position[n - 1] - position[0];
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (check(position, mid, m))
                left = mid + 1;
            else
                right = mid - 1;
        }
        //When the binary search ends, right will be the largest distance that satisfies the condition,
        // and left will be one more than this value.
        return right;
    }

    private boolean check(int[] arr, int mid, int m) {
        int count = 1, last = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - last >= mid) {
                count++;
                last = arr[i];
                if (count >= m) return true;
            }
        }
        return false;
    }
}
