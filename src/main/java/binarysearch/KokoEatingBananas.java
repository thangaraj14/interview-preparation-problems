package binarysearch;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/koko-eating-bananas/
 */
public class KokoEatingBananas {

    public int minEatingSpeed(int[] piles, int h) {

        int left = 1;
        // if you feel this would take O(N) then take the max value
        // given as part of problem constraint

        int right = Arrays.stream(piles).max().orElse(0);

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (isFeasible(piles, mid, h)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;

    }

    public boolean isFeasible(int[] piles, int mid, int h) {
        int time = 0;

        for (int p : piles) {
            time += (int) Math.ceil((double) (p) / (double) (mid));
        }

        return time <= h;
    }
}
