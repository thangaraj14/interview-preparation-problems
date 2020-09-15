package dynamicProgramming.lcs;

public class LongestCommonSubsequence {

	public static void main(String[] args) {
		String str1 = "ABCD";
		String str2 = "AEDB";

		System.out.println(getLCS(str1.toCharArray(), str2.toCharArray()));

	//	System.out.println(lcs(str1.toCharArray(), str2.toCharArray(), str1.length(), str2.length()));
	}

	private static int getLCS(char[] str1, char[] str2) {

		int temp[][] = new int[str1.length + 1][str2.length + 1];
		int max = 0;
		for (int i = 1; i < temp.length; i++) {
			for (int j = 1; j < temp[i].length; j++) {
				if (str1[i - 1] == str2[j - 1]) {
					temp[i][j] = temp[i - 1][j - 1] + 1;
				} else {
					temp[i][j] = Math.max(temp[i][j - 1], temp[i - 1][j]);
				}
				if (temp[i][j] > max) {
					max = temp[i][j];
				}
			}
		}
		return max;
	}

	public int longestCommonSubsequence(String text1, String text2) {
		if(text1==null || text2==null) return 0;

		int[][] dp= new int[text1.length()+1][text2.length()+1];

		for(int i=1;i<=text1.length();i++){
			for(int j=1; j<=text2.length(); j++){
				if(text1.charAt(i-1)==text2.charAt(j-1)){
					dp[i][j]= 1+dp[i-1][j-1]; // previously matched characters
				}else{
					dp[i][j]= Math.max(dp[i][j-1],dp[i-1][j]);
				}
			}
		}

		return dp[text1.length()][text2.length()];
	}

	static int lcs(char[] X, char[] Y, int m, int n) {
		if (m == 0 || n == 0)
			return 0;
		if (X[m - 1] == Y[n - 1])
			return 1 + lcs(X, Y, m - 1, n - 1);
		else
			return Math.max(lcs(X, Y, m, n - 1), lcs(X, Y, m - 1, n));
	}

	public int longestCommonSubsequenceRec(String text1, String text2) {
		if(text1==null || text2==null) return 0;

		Integer[][] dp= new Integer[text1.length()+1][text2.length()+1];

		longestCommonSubsequenceUtil(text1, text1.length(), text2, text2.length(), dp);

		return dp[text1.length()][text2.length()];
	}

	public int longestCommonSubsequenceUtil(String text1,int i, String text2,int j, Integer[][] dp ){
		if(i<=0 || j<=0) return 0;

		if(dp[i][j]!=null) return dp[i][j];

		if(text1.charAt(i-1)==text2.charAt(j-1)){
			dp[i][j]=1+longestCommonSubsequenceUtil(text1, i-1, text2, j-1, dp);
		}else{
			dp[i][j]= Math.max(longestCommonSubsequenceUtil(text1, i, text2, j-1, dp), longestCommonSubsequenceUtil(text1, i-1, text2, j, dp));
		}

		return dp[i][j];
	}

}


