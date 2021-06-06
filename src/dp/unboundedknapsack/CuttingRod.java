package dp.unboundedknapsack;

import java.util.Arrays;

/**
 * http://www.geeksforgeeks.org/dynamic-programming-set-13-cutting-a-rod/
 * https://www.youtube.com/watch?v=IRwVmTmN6go&ab_channel=TusharRoy-CodingMadeSimple
 */
public class CuttingRod {

    public int maxValue(int[] price) {
        int[] max = new int[price.length + 1];
        int n = price.length;
        // i is no.of.cuts
        // price is value
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                System.out.println(
                        "max[" + j + "] : " + max[j] + " , max[" + (j - i) + "] : " + max[j - i] + "+  price[" + (i - 1)
                                + "] : " + price[i - 1]);
                // add previous value or current value
                max[j] = Math.max(max[j], max[j - i] + price[i - 1]);
                System.out.println(Arrays.toString(max));
            }
        }
        return max[price.length];
    }

    public static void main(String[] args) {
        CuttingRod cr = new CuttingRod();
        int[] price = { 1, 5, 3, 6 };
        System.out.println(cr.maxValue(price));
    }
}