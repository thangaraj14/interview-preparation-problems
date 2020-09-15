package geeksforgeeks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordBreakII {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Map<String,List<String>> cache = new HashMap<>();
        backtrack(s,wordDict, cache);
       return cache.get(s);
   }
   
    public List<String> backtrack(String s, List<String> wordDict, Map<String,List<String>> cache){
        
       if(cache.containsKey(s)) return cache.get(s);
        
       List<String> result = new ArrayList<>();
       for(String word: wordDict) {
           if(!s.startsWith(word)) continue;   // string does not start with this word?
           String next = s.substring(word.length());
           if(next.isEmpty()) {                        // awesome!
               result.add(word);
               continue;
           }
           for(String sub: backtrack(next, wordDict, cache))
               result.add(word + " " + sub);
       }
       cache.put(s, result);
       return result;
   } 
}