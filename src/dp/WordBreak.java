package dp;

import java.util.*;

/**
 * Date 08/01/2014
 *
 * @author tusroy
 * <p>
 * Given a string and a dictionary, split this string into multiple
 * words such that each word belongs in dictionary.
 * <p>
 * e.g peanutbutter -> pea nut butter e.g Iliketoplay -> I like to play
 * <p>
 * Solution DP solution to this problem if( input[i...j] belongs in
 * dictionary) T[i][j] = i else{ T[i][j] = k if T[i][k-1] != -1 &&
 * T[k][j] != -1
 * <p>
 * Test cases 1) Empty string 2) String where entire string is in
 * dictionary 3) String which cannot be split into words which are in
 * dictionary 3) String which can be split into words which are in
 * dictionary
 * <p>
 * https://leetcode.com/problems/word-break/
 * https://leetcode.com/problems/word-break-ii/
 */
public class WordBreak {

    public static String breakWordDP(String word, Set<String> dict) {

        int[][] arr = new int[word.length()][word.length()];

        Arrays.stream(arr).forEach(ar -> Arrays.fill(ar, -1));

        for (int level = 1; level <= word.length(); level++) {
            for (int i = 0; i < word.length() - level + 1; i++) {
                int j = i + level - 1;
                String str = word.substring(i, j + 1);

                if (dict.contains(str)) {
                    arr[i][j] = i;
                    continue;
                }
                // find a k between i+1 to j such that arr[i][k-1] && arr[k][j] are both true
                for (int k = i + 1; k <= j; k++) {
                    if (arr[i][k - 1] != -1 && arr[k][j] != -1) {
                        arr[i][j] = k;
                        break;
                    }
                }
            }
        }
        if (arr[0][word.length() - 1] == -1) {
            return null;
        }

        // create space separate word from string is possible
        StringBuffer buffer = new StringBuffer();
        int i = 0;
        int j = word.length() - 1;
        while (i < j) {
            int k = arr[i][j];
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
        Set<String> dictionary = new HashSet<>();
        dictionary.add("I");
        dictionary.add("am");
        dictionary.add("ace");
        String str = "Iamace";
        WordBreak bmw = new WordBreak();
        String result1 = bmw.breakWordDP(str, dictionary);
        // System.out.println(bmw.wordBreakBottomUp(str, dictionary));

        String s = "catsanddog";
        List<String> list = new ArrayList<>();
        list.add("cat");
        list.add("cats");
        list.add("and");
        list.add("sand");
        list.add("dog");
        //        wordBreak(s, list).forEach(System.out::println);

        // System.out.print(result1);
    }

    /**
     * to find out it has all the words
     */
    public boolean wordBreakBottomUp(String s, Set<String> set) {
        boolean[] T = new boolean[s.length() + 1];
       /* f[i] stands for whether subarray(0, i) can be segmented into words from the dictionary.
       So f[0] means whether subarray(0, 0) (which is an empty string) can be segmented, and of course the answer is yes.
        The default value for boolean array is false. Therefore we need to set f[0] to be true.*/
        T[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                System.out.println(s.substring(j, i));
                if (T[j] && set.contains(s.substring(j, i))) {
                    T[i] = true;
                    break;
                }
            }
        }
        return T[s.length()];
    }

    public static List<String> wordBreak(String s, List<String> wordDict) {
        return backtrack(s, wordDict, new HashMap<>());
    }

    // backtrack returns an array including all substrings derived from str.
    public static List<String> backtrack(String str, List<String> wordDict, Map<String, List<String>> map) {
        if (map.containsKey(str)) {
            return map.get(str);
        }
        List<String> result = new ArrayList<>();
        for (String word : wordDict)
            if (str.startsWith(word)) {
                String next = str.substring(word.length());
                if (next.length() == 0) {
                    result.add(word);
                } else {
                    for (String sub : backtrack(next, wordDict, map))
                        result.add(word + " " + sub);
                }
            }
        map.put(str, result);
        return result;
    }
}