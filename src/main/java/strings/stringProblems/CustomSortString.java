package strings.stringProblems;

import java.util.Arrays;

public class CustomSortString {

    public String customSortStringApproach1(String order, String str) {
        int[] cache = new int[26];
        //Arrays.fill(cache,Integer.MAX_VALUE-1);
        for (int i = 0; i < order.length(); i++) {
            cache[order.charAt(i) - 'a'] = i;
        }
        Character[] tmpArr = new Character[str.length()];

        for (int i = 0; i < str.length(); i++) {
            tmpArr[i] = str.charAt(i);
        }
        Arrays.sort(tmpArr, (a, b) ->
                Integer.compare(cache[a - 'a'], cache[b - 'a'])
        );
        StringBuilder sb = new StringBuilder();
        for (Character i : tmpArr) {
            sb.append(i);
        }
        return sb.toString();
    }

    public String customSortString(String order, String str) {
        int[] cache = new int[26];
        // take target's char counts
        for (char c : str.toCharArray()) {
            cache[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        // greedily populate the order chars as first
        for (char c : order.toCharArray()) {
            if (cache[c - 'a'] == 0) continue;
            int occurence = cache[c - 'a'];
            while (occurence-- > 0) {
                sb.append(c);
            }
            cache[c - 'a'] = 0;
        }
        // then populate the non order chars
        for (int i = 0; i < 26; i++) {
            int occurrence = cache[i];
            while (occurrence-- > 0) {
                sb.append((char) (i + 'a'));
            }
            cache[i] = 0;
        }
        return sb.toString();
    }
}
