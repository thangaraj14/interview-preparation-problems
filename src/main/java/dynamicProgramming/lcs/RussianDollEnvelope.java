package dynamicProgramming.lcs;

import java.util.Arrays;

public class RussianDollEnvelope {

    public int maxEnvelopes(int[][] envelopes) {

        /**
         *  sort envelopes by width (envelopes[i][0]), then we only need to consider height
         *     //if two envelopes have same width, sort them by descending order
         *     //because [3, 4] cannot contains [3, 3], so we need to put [3, 4] before [3, 3] when sorting,
         *     //otherwise it will be counted as an increasing number if the order is [3, 3], [3, 4]
         *     //but we actually do not want to count them as valid russian doll envelopes
         *
         *     prevent calculating the envelope with the same width? For example [3, 1] [3, 2] [3, 3] will get 3, but [3, 3], [3, 2], [3, 1] will get 1.
         */
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] == b[0]) return Integer.compare(b[1], a[1]);
            return Integer.compare(a[0], b[0]);
        });
        int result = 0;

        /**
         * //KEY POINTS: after sorting them by width with increasing order, we need to find Longest Increasing Subsequence
         *     //by traversing height of each envelope, then we get the final result
         *     //store tails of each increasing subsequence with different length
         *     /*eg: 3, 5, 1, 8, 2, 12
         *      * 1
         *      * 1, 2
         *      * 3, 5, 8
         *      * 3, 5, 8, 12
         *      * tails = {1, 2, 8, 12}
         *
         *     //we do not care about what elements are in each subsequence, we only care about
         *     //tails of them, because every time we only compare with their tails to decide
         *     //which subsequence could we add new item and update the entire structure
         */
        int[] increasingHeight = new int[envelopes.length];

        for (int[] envelope : envelopes) {

            int left = 0;
            int right = result;
            while (left < right) {

                int mid = left + (right - left) / 2;
                if (increasingHeight[mid] < envelope[1]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            increasingHeight[left] = envelope[1];
            if (left == result) {
                result++;
            }
        }

        return result;
    }
}
