package practiceproblems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://www.geeksforgeeks.org/print-all-subarrays-with-0-sum/
 *
 * tricky sub array sum
 */


public class SumSubArrayZero {

    private static class Pair {
        int first, second;

        Pair(int a, int b) {
            first = a;
            second = b;
        }

        public String toString() {
            return this.first + "--" + this.second;
        }
    }

    static ArrayList<Pair> findSubArrays(int[] arr, int n) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        ArrayList<Pair> result = new ArrayList<>();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            if (sum == 0)
                result.add(new Pair(0, i));
            List<Integer> al = new ArrayList<>();
            if (map.containsKey(sum)) {
                al = map.get(sum);
                for (Integer integer : al) {
                    result.add(new Pair(integer + 1, i));
                }
            }
            al.add(i);
            map.put(sum, al);
        }
        return result;
    }

    public static void main(String args[]) {
        int[] arr = {6, 3, -1, -3, 4, -2, 2, 4, 6, -12, -7};
        int n = arr.length;

        ArrayList<Pair> out = findSubArrays(arr, n);

        if (out.isEmpty())
            System.out.println("No subarray exists");
        else
            print(out);
    }

    static void print(ArrayList<Pair> out) {
        for (Pair p : out) {
            System.out.println("Subarray found from Index " + p.first + " to " + p.second);
        }
    }
}
