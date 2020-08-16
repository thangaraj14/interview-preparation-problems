package geeksforgeeks;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


/**
 * https://www.geeksforgeeks.org/minimum-number-swaps-required-sort-array/
 */
class MinimumSwapSortArray {
    private static class Pair<T, I> {
        T key;
        I value;

        public T getKey() {
            return key;
        }

        public I getValue() {
            return value;
        }

        public void setKey(T key) {
            this.key = key;
        }

        public void setValue(I value) {
            this.value = value;
        }
    public Pair(T key, I value){
            this.key=key;
            this.value=value;
    }

        @Override
        public String toString() {
            return "Pair{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    public static int minSwaps(int[] arr) {
        int n = arr.length;

        // Create two arrays and use as pairs where first
        // array is element and second array
        // is position of first element
        ArrayList<Pair<Integer, Integer>> arrpos =
                new ArrayList<Pair<Integer, Integer>>();
        for (int i = 0; i < n; i++)
            arrpos.add(new Pair<Integer, Integer>(arr[i], i));

        // Sort the array by array element values to
        // get right position of every element as the
        // elements of second array.
       Collections.sort(arrpos, (a,b)-> {
           if (a.getKey() == b.getKey()) {
               return 0;
           }
           return Integer.compare(b.getKey(), a.getKey());
       });
        // arrpos.sort(new Comparator<Pair<Integer, Integer>>() {
        //     @Override
        //     public int compare(Pair<Integer, Integer> o1,
        //                        Pair<Integer, Integer> o2) {
        //         if (o1.getKey() > o2.getKey())
        //             return -1;

        //             // We can change this to make it then look at the
        //             // words alphabetical order
        //         else if (o1.getKey().equals(o2.getKey()))
        //             return 0;

        //         else
        //             return 1;
        //     }
        // });
        System.out.println(arrpos);
        // To keep track of visited elements. Initialize
        // all elements as not visited or false.
        Boolean[] vis = new Boolean[n];
        Arrays.fill(vis, false);

        // Initialize result
        int ans = 0;

        // Traverse array elements
        for (int i = 0; i < n; i++) {
            // already swapped and corrected or
            // already present at correct pos
            if (vis[i] || arrpos.get(i).getValue() == i)
                continue;

            // find out the number of  node in
            // this cycle and add in ans
            int cycle_size = 0;
            int j = i;
            while (!vis[j]) {
                vis[j] = true;

                // move to next node
                j = arrpos.get(j).getValue();
                cycle_size++;
            }

            // Update answer by adding current cycle.
            if (cycle_size > 0) {
                ans += (cycle_size - 1);
            }
        }

        // Return result
        return ans;
    }

    public static void main(String[] args) {
        int[] a = {1, 5, 4, 3, 2};
        System.out.println(minSwaps(a));
    }
}