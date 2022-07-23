package dynamicProgramming.palindrome;

public class CountSubStrings {
    public int countSubstrings(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++)
            count += countSubstrings(s, i, i) + countSubstrings(s, i, i + 1);
        return count;
    }

    private int countSubstrings(String s, int start, int end) {
        int count = 0;
        while (start >= 0 && end < s.length() && s.charAt(start--) == s.charAt(end++))
            count++;
        return count;
    }

    public static int countSubstring(String s) {
        int n = s.length();
        if (n < 2)
            return n;
        int count = n;
        boolean[][] dp = new boolean[n][n];

        // size 1 substrings are palindromes
        for (int i = 0; i < n; i++)
            dp[i][i] = true;

        // for size 2 substrings, check first and last char
        for (int i = 0; i + 1 < n; i++)
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                count++;
            }

        // for size = 3+
        for (int len = 2; len < n; len++) // controls the size of the substring
            for (int i = 0; i + len < n; i++) { // controls the start index
                int j = i + len; // end index
                System.out.print(i + " - " + j + " ");
                // if s.charAt(i) == s.charAt(j) means the substring's first and last are equal letters
                // we don't have to check the contents, we just have to check if lower diagonal is true
                // lower diagonal is the result of the content in between s.charAt(i) == s.charAt(j)

                if ((s.charAt(i) == s.charAt(j)) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    count++;
                }
                System.out.println();
            }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(countSubstring("aaaa"));
    }
}
