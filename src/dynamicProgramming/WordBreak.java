package dynamicProgramming;

import java.util.*;

/**
 * Date 08/01/2014
 * 
 * @author tusroy
 * 
 *         Given a string and a dictionary, split this string into multiple
 *         words such that each word belongs in dictionary.
 * 
 *         e.g peanutbutter -> pea nut butter e.g Iliketoplay -> I like to play
 * 
 *         Solution DP solution to this problem if( input[i...j] belongs in
 *         dictionary) T[i][j] = i else{ T[i][j] = k if T[i][k-1] != -1 &&
 *         T[k][j] != -1
 * 
 *         Test cases 1) Empty string 2) String where entire string is in
 *         dictionary 3) String which cannot be split into words which are in
 *         dictionary 3) String which can be split into words which are in
 *         dictionary
 *
 */
public class WordBreak {

	public String breakWordDP(String word, Set<String> dict) {
		int[][] T = new int[word.length()][word.length()];

		for (int i = 0; i < T.length; i++) {
			for (int j = 0; j < T[i].length; j++) {
				T[i][j] = -1;
			}
		}

		for (int l = 1; l <= word.length(); l++) {
			for (int i = 0; i < word.length() - l + 1; i++) {
				int j = i + l - 1;
				String str = word.substring(i, j + 1);

				if (dict.contains(str)) {
					T[i][j] = i;
					continue;
				}
				// find a k between i+1 to j such that T[i][k-1] && T[k][j] are both true
				for (int k = i + 1; k <= j; k++) {
					if (T[i][k - 1] != -1 && T[k][j] != -1) {
						T[i][j] = k;
						break;
					}
				}
			}
		}
		if (T[0][word.length() - 1] == -1) {
			return null;
		}

		// create space separate word from string is possible
		StringBuffer buffer = new StringBuffer();
		int i = 0;
		int j = word.length() - 1;
		while (i < j) {
			int k = T[i][j];
			if (i == k) {
				buffer.append(word.substring(i, j + 1));
				break;
			}
			buffer.append(word.substring(i, k) + " ");
			i = k;
		}

		return buffer.toString();
	}

	public static void main(String args[]) {
		Set<String> dictionary = new HashSet<String>();
		dictionary.add("I");
		dictionary.add("am");
		dictionary.add("ace");
		String str = "Iamace";
		WordBreak bmw = new WordBreak();
		String result1 = bmw.breakWordDP(str, dictionary);
		System.out.println(bmw.wordBreakBottomUp(str, dictionary));

		//System.out.print(result1);
	}

	/**
	 * to find out it has all the words
	 */
	public boolean wordBreakBottomUp(String s, Set<String> set) {
		//s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]  ans=false
		boolean[] f = new boolean[s.length() + 1];
		//f[i] stands for whether subarray(0, i) can be segmented into words from the dictionary.
		// So f[0] means whether subarray(0, 0) (which is an empty string) can be segmented, and of course the answer is yes.
		//The default value for boolean array is false. Therefore we need to set f[0] to be true.

		f[0] = true;
		for (int i = 1; i <= s.length(); i++) {
			for (int j = i - 1; j >= 0; j--) {
				System.out.println(s.substring(j, i));
				f[i] = f[j] && set.contains(s.substring(j, i));
				if (f[i]) break;
			}
		}
		return f[s.length()];
	}

}