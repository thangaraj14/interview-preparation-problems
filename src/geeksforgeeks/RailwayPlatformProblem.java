package geeksforgeeks;

import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/minimum-number-platforms-required-railwaybus-station/
 */
public class RailwayPlatformProblem {

    static int findPlatform(int[] arrival, int[] departure, int n) {
        Arrays.sort(arrival);
        Arrays.sort(departure);

        int platNeeded = 1;
        int result = 1;
        int i = 1;
        int j = 0;

        while (i < n && j < n) {
            if (arrival[i] <= departure[j]) {
                platNeeded++;
                i++;

                if (platNeeded > result)
                    result = platNeeded;
            } else {
                platNeeded--;
                j++;
            }
        }
        return result;
    }

    public static void main(String[] args) {

        int[] arr = {900, 940, 950, 1100, 1500, 1800};
        int[] dep = {910, 1200, 1120, 1130, 1900, 2000};

        int n = arr.length;
        System.out.println("Minimum Number of Platforms Required = " + findPlatform(arr, dep, n));
    }

}
