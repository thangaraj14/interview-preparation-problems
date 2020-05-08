package geeksforgeeks;

public class CheckPermutationContains {
    public boolean checkInclusion(String s1, String s2) {
        if(s1.length()==0 || s2.length()==0) return false;
        int[] cache= new int[26];
        for(char s: s1.toCharArray()){
            cache[s-'a']++;
        }
    
        int right=0;
        
        while(right<s2.length()){
            cache[s2.charAt(right)-'a']--;
            if(right>=s1.length()) cache[s2.charAt(right-s1.length())-'a']++;
            if(allZero(cache)) return true;
            right++;
        }
        return false;
    }
    
    public boolean allZero(int[] cache){
        for(int i=0;i<26;i++){
            if(cache[i]>0) return false; 
        }
        return true;
    }
}