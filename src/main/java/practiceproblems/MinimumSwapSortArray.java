package practiceproblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;


/**
 * https://www.geeksforgeeks.org/minimum-number-swaps-required-sort-array/
 */
public class MinimumSwapSortArray {
    // Return the minimum number
    // of swaps required to sort the array
    public static int minSwaps(int[] nums) {
        int len = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++)
            map.put(nums[i], i);

        Arrays.sort(nums);

        // To keep track of visited elements. Initialize
        // all elements as not visited or false.
        boolean[] visited = new boolean[len];

        // Initialize result
        int ans = 0;
        List<List<Integer>> results = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            List<Integer> temp = new ArrayList<>();
            // already swapped and corrected or
            // already present at correct pos
            if (visited[i] || map.get(nums[i]) == i)
                continue;

            int j = i, cycle_size = 0;
            while (!visited[j]) {
                visited[j] = true;

                // move to next node
                j = map.get(nums[j]); // nums is sorted now remember
                cycle_size++;
                temp.add(nums[j]);
            }

            // Update answer by adding current cycle.
            if (cycle_size > 0) {
                ans += (cycle_size - 1);
                if (cycle_size > 1) {
                    results.add(temp);
                }
            }
        }
        for (List<Integer> t : results) {
            System.out.println(Arrays.toString(t.toArray()));
        }
        return ans;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void swap(ArrayList<Pair> list, int i, int j) {
        Pair temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    /**
     * tricky sort
     */
    public static int minimumSwaps(int[] nums) {

        ArrayList<Pair> pairs = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            pairs.add(new Pair(i, nums[i]));
        }

        pairs.sort(Comparator.comparingInt(a -> a.val));
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == pairs.get(i).idx) continue;

            result++;
            swap(pairs, pairs.get(i).idx, i);
            i -= 1;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(minSwaps(new int[]{10, 19, 6, 3, 5}) + " - " + minimumSwaps(new int[]{10, 19, 6, 3, 5}));
    }

    static class Pair {
        int idx;
        int val;

        public Pair(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }
}