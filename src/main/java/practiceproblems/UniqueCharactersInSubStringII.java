package practiceproblems;


import java.util.Arrays;

/**
 * https://leetcode.com/problems/total-appeal-of-a-string
 */
public class UniqueCharactersInSubStringII {


    /**
     * Consider string "bcde"
     * <p>
     * 🎯 lets talk about character 'c' that in how many substrings this 'c' will be counted as unique string
     * ::: left no of characters on L.H.S of 'c' = left
     * ::: left no of characters on R.H.S of 'c' = right
     * Then total possible substrings containing this 'c' = (left+1) x (right+1)
     * <p>
     * Thus contributions for c = 2 * 3 = 6
     * and those 6 substrings are ( bc, bcd, bcde, c, cd, cde )
     * <p>
     * So we can say that every character will have contribution of (left+1)x(right+1) to the ans.
     * <p>
     * 🎯 But there is one small catch here !
     * What if characters are same ?? then only one of those characters will have contribution in any substring containing them.
     * "acca"
     * Here first 'c' will for sure have a contribution = (left+1)x(right+1) = 2 x 3 = 6
     * but for second 'c' Left region will be reduced till the rightmost 'c' on leftside. So the value of left for second 'c' will be = 0 !!
     * Right will have no change.
     * So contribution for second 'c' = 1 x 2 = 2;
     * <p>
     * This way we can calculate the contribution for each and every character in just one for loop.
     */
    public long appealSum(String s) {
        int l = s.length();
        int[] lo = new int[26];  //store the last occurrence index of every character
        Arrays.fill(lo, -1);
        long ans = 0;
        for (int i = 0; i < l; i++) {
            int cc = s.charAt(i) - 'a';
            int right = l - i - 1;
            int left;

            int last = lo[cc];
            if (last == -1) left = i;
            else
                left = (i - last) - 1; // this is to ensure that contribution for duplicate char will skip from the prev last seen position
            ans += (left + 1) * (right + 1);
            lo[cc] = i;
        }
        return ans;

    }
}
