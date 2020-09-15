package stringProblems;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class RearrangeCharactersInString {

    public static String rearrangeString(String S) {
        if (S == null || S.length() == 0) {
            return "";
        }
        Map<Character, Integer> map = new HashMap<>();
        for (char c : S.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> (b.getValue() - a.getValue()));
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            pq.offer(entry);
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            Map.Entry<Character, Integer> first = pq.poll();

            if (sb.length() == 0 || first.getKey() != sb.charAt(sb.length() - 1)) {
                sb.append(first.getKey());
                first.setValue(first.getValue() - 1);

                if (first.getValue() != 0) {
                    pq.offer(first);
                }
            } else {
                Map.Entry<Character, Integer> second = pq.poll();
                // we do not have any other characters that different with current
                // string tail
                if (second == null) {
                    return "";
                }
                sb.append(second.getKey());
                second.setValue(second.getValue() - 1);

                if (second.getValue() != 0) {
                    pq.offer(second);
                }
                // DO NOT FORGET to push top frequency entry into queue as well
                pq.offer(first);
            }
        }
        return sb.toString();
    }

    public static void main(String args[]) {
        String str = "bbbaa";
        System.out.println(rearrangeString(str));
    }
}
