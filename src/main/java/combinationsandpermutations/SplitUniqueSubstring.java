package CombinationsAndPermutations;

import java.util.HashSet;
import java.util.Set;

public class SplitUniqueSubstring {
    public int maxUniqueSplit(String s) {
        int[] result= new int[]{0};
        recursionHelper(s, 0, new HashSet<>(), result);
        return result[0];
    }

    /**
     *   if(start == s.length()) {
     *             max = Math.max(max, h.size());
     *         }
     *         String res = "";
     *
     *         for(int i = start;i < s.length();i++) {
     *             res += s.charAt(i);
     *             if(h.contains(res)) continue;
     *             h.add(res);
     *             backtrack(s, i+1, h);
     *             h.remove(res);
     *         }
     *     }
     */
    public void recursionHelper(String s, int index, Set<String> cache, int[] result){
        if(index==s.length()) {
            result[0]=Math.max(result[0],cache.size());
            return;
        }

        for(int i= index;i<s.length();i++){
            String temp = s.substring(index,i+1);
            if(cache.contains(temp)) continue;
            cache.add(temp);
            recursionHelper(s,i+1,cache,result);
            cache.remove(temp);
        }
    }
}
