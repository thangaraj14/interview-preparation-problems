package binarysearch;

import java.util.Arrays;

/**
 * A conveyor belt has packages that must be shipped from one port to another within D days.
 * <p>
 * The ith package on the conveyor belt has a weight of weights[i].
 * Each day, we load the ship with packages on the conveyor belt (in the order given by weights).
 * We may not load more weight than the maximum weight capacity of the ship.
 * <p>
 * Return the least weight capacity of the ship
 * that will result in all the packages on the conveyor belt being shipped within D days.
 *
 * Input: weights = [1,2,3,4,5,6,7,8,9,10], D = 5
 * Output: 15
 * Explanation: A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
 * 1st day: 1, 2, 3, 4, 5
 * 2nd day: 6, 7
 * 3rd day: 8
 * 4th day: 9
 * 5th day: 10
 *
 * Note that the cargo must be shipped in the order given,
 * so using a ship of capacity 14 and splitting the packages into parts like
 * (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.
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
        System.out.println(shipWithinDays(new int[]{1,2,3,4,5,6,7,8,9,10}, 5));
    }
}