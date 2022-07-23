package strings.stringProblems;

import java.util.Arrays;

public class LongestCommonPrefix {

    /**
     * The first thing to understand is that the longest common prefix can only be as long as the shortest string with a commo prefix in the array.
     * So, when we sort, the shortest string with a common prefix will be the first string (assuming ascending order).
     *
     * Then, we have to understand that the longest common prefix must apply for ALL array elements,
     * if there's an array element that does not have the longest common prefix you've found so far, then there is no prefix, it's empty string.
     * So, for example, if the first string is "aaa" and last string comes out to be "baa", then there is no common prefix.
     *
     * ["HAM",.........."HAMPER"]
     * So, here the first and only the last is compared because if all the middle starts with "HAM" and even the last one is "HAM ",
     * that means all the middle one would have had "HAM" in them.
     *
     * Arrays.Sort sorts the string in a lexicographical order. Which means that the order would be following:
     * AAA
     * AAAA
     * AAB
     * BAA
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        if (strs.length == 1) return strs[0];

        StringBuilder sb = new StringBuilder();
        Arrays.sort(strs);
        char[] first = strs[0].toCharArray();
        char[] last = strs[strs.length - 1].toCharArray();
        for (int i = 0, j = 0; i < first.length && j < last.length; i++, j++) {
            if (first[i] != last[j]) break;
            sb.append(first[i]);
        }
        return sb.toString();
    }
}
