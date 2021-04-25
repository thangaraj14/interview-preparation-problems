package binarysearch;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/magnetic-force-between-two-balls/
 *
 */
public class MagneticForceBetweenTwoBalls {

 /**
  - First, might as well consider the placements in sorted order.
 - We only really need to check the forces of balls immediately to their left and right.
 - Say our target is D. We want all balls to be at least D units apart.
 - We can check if D is doable, by trying a greedy approach. Put the very
 first ball at the first available position, place the next ball at the
 first position rightwards that is sufficiently distant, etc.

 - We can do a binary search for what minimum distance is achievable.
 - With this we have an O(n*log(L/m)) solution. Where L is the largest distance between
 baskets, and m is the number of balls we're trying to place, n is number of possible positions.
 */
    public int maxDistance(int[] position, int m) {

        Arrays.sort(position);

        int left = 1;
        int right = position[position.length - 1];

        while (left < right) {
            int mid = left + (right - left) / 2;
            System.out.println("before isFeasible:: left "+left+" right "+right+" mid "+mid);
            if (isFeasible(position, m, mid)) {
                System.out.println("condition is Feasible so changing right to mid");
                right = mid;
            } else {
                System.out.println("condition is not Feasible so changing left to mid+1");
                left = mid + 1;
            }
            System.out.println();
        }

        return left - 1;
    }

    public boolean isFeasible(int[] position, int noOfBalls, int minDistance) {
        int lastPos = position[0]; // first ball at 0 position - keep track of last position
        int idx = 1;
        noOfBalls--; // we have placed first ball at 0 position
        System.out.println("begin isFeasible:: remaining noOfBalls after placing first "+noOfBalls+" minDistance "+minDistance);
        while (idx < position.length && noOfBalls > 0) {
            /* if minDistance between last position and position at idx is greater then or equal to minDistance
             *  then place the ball else skip
             * */
            if (position[idx] - lastPos >= minDistance) {
                lastPos = position[idx];
                noOfBalls--;
            }
            idx++;
        }
        System.out.println("closing isFeasible:: noOfBalls "+noOfBalls);
        return noOfBalls != 0; /*if all balls have been placed then return true else false*/
    }

    public static void main(String[] args) {
        new MagneticForceBetweenTwoBalls().maxDistance(new int[]{1,2,3,4,7}, 3);
    }
}
