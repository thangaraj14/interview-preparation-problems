package dynamicProgramming.lcs;

/**
 * 
 * @author Tushar Roy
 *
 *         Given two strings how many minimum edits(update, delete or add) is
 *         needed to convert one string to another
 *
 *         Time complexity is O(m*n) Space complexity is O(m*n)
 *
 *         References:
 *         http://www.geeksforgeeks.org/dynamic-programming-set-5-edit-distance/
 *         https://en.wikipedia.org/wiki/Edit_distance
 */
public class EditDistance {


	/**
	 * Uses bottom up DP to find the edit distance
	 */
	public int dynamicEditDistance(char[] str1, char[] str2) {
		int temp[][] = new int[str1.length + 1][str2.length + 1];

		for (int i = 0; i < temp[0].length; i++) {
			temp[0][i] = i; // it's number of edits to make top row empty, remember dp[0][0]="" empty char
		}

		for (int i = 0; i < temp.length; i++) {
			temp[i][0] = i; // it's number of edits to make empty char into first col values, remember dp[0][0]="" empty char
		}

		for (int i = 1; i <= str1.length; i++) {
			for (int j = 1; j <= str2.length; j++) {
				if (str1[i - 1] == str2[j - 1]) {
					temp[i][j] = temp[i - 1][j - 1];
				} else {
					temp[i][j] = 1 + min(temp[i - 1][j - 1], temp[i - 1][j], temp[i][j - 1]);
				}
			}
		}
		printActualEdits(temp, str1, str2);
		return temp[str1.length][str2.length];

	}

	/**
	 * Prints the actual edits which needs to be done.
	 */
	public void printActualEdits(int T[][], char[] str1, char[] str2) {
		int i = T.length - 1;
		int j = T[0].length - 1;
		while (true) {
			if (i == 0 || j == 0) {
				break;
			}
			if (str1[i - 1] == str2[j - 1]) {
				i = i - 1;
				j = j - 1;
			} else if (T[i][j] == T[i - 1][j - 1] + 1) {
				System.out.println("Edit " + str2[j - 1] + " in string2 to " + str1[i - 1] + " in string1");
				i = i - 1;
				j = j - 1;
			} else if (T[i][j] == T[i - 1][j] + 1) {
				System.out.println("Delete in string1 " + str1[i - 1]);
				i = i - 1;
			} else if (T[i][j] == T[i][j - 1] + 1) {
				System.out.println("Delete in string2 " + str2[j - 1]);
				j = j - 1;
			} else {
				throw new IllegalArgumentException("Some wrong with given data");
			}
		}
	}

	public int minDistance(String word1, String word2) {
		if(word1==null) return word2.length();
		if(word2==null) return word1.length();

		int[][] dp= new int[word1.length()+1][word2.length()+1];

		for(int i = 0;i<=word1.length();i++) dp[i][0] = i;
		for(int j = 0;j<=word2.length();j++) dp[0][j] = j;

		for(int i=1;i<=word1.length();i++){
			for(int j=1; j<= word2.length(); j++){
				if(word1.charAt(i-1)==word2.charAt(j-1)){
					dp[i][j]=dp[i-1][j-1];
				}else{
					dp[i][j]= 1+Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]));
				}
			}
		}

		return dp[word1.length()][word2.length()];
	}

	Integer[][] cache;
	public int minDistanceTopDown(String word1, String word2) {
		cache= new Integer[word1.length()][word2.length()];
		return recursionHelper(word1,word2,0,0);
	}

	public int recursionHelper(String word1, String word2, int idx1, int idx2){
		if(idx1>=word1.length()) return word2.length()-idx2;
		if(idx2>=word2.length()) return word1.length()-idx1;

		if(cache[idx1][idx2]!=null) return cache[idx1][idx2];

		int result=0;
		if(word1.charAt(idx1)==word2.charAt(idx2)){
			result+= recursionHelper(word1,word2,idx1+1,idx2+1);
		}else{
			result+= 1+ Math.min(recursionHelper(word1,word2,idx1+1,idx2+1),
					Math.min(recursionHelper(word1,word2,idx1+1,idx2),recursionHelper(word1,word2,idx1,idx2+1)));
		}

		return cache[idx1][idx2]=result;
	}
	private int min(int a, int b, int c) {
		int l = Math.min(a, b);
		return Math.min(l, c);
	}

	public static void main(String args[]) {
		String str1 = "abcd";
		String str2 = "bcad";
		EditDistance editDistance = new EditDistance();
		int result = editDistance.dynamicEditDistance(str1.toCharArray(), str2.toCharArray());
		System.out.print(result);
	}

}


