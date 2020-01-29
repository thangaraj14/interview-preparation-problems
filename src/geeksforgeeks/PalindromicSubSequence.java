package geeksforgeeks;

class PalindromicSubSequence {
    public int longestPalindromeSubseq(String s) {
        if(s==null || s.length()==0) return 0;
        
        int[][] dp= new int[s.length()][s.length()];
        
        for(int i=s.length()-1;i>=0;i--){
            for(int j=i;j<s.length();j++){
               if(i==j){ dp[i][j]=1; continue;}
                
                dp[i][j]=s.charAt(i)==s.charAt(j)?2+dp[i+1][j-1]:Math.max(dp[i+1][j],dp[i][j-1]);                
            }
        }
        
        return dp[0][s.length()-1];
        
        
    }


    public int longestPalindromeSubseq1(String s) {
        int[] dp = new int[s.length()];
        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i] = 1;
            int pre = 0;
            for (int j = i + 1; j < s.length(); j++) {
                int tmp = dp[j];
                if (s.charAt(i) == s.charAt(j)) {
                    dp[j] = pre + 2;
                }
                else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
                pre = tmp;
            }
        }
    
        return dp[s.length() - 1];
    }
}