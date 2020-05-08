package geeksforgeeks;
public class CheckPalindromePermutation {
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