package practiceproblems;

import java.util.Arrays;

/**
 * Given a string, determine if a permutation of the string could form a palindrome.
 * Input: "code"
 * Output: false
 * Input: "carerac"
   Output: true
 */
public class CheckPalindromePermutation {
    // If a string with an even length is a palindrome, every character in the string must always occur an even number of times. 
    // If the string with an odd length is a palindrome, every character except one of the characters must always occur an even number of times. 
    // Thus, in case of a palindrome, the number of characters with odd number of occurrences can't exceed 1
   
    public boolean canPermutePalindrome(String s) {
        int[] cache = new int[26];

        for(char t: s.toCharArray()){
            if(cache[t-'a']>0){
                cache[t-'a']--;
            }else{
                cache[t-'a']++;
            }
        }

        int result = Arrays.stream(cache).sum();

        return result<=1;
     }
}