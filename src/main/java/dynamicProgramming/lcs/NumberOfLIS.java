package dynamicProgramming.lcs;

import java.util.Arrays;

/**
 * tricky lis
 * https://www.youtube.com/watch?v=_eHbuLHo6pM&ab_channel=LearnCodeRepeat
 *
 * While you're iterating over all the elements from '0' to 'i-1',
 *
 * First check whether the addition of the current element will form a LIS or not.
 * (This statements denotes to check condition of nums[i] > nums[j])
 *
 * If a LIS is forming with the inclusion of element nums[i], then only a simple problem remains - Whether a subsequece of an equal length is already present or not.
 *
 * To resolve this problem, check whether you've already acheived a LIS of that length or not by simply comparing with the count[i].
 *
 * If yes, the count all the subsequences that have been formed at index 'j' and add them to the subsequences formed without subsequences ending at nums[j].
 * (This statement denotes - dp[j] + 1 == dp[i])
 *
 * If not, then count all the subsequences formed with the subsequences ending at nums[j]
 * (This statement denotes - dp[j] + 1 > dp[i])
 *
 * Arr => 1, 3, 5, 4, 7
 * dp =>  1, 2, 3, 3, 4
 * cnt=>  1, 1, 1, 1, 2  when i at 7 and j at 4 dp[j] + 1 == dp[i]  so count is 2
 */
public class NumberOfLIS {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        //dp[i] will store the length of Longest Increasing Subsequence, ending at nums[i].
        int[] dp = new int[n];
        //count[i] will store the total number of Longest Increasing Subsequences, ending at nums[i].
        int[] count = new int[n];

        Arrays.fill(dp,1);
        Arrays.fill(count,1);
        //lis : length of Longest Increasing Subsequence.
        int lis = 0;
        //res : total number of subsequences of length lis.
        int res = 0;
        for (int i = 1; i < n; i++) {
            //Checking it's previous values.
            for (int j = 0; j < i; j++) {
                //If any previous value of nums[i] is less than it, then only it can be appended(as we know the
                //subsequence would have ended at nums[j]).
                if (nums[j] < nums[i]) {
                    //If dp[i] is equal to dp[j] + 1, meaning a different sequence has been found of same
                    //length, so increase count[i] by count[j].
                    if (dp[i] == dp[j] + 1) {
                        count[i] += count[j];
                    }
                    //Else, if dp[i] is less than dp[j] + 1, meaning length will increase as sequence will have a
                    //new element, so store dp[j] + 1 in dp[i] and count[j] in count[i].
                    else if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    }
                }
            }

            //If lis is equal to dp[i], meaning a new sequence is found of same length, so add count[i] in res.
            if (lis == dp[i]) {
                res += count[i];
            }
            //Else if lis is less than dp[i], meaning a new sequence is formed of greater length, so store the
            //new increased length in lis and count[i] in res.
            else if (lis < dp[i]) {
                lis = dp[i];
                res = count[i];
            }
        }

        return res;
    }
}
