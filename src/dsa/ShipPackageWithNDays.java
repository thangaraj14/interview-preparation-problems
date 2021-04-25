package dsa;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
 */
public class ShipPackageWithNDays {

    public static int shipWithinDays(int[] weights, int days) {
        if (weights.length == 0 || days == 0) {
            return 0;
        }

        int lowerBound = Arrays.stream(weights).max().orElse(0);
        int upperBound = Arrays.stream(weights).sum();
        int mid;
        while (lowerBound < upperBound) {
            mid = (lowerBound + upperBound) / 2;
            int daysToCarry = binarySearchUtil(weights, mid, days);
            if (daysToCarry <= days) {
                upperBound = mid;
            } else {
                lowerBound = mid + 1;
            }
        }
        return lowerBound;
    }

    public static int binarySearchUtil(int[] weights, int mid, int D) {
        int sum = 0;
        int currentDay = 1;
        for (int weight : weights) {
            if (sum + weight > mid) {
                currentDay += 1;
                sum = 0;
            }
            sum += weight;
        }
        return currentDay;
    }

    public static void main(String[] args) {
        System.out.println(shipWithinDays(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, 5));
    }
}