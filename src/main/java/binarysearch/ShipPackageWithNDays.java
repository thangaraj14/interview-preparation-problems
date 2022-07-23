package binarysearch;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/capacity-to-ship-packages-within-d-days
 */
public class ShipPackageWithNDays {
    public static int shipWithinDays(int[] weights, int D) {
        if (weights.length == 0 || D == 0) return 0;

        int lowerBound = Arrays.stream(weights).max().orElse(0);
        int upperBound = Arrays.stream(weights).sum();
        int mid = 0;
        while (lowerBound < upperBound) {
            mid = (lowerBound + upperBound) / 2;
            int daysToCarry = binarySearchUtil(weights, mid);
            // if mid can carry weight then increase the upperBound to mid
            if (daysToCarry<=D) upperBound = mid;
            else  lowerBound = mid + 1;

        }

        return lowerBound;
    }


    public static int binarySearchUtil(int[] weights, int mid) {
        int sum = 0; // weight of current ship
        int currentDay = 1; // number of days estimated
        for (int weight : weights) {
            if (sum + weight > mid) {
                currentDay += 1;
                sum = 0;

            }
            sum += weight;

            // currentWeight += weight
            //if (currentWeight > estimatedCapacity) {
            //    	estimatedDays += 1       // current weight on the ship over estimated capacity so we need one more day
            //    	currentWeight = weight   // move the latest over weighted package to the ship on the following day
            //    }
        }

        return currentDay;
    }

    public static void main(String[] args) {
        System.out.println(shipWithinDays(new int[]{1,2,3,4,5,6,7,8,9,10}, 5));
    }
}