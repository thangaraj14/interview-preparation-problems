package geeksforgeeks;

import java.util.*;

//https://leetcode.com/problems/palindrome-partitioning/
class PalindromePartion {
    public List<List<String>> partition(String s) {
        List<List<String>> result= new ArrayList<>();
        if(s==null || s.length()==0) return result;
        
        backtrackingUtil(s,  result, new ArrayList<String>());
        
        return result;
        
    }
    
    public void backtrackingUtil(String s,  List<List<String>> result, List<String> tempList){
            if(s==null || s.length()==0){
                result.add(new ArrayList<>(tempList));
                return;
            }  
      
        for(int i=1;i<=s.length();i++){
            String temp= s.substring(0,i);
            if(isPalin(temp)){
                tempList.add(temp);
                backtrackingUtil(s.substring(i,s.length()),result,tempList);
                tempList.remove(tempList.size()-1);
            }
        }
        
    }
    
    public boolean isPalin(String s){
        int start=0;
        int end= s.length()-1;
        while(start<=end){
            if(s.charAt(start)!=s.charAt(end)) return false;
            start++;
            end--;
        }
        return true;
    }
    
  public static void main(String[] args) {
      new PalindromePartion().partition("aab");
  }
    
    
}