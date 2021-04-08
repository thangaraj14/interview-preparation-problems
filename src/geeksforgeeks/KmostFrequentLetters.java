package geeksforgeeks;

import java.util.*;

/**
 * Given a list of reviews, a list of keywords and an integer k.
 * Find the most popular k keywords in order of most to least frequently mentioned.
 * The comparison of strings is case-insensitive.
 * Multiple occurrences of a keyword in a review should be considered as a single mention.
 * If keywords are mentioned an equal number of times in reviews, sort alphabetically.
 */
public class KmostFrequentLetters {
    public static void main(String[] args) {
        int k1 = 2;
        String[] keywords1 = {"anacell", "cetracular", "betacellular"};
        String[] reviews1 = {"Anacell provides the best services in the city", "betacellular has awesome services",
                "Best services provided by anacell, everyone should use anacell",};
        int k2 = 2;
        String[] keywords2 = {"anacell", "betacellular", "cetracular", "deltacellular", "eurocell"};
        String[] reviews2 = {"I love anacell Best services; Best services provided by anacell",
                "betacellular has great services", "deltacellular provides much better services than betacellular",
                "cetracular is worse than anacell", "Betacellular is better than deltacellular.",};
        //System.out.println(solve(k1, keywords1, reviews1));
        System.out.println(solve(k2, keywords2, reviews2));
    }

    private static List<String> solve(int k, String[] keywords, String[] reviews) {
        List<String> res = new ArrayList<>();
        Set<String> set = new HashSet<>(Arrays.asList(keywords));
        Map<String, Integer> map = new HashMap<>();
        for (String r : reviews) {
            String[] strs = r.split("\\W");
            Set<String> added = new HashSet<>(); // creating a set per review to avoid duplicate within a review
            for (String s : strs) {
                s = s.toLowerCase();
                if (set.contains(s) && !added.contains(s)) {
                    map.put(s, map.getOrDefault(s, 0) + 1);
                    added.add(s);
                }
            }
        }
        PriorityQueue<Map.Entry<String, Integer>> maxHeap = new PriorityQueue<>((a, b) -> a.getValue() == b.getValue() ? a.getKey().compareTo(b.getKey()) : b.getValue() - a.getValue());
        maxHeap.addAll(map.entrySet());
        map.entrySet().forEach(e-> System.out.println(e.getKey() +" "+e.getValue()));
        while (!maxHeap.isEmpty() && k-- > 0) {
            res.add(maxHeap.poll().getKey());
        }
        return res;
    }
}