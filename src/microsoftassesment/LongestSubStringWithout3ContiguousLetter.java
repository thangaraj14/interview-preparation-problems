package microsoftassesment;

import java.util.HashMap;
import java.util.Map;


//Given a string s containing only a and b, find longest substring of s such that s does not contain more than two contiguous occurrences of a and b.
//
//        Example 1:
//
//        Input: "aabbaaaaabb"
//        Output: "aabbaa"
public class LongestSubStringWithout3ContiguousLetter {

    public  String validLongestSubstring(String s) {
        if (s.length() < 3)
            return s;
        int cur = 0, // current starting pointer for current substring
            end = 1; // the look-ahead pointer that's at the end of substring
        char c = s.charAt(0);   // current character/letter
        int count = 1; // counter for same letter consecutive appearance
        int maxLen = 1; // result
        int start = 0; // anchor pointer for the result's starting index

        while (end < s.length()) {
            if (s.charAt(end) == c) { // saw the same letter again
                count ++;

                // valid enough to consider to be a part of the substring
                if (count == 2) {
                    if (end - cur + 1 > maxLen) {  // length of the current substring is greater than maxlen
                        maxLen = end - cur + 1; // the "+1" is to include the full substring length
                        start = cur;   // override to be the current maxlen's start index
                    }
                }
                else {
                    cur = end - 1; // count > 2 whenever we see 3 consec element, move the curr , therefore we need to start a new substring; reset curr
                }
            }
            else {
                c = s.charAt(end);  // diff char/letter found; reset current char
                count = 1; // reset same letter consecutive appearance counter
                if (end - cur + 1 > maxLen) {    // length of the current substring is greater than maxlen
                    maxLen = end - cur + 1;
                    start = cur;
                }
            }
            end ++;
        }
        return s.substring(start, start + maxLen);
    }

    public String slidingWindowPattern(String s){
        if(s==null) return null;
        Map<Character,Integer> map= new HashMap<>();
        int left=0;
        int right=0;
        int count=0;
        int start=0;
        int result=0;
        int end=0;
        while(left<s.length()){
            char temp= s.charAt(left);
            map.put(temp, map.getOrDefault(temp,0)+1);
            if(map.get(temp)>=3){
                count++;
            }

            while(count>0 ){
                char rightchar=s.charAt(right);
                map.put(rightchar,map.getOrDefault(right,1)-1);
                if(map.get(temp)<=2) count--;
                right++;
                //count--;
            }

            if(result<left-right+1){
                result=left-right+1;
                start=right;
                end= left;
            }
            left++;
        }

        System.out.println("result "+ result);

        return s.substring(start, start+result);
    }

    public static void main(String[] args) {
        System.out.println( new LongestSubStringWithout3ContiguousLetter().validLongestSubstring("aabbaaaaabb"));
        System.out.println( new LongestSubStringWithout3ContiguousLetter().validLongestSubstring("aabbaabbaabbaa"));
        System.out.println( new LongestSubStringWithout3ContiguousLetter().validLongestSubstring("abbaabbaaabbaaa"));
        System.out.println("------------------------------------------");
        System.out.println( new LongestSubStringWithout3ContiguousLetter().slidingWindowPattern("aabbaaaaabb"));
        System.out.println( new LongestSubStringWithout3ContiguousLetter().slidingWindowPattern("aabbaabbaabbaa"));
        System.out.println( new LongestSubStringWithout3ContiguousLetter().slidingWindowPattern("abbaabbaaabbaaa"));
    }
}
