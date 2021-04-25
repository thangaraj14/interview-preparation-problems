package practiceproblems;

public class IsSubsequence {
    // Input: s = "abc", t = "ahbgdc"
    // Output: true
    // Input: s = "axc", t = "ahbgdc"
    // Output: false
    public boolean isSubsequence(String s, String t) {
        if(s==null || t==null) return false;
        int i=0; int j=0;
        
        while(i<s.length() && j<t.length()){
            //System.out.println(s.charAt(i)+" - "+t.charAt(j));
            if(s.charAt(i)==t.charAt(j)){
                i++;
            }
            j++;
        }
        
        return i==s.length();
    }
}