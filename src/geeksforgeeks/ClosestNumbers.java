package geeksforgeeks;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://www.geeksforgeeks.org/closest-numbers-list-unsorted-integers/
 */
public class ClosestNumbers {

    public static void main(String[] args) {
        //10, 50, 12, 100
        int[] arr = new int[]{5, 4, 3, 2};
        Arrays.sort(arr);

        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length - 1; i++) {
            map.put(arr[i] + "-" + arr[i + 1], arr[i + 1] - arr[i]);
        }
        int min = arr[1] - arr[0];
        for (Map.Entry<String, Integer> m : map.entrySet()) {
            if (min > m.getValue())
                min = m.getValue();
        }

        for (Map.Entry<String, Integer> ma : map.entrySet()) {
            if (ma.getValue().equals(min))
                System.out.println(ma.getKey());
        }
    }
}
