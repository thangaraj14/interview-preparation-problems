package practiceproblems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MinIncrementToMakeArrayUnique {

    /**
     * O(N^2)
     *
     * @param A
     * @return
     */
    public int minIncrementForUniqueBruteForce(int[] A) {
        if (A == null || A.length == 0) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : A) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        int result = 0;
        for (int i = 0; i < A.length; i++) {
            if (map.get(A[i]) > 1) {
                int temp = A[i];
                while (map.containsKey(temp)) {
                    temp++;
                    result++;
                }
                map.put(A[i], map.get(A[i]) - 1);
                map.put(temp, map.getOrDefault(i, 0) + 1);
                A[i] = temp;
            }
        }
        //System.out.println(Arrays.toString(A));

        return result;
    }


    /**
     * tricky make array unique
     * @param A
     * @return
     */
    public int minIncrementForUniqueSort(int[] A) {
        Arrays.sort(A);
        int count = 0;
        /**
         * in the case of [1,1,2,2,3,3]:
         * A[1] is incremented to 2 (+1)
         *  [1,2,2,2,3,3]
         * A[2] is incremented to 3 (+1)
         *  [1,2,3,2,3,3]
         * A[3] is incremented to 4 (+2)
         *  [1,2,3,4,3,3]
         * A[4] is incremented to 5, (+2)
         *  [1,2,3,4,5,3]
         * A[5] is incremented to 6, (+3)
         *  [1,2,3,4,5,6]
         */
        for (int i = 1; i < A.length; i++) {
            //condition '<=' is because [1,2,2,2,3,7] when optimising this array
            // when i=2 completes iteration the arr becomes [1,2,3,2,3,7]
            // when i=3 we need to check if nums[i]<=nums[i-1]
            if (A[i] <= A[i - 1]) {
                int diff = A[i - 1] - A[i] + 1;
                A[i] = A[i] + diff;
                count += diff;
            }
        }

        return count;
    }


    /**
     * tricky array processing
     * O(N)
     *
     * @param arr
     * @return
     */
    public int minIncrementForUnique(int[] arr) {
        if (arr == null || arr.length == 0)
            return 0;

        int moves = 0;
        int[] freq = new int[110000]; //given the max num = 40000 and array length = 39999, the worst case will fit in 80000
        for (int num : arr)
            freq[num]++;

        for (int i = 0; i < freq.length - 1; i++) {
            if (freq[i] <= 1) //no need to move anything!
                continue;
            //consider an example like where frequency of number 3 is 4
            //remaining that needs to be "reevaluated" is 3 (since one 3 is valid in this slot)
            //if we were to add 1 to value 3, it is 4
            //since we have frequency of 3, its like now 4 has 3 frequency
            //we repeat this process
            int remain = freq[i] - 1;
            moves += remain;
            freq[i + 1] += remain;
        }
        return moves;
    }
}
