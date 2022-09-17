package dynamicProgramming.palindrome;

/**
 * revise
 * https://leetcode.com/problems/palindrome-partitioning-ii
 */
public class PalindromePartitioningII {

    public static int minCutPalindromicSubstringVariant(String s) {
        int[] cutsDp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            cutsDp[i] = i;
        }

        for (int mid = 0; mid < s.length(); mid++) {
            findMin(mid, mid, cutsDp, s);
            findMin(mid, mid + 1, cutsDp, s);
        }

        return cutsDp[s.length() - 1];
    }

    public static void findMin(int start, int end, int[] cutsDp, String s) {
        for (int i = start, j = end; i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j); i--, j++) {
            int newCut = i == 0 ? 0 : cutsDp[i - 1] + 1;
            cutsDp[j] = Math.min(cutsDp[j], newCut);
        }
    }

    public static void main(String[] args) {
        minCutPalindromicSubstringVariant("nooradars");
    }

    public int minCut(String s) {
        int n, min;
        n = s.length();

        //cut[i] represents minimum number of cuts from String 0 to i
        int[] cut = new int[n];

        //p[i][j] represents String i to j is a palindrome or not
        boolean[][] p = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            min = i;  // Max number of cuts is i for string length i+1
            for (int j = 0; j <= i; j++) {
                // Why i - j < 3  ?
                // 1. String of length 1 is always palindrome so no need to check in boolean table
                // 2. String of length 2 is palindrome if Ci == Cj which is already checked in first part so no need to check again
                // 3. String of length 3 is palindrome if Ci == Cj which is already checked in first part and Ci+1 and Cj-1 is same character which is always a palindrome

                // If String length >=4
                // then check if Ci == Cj and if they are equal check if String[j+1 .. i-1] is a palindrome from the boolean table
                /**
                 * a   b   a   |   c  c
                 *                 j  i
                 *        j-1  |  [j, i] is palindrome
                 *    cut(j-1) +  1
                 */
                if (s.charAt(j) == s.charAt(i) && (i - j < 3 || p[j + 1][i - 1])) {
                    // Its a palindrome as Ci == Cj and String[j+1...i-1] is a palindrome
                    p[j][i] = true;
                    // j == 0 because String from j to i is a palindrome and it starts from first character so means no cuts needed
                    // Else I need a cut at jth location and it will be cuts encountered till j-1 + 1
                    min = j == 0 ? 0 : Math.min(min, cut[j - 1] + 1);
                }
            }
            cut[i] = min;
        }
        return cut[n - 1];
    }


    Integer[][] cache;

    public int minCutRecursive(String s) {
        cache = new Integer[s.length() + 1][s.length() + 1];

        return recursionHelper(s, 0, s.length() - 1);
    }

    public int recursionHelper(String s, int start, int end) {
        if (start == end || isPalin(s, start, end)) {
            return 0;
        }
        if (cache[start][end] != null) return cache[start][end];

        int minCuts = end - start;
        for (int i = start; i <= end; i++) {
            if (isPalin(s, start, i)) {
                minCuts = Math.min(minCuts, 1 + recursionHelper(s, i + 1, end));
            }
        }

        return cache[start][end] = minCuts;
    }

    public boolean isPalin(String s, int start, int end) {
        if (start > end) return false;

        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) return false;

            start++;
            end--;
        }

        return true;

    }
}
