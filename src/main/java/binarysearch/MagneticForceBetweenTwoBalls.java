package binarysearch;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/magnetic-force-between-two-balls/
 *
 * Revise
 */
public class MagneticForceBetweenTwoBalls {

    /**
     * - First, might as well consider the placements in sorted order.
     * - We only really need to check the forces of balls immediately to their left and right.
     * - Say our target is D. We want all balls to be at least D units apart.
     * - We can check if D is doable, by trying a greedy approach. Put the very
     * first ball at the first available position, place the next ball at the
     * first position rightwards that is sufficiently distant, etc.
     * <p>
     * - We can do a binary search for what minimum distance is achievable.
     * - With this we have an O(n*log(L/m)) solution. Where L is the largest distance between
     * baskets, and m is the number of balls we're trying to place, n is number of possible positions.
     */
    public int maxDistance(int[] position, int m) {
        int n = position.length;
        Arrays.sort(position);
        int left = 0, right = position[n - 1] - position[0];
        while (left < right) {
            int mid = right - (right - left) / 2;
            if (check( position,mid, m))
                left = mid+1;
            else
                right = mid;
        }
        return left;
    }

    /**
     * Define function count(d) that counts the number of balls can be placed in to baskets,
     * under the condition that the minimum distance between any two balls is d.
     */
    private boolean check(int[] position, int minimumDistance, int m) {
        // Always place first object at position[0]
        int lastBallPosition = position[0];
        int ballsLeftToBePlaced = m - 1; // we have placed first ball at 0 position
        for (int i = 1; i < position.length && ballsLeftToBePlaced != 0; ) {
            /** if minDistance between last position and position at idx is greater than or equal to minDistance
             *  then place the ball else skip
             * */
            if (position[i] - lastBallPosition < minimumDistance) {
                // Try to place the next ball, since this ball isn't minimumDistance away from lastBall
                i++;
            } else {
                // Place the ball only if this ball is farther than the previous ball by minimumDistance
                lastBallPosition = position[i];
                ballsLeftToBePlaced--;
            }
        }
        return ballsLeftToBePlaced == 0; /*if all balls have been placed then return true else false*/
    }

    public static void main(String[] args) {
        new MagneticForceBetweenTwoBalls().maxDistance(new int[]{1, 2, 3, 4, 7}, 3);
    }
}
