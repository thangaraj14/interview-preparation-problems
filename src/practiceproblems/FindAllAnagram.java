package practiceproblems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 *
 * Strings consists of lowercase English letters only and the length of
 * both strings s and p will not be larger than 20,100.
 *
 * The order of output does not matter.
 *
 * Input:
 * s: "cbaebabacd" p: "abc"
 *
 * Output:
 * [0, 6]
 *
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 */
public class FindAllAnagram {

    public List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> map = new HashMap<>();
        for(char ch: p.toCharArray()){
            map.put(ch, map.getOrDefault(ch,0)+1);
        }
        int counter= map.size();

        int start=0; int end=0;
        List<Integer> result= new ArrayList<>();
        while(end<s.length()){
            char temp=s.charAt(end);
            if(map.containsKey(temp)){
                map.put(temp, map.get(temp)-1);
                if(map.get(temp)==0) counter--;
            }
            end++;

            while(counter==0){
                char begin= s.charAt(start);
                if(map.containsKey(begin)){
                    map.put(begin, map.get(begin)+1);
                    if(map.get(begin)>0) counter++;
                }
                if(end-start==p.length()) {
                    result.add(start);
                }
                start++;
            }
        }

        return result;
    }
}
