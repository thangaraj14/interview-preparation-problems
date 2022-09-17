package dynamicProgramming.lcs;

/**
 * General Idea: Credit: https://leetcode.com/problems/wildcard-matching/discuss/370736/Detailed-Intuition-From-Brute-force-to-Bottom-up-DP
 * The idea is pretty straightforward : scan S and P while there is a match between the current character of S and the current character of P.
 * If we reach the end of both strings while there is still a match, return True, otherwise return False.
 * The scan is done by having a pointer in S and a pointer in P.

 Example: S="code"
 The character 'c' of S matches the first character of P if the first character of P is:

 'c'
 '?'
 '*'

 Case 1:
 When the first character of P is a lowercase letter different from 'c', return False.

 Case 2:
 If the first character of P is 'c' or '?', we move both pointers one step to the right.

 Case 3:
 If the first character of P is '*', we have 2 possibilities:

 - '*' matches 0 character : in this case we move the pointer in P one step, ie will ignore the whole pattern
 - '*' matches 1 or more characters : in this case we move the pointer in S one step, ie we consider pattern
 And we continue like this for each two positions taken by the two pointers.

 - If we reach the end of P but there is still characters from S, simply return .. False !
 - If we reach the end of S and there is still characters from P, the only case when there is a match is that all the remaining characters in P are '*',
 in this case these stars will be matched with the empty string.
 **/
public class WildCardMatching {

    public boolean isMatch(String s, String p) {

        return isMatchApproach1Helper(0, 0, s, p);
    }

    public boolean isMatchApproach1Helper(int tIdx, int pIdx, String text, String pattern) {

        // reached the end of both S and P
        if (tIdx == text.length() && pIdx == pattern.length()) {
            return true;
        }
        // there are still characters in S => there is no match
        else if (pIdx == pattern.length()) {
            return false; // Can't have a non-empty s match an empty p.
        }
        // if we reached end of text and pattern is still left.
        // Try to see if p at or after this stage is only * or ** or *** etc. Only way to match an empty text.
        else if (tIdx == text.length()) {
            return pattern.charAt(pIdx) == '*' && isMatchApproach1Helper(tIdx, pIdx + 1, text, pattern);
        }
        // Here cuz text & pattern match atleast a char
        else if (text.charAt(tIdx) == pattern.charAt(pIdx) || pattern.charAt(pIdx) == '?') {
            // Match here if strs from next index onward also are a match. Delegate job to recursive func for latter.
            return isMatchApproach1Helper(tIdx + 1, pIdx + 1, text, pattern);
        }

        // star either matches 0 or >=1 character
        else if (pattern.charAt(pIdx) == '*') {
            // 1: When * matches an empty seq, it's work is done. Hence, the next stage to check match for is w/o *.
            // 	  Also, there could be *s in line. So, consuming this *, could exhibit new p with next fresh *.
            // 2: '*' can match seq of chars. Hence, * kept. Further rec stages could use it to match more chars/empty.
            return isMatchApproach1Helper(tIdx, pIdx + 1, text, pattern)
                    || isMatchApproach1Helper(tIdx + 1, pIdx, text, pattern);
        }

        return false;
    }

    /*******************
     * Approach 3: Bottom Up Tabulation: https://www.youtube.com/watch?v=3ZDZ-N0EPV0
     * Bottom-up the smallest is (0, 0)
     *
     * 				|	dp[i-1][j-1]  if str[i] == pattern[j] || pattern[j] == '?'
     * 				|
     * 				|	if pattern[j-1] == '*'
     * dp[i][j] = 	|		dp[i][j-1] || dp[i-1][j]
     * 				|
     * 				|	False
     *
     * Time Complexity : O(m * n)
     * Space Complexity : O(m * n)
     **********************************/
	public boolean isMatchBottomUp(String s, String t) {
        //First, we need to create a 2d dp table dp. The size of this table is (s.size() + 1) * (p.size() + 1).
        // We introduce +1 here to better handle the edge cases where we have an empty string or an empty pattern.
		boolean[][] dp= new boolean[s.length()+1][t.length()+1];

        //When both the string and the pattern are empty.
        //Always match. dp[0][0] = true
		dp[0][0]= true;

		for(int i=1;i<=t.length();i++){
			if(t.charAt(i-1)=='*')
				dp[0][i]= dp[0][i-1];
		}

		for(int i=1;i<=s.length();i++){
			for(int j=1;j<=t.length();j++){

				if(t.charAt(j-1)=='?' || s.charAt(i-1)==t.charAt(j-1)){
					dp[i][j]=dp[i-1][j-1];

				}else if(t.charAt(j-1)=='*'){
					dp[i][j]= dp[i-1][j] || dp[i][j-1];
				}else{
					dp[i][j]=false;
				}
			}
		}
		return dp[s.length()][t.length()];
	}

    public static void main(String args[]) {
        WildCardMatching wcm = new WildCardMatching();
        System.out.println(wcm.isMatch("xbylmz", "x?y***z"));

    }

}