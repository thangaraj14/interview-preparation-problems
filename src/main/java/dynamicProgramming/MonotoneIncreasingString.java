package dynamicProgramming;

/**
 * https://leetcode.com/problems/flip-string-to-monotone-increasing
 */
public class MonotoneIncreasingString {

    /**
     * * Algorithm:
     *  *
     *  * Skip 0's until we encounter the first 1.
     *  * Keep track of number of 1's in onesCount (Prefix).
     *  * Any 0 that comes after we encounter 1 can be a potential candidate for flip. Keep track of it in flipCount.
     *  * If flipCount exceeds oneCount - (Prefix 1's flipped to 0's)
     *  * a. Then we are trying to flip more 0's (suffix) than number of 1's (prefix) we have.
     *  * b. Its better to flip the 1's instead.
     * @param s
     * @return
     */
    public int minFlipsMonoIncr(String s) {
        char[] ch = s.toCharArray();
        int onescount = 0;
        int flipcount = 0;

        for (int i = 0; i < s.length(); i++) {
            if (ch[i] == '0') {
                if (onescount == 0) continue; // if all beginning char is zero, no need to flip
                flipcount++; // flip 0 -> 1
            } else {
                onescount++; // flip 1 -> 0
            }
            if (flipcount > onescount) { // if flip 0-> 1 greater than flip 1 -> 0 then
                flipcount = onescount; // then we choose flip 1 -> 0 for the left part
            }
        }

        return flipcount;
    }

    /**
     * LIS variation
     * Thought:
     * Since s is a binary string, so an increasing subsequence will either:
     *
     * end at '0'
     * end at '1'
     * For number of changes(flips) to be minimum
     * we need to find the longest part of the string which can be left untouched (i.e which is already increasing).
     *
     * Implementation Details:
     *
     * So if s[i]=='0':
     *
     * current element can extend an increasing subsequence ending at '0'.
     * else if s[i]=='1' current element can extend:
     *
     * an increasing subsequence ending at a '0'
     * an increasing subsequence ending at a '1'
     * which one to choose ? the one which is the largest out of 1) and 2).
     * answer=s.size() - max(lis ending at 0, lis ending at 1)
     */
    public int MinFlipsMonoIncrLIS(String S)
    {
        int zeros = 0, increasingSeq = 0;

        for(int i = 0; i < S.length(); i++)
        {
            if((S.charAt(i) - '0') == 0)
                zeros++;
            else
                increasingSeq++;
        // it can extend both lis ending at 0 and ending at 1 so will choose the best of 2
            increasingSeq = Math.max(increasingSeq, zeros);
        }

        return S.length() - increasingSeq;
    }
}
