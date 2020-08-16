package geeksforgeeks;

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
        if(s==null || s.length()==0) return false;
        int[] cache= new int[128];
        
        for(char ch: s.toCharArray()){
            cache[ch]++;
        }
        
        int oddCOunt=0;

        for(int i=0;i<128;i++){
            oddCOunt+= cache[i]%2;
            if(oddCOunt>1) return false;
        }
        
        return true;
     }
}