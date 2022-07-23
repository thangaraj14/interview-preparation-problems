package dynamicProgramming.lcs;

public class NumberOfDistinctSubSequence {

    Integer[][] cache;

    public int numDistinct(String s, String t) {
        cache = new Integer[s.length() + 1][t.length() + 1];
        return recursionHelper(s, t, 0, 0);
    }

    public int recursionHelper(String s, String t, int idx1, int idx2) {

        if (cache[idx1][idx2] != null) return cache[idx1][idx2];

        if (idx1 == s.length() && idx2 < t.length()) return 0;
        if (idx2 == t.length()) return 1;

        int result = 0;
        // if both chars are same we skip both, and also we explore by skipping the source index to find the target char elsewhere
        if (s.charAt(idx1) == t.charAt(idx2)) {
            result = recursionHelper(s, t, idx1 + 1, idx2 + 1) + recursionHelper(s, t, idx1 + 1, idx2);
        }else{
            // else we explore by skipping source index only because it's total ways
            result += recursionHelper(s, t, idx1 + 1, idx2);
        }


        return cache[idx1][idx2] = result;
    }

    /**
     * Copy the recurrence relation into tabulation that's all is the trick
     */
    public int numDistinctTopDown(String s, String t) {

        int[][] dp = new int[s.length()+1][t.length()+1];

        for(int i=0;i<=s.length();i++){
            dp[i][0]=1;
        }

        for(int i=1;i<=s.length();i++){
            for(int j=1;j<=t.length();j++){

                if(s.charAt(i-1)==t.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+dp[i-1][j];
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[s.length()][t.length()];
    }
}
