package dsa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://www.geeksforgeeks.org/print-all-subarrays-with-0-sum/
 * https://www.techiedelight.com/find-sub-array-with-0-sum/
 */
class Pair {
    int sum;
    int index;

    Pair(int sum, int index) {
        this.sum = sum;
        this.index = index;
    }

    public String toString() {
        return this.sum + "--" + this.index;
    }
}

public class SumSubArrayZero {

    static ArrayList<Pair> findSubArrays(int[] arr) {

        int n = arr.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        ArrayList<Pair> result = new ArrayList<>();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            if (sum == 0) {
                result.add(new Pair(0, i));
            }
            List<Integer> list = new ArrayList<>();
            if (map.containsKey(sum)) {
                list = map.get(sum);
                for (int val : list) {
                    result.add(new Pair(val + 1, i));
                }
            }
            list.add(i);
            map.put(sum, list);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = { 4, 2, -3, -1, 0, 4 };

        ArrayList<Pair> out = findSubArrays(arr);

        if (out.size() == 0) {
            System.out.println("No subarray exists");
        } else {
            print(out);
        }
    }

    static void print(ArrayList<Pair> out) {
        for (int i = 0; i < out.size(); i++) {
            Pair p = out.get(i);
            System.out.println("Subarray found from Index " + p.sum + " to " + p.index);
        }
    }
}
