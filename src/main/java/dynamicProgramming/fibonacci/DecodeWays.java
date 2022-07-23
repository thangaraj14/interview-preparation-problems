package dynamicProgramming.fibonacci;


/**
 Case 1 : Pick single element, so in this we pick current and call for index + 1.
 note : In case of single pick, element should not be '0' as it is invalid

 -> ways = decode(s, idx+1, n)
 : elements in range [1,9] is covered here in this case

 Case 2 : Pick couple, so that we can get elements in range [10, 26] .
 Catch here is that we need to check and validate values so that we do not exceed the range.

 */
public class DecodeWays {

    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1; // an empty string can only be decoded as an empty string
        dp[1] = s.charAt(0) != '0' ? 1 : 0; // If current element is 0, we simply return 0 as it is not possible to get a character using 0.
        for (int i = 2; i <= n; i++) {
            int first = Integer.parseInt(s.substring(i - 1, i));
            int second = Integer.parseInt(s.substring(i - 2, i));
            if (first >= 1 && first <= 9) {
                dp[i] += dp[i - 1];
            }
            if (second >= 10 && second <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }

    public int numDecodings1(String s) {
        if (s == null || s.length() == 0) return 0;
        Integer[] cache = new Integer[s.length() + 1];
        return helperFn(s, 0, cache);
    }

    public int helperFn(String s, int index, Integer[] cache) {
//        When we reach the end of the string this means that we have found a possible way to decode.
//        Thus, this will contribute to answer and return 1.
        if (index >= s.length()) return 0;

        if (cache[index] != null) return cache[index];
        int total = 0;
        if (index + 1 <= s.length()) {
            String temp1 = s.substring(index, index + 1);
            if (valid(temp1)) {
                total += 1+helperFn(s, index + 1, cache);
            }
        }

        if (index + 2 <= s.length()) {
            String temp2 = s.substring(index, index + 2);
            if (valid(temp2)) {
                total += 1+helperFn(s, index + 2, cache);
            }
        }

        cache[index] = total;
        return cache[index];

    }

    public boolean valid(String s1) {
        if (s1.length() == 0) return false;
        if (s1.charAt(0) == '0') return false; //If current element is 0, we simply return 0 as it is not possible to get a character using 0.

        int val = Integer.parseInt(s1);

        return val >= 1 && val <= 26;
    }

    public static void main(String[] args) {
        DecodeWays decode = new DecodeWays();
        System.out.println(decode.numDecodings("1210"));
    }
}
