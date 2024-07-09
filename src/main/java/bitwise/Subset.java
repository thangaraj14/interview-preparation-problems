package bitwise;

import java.util.ArrayList;
import java.util.List;

public class Subset {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n=nums.length;
        /*

         Iteration 1: num = 0 (000 in binary), loop skips.
         Iteration 2: num = 1 (001), subset = [1].
         Iteration 3: num = 2 (010), subset = [2].
         Iteration 4: num = 3 (011), subset = [1, 3].
         Iteration 5: num = 4 (100), subset = [3].
         Iteration 6: num = 5 (101), subset = [2, 3].
         Iteration 7: num = 6 (110), subset = [1, 2].
         Iteration 8: num = 7 (111), subset = [1, 2, 3].
         */
        for (int i = 0; i < (1 << n); i++) {
            ArrayList<Integer> in = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    in.add(nums[j]);
                }
            }
            result.add(in);
        }

        return result; // total complexity: n.2^n
    }
}
