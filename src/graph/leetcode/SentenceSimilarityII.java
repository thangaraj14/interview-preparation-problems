package graph.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, determine if two sentences are similar.
 *
 * For example, words1 = ["great", "acting", "skills"] and words2 = ["fine", "drama", "talent"] are similar,
 * if the similar word pairs are pairs = [["great", "good"], ["fine", "good"], ["acting","drama"], ["skills","talent"]].
 * Note that the similarity relation is transitive. For example, if “great” and “good” are similar, and “fine” and “good” are similar, then “great” and “fine” are similar.
 */

public class SentenceSimilarityII {

        public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
            if (words1.length != words2.length) {
                return false;
            }

            UnionFind uf = new UnionFind(2 * pairs.length);

            Map<String, Integer> map = new HashMap<>();
            int id = 0;

            for (String[] pair : pairs) {
                for (String word : pair) {
                    if (!map.containsKey(word)) {
                        map.put(word, id);
                        ++id;
                    }
                }

                uf.union(map.get(pair[0]), map.get(pair[1]));
            }


            for (int i = 0; i < words1.length; i++) {
                String word1 = words1[i];
                String word2 = words2[i];

                if (word1.equals(word2)) {
                    continue;
                }

                if (!map.containsKey(word1) || !map.containsKey(word2) || uf.find(map.get(word1)) != uf.find(map.get(word2))) {
                    return false;
                }
            }
            return true;
        }

        class UnionFind {
            int[] sets;
            int[] size;
            int count;

            public UnionFind(int n) {
                sets = new int[n];
                size = new int[n];
                count = n;

                for (int i = 0; i < n; i++) {
                    sets[i] = i;
                    size[i] = 1;
                }
            }

            public int find(int node) {
                while (node != sets[node]) {
                    node = sets[node];
                }
                return node;
            }

            public void union(int i, int j) {
                int node1 = find(i);
                int node2 = find(j);

                if (node1 == node2) {
                    return;
                }

                if (size[node1] < size[node2]) {
                    sets[node1] = node2;
                    size[node2] += size[node1];
                } else {
                    sets[node2] = node1;
                    size[node1] += size[node2];
                }
                --count;
            }
        }

}
