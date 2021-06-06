package dsa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/top-k-frequent-elements/
 * https://leetcode.com/problems/top-k-frequent-elements/discuss/445815/Java8-Lambda-solution-3-lines-code
 */
public class TopKFrequentElements {

    public static List<Integer> topKFrequent(int[] nums, int k) {
        if (nums.length == 0) {
            return Collections.emptyList();
        }

        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(
                (obj1, obj2) -> obj2.getValue() - obj1.getValue());
        Map<Integer, Integer> map = new HashMap<>();

        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        pq.addAll(map.entrySet());

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(pq.remove().getKey());
        }
        return result;
    }

    public static List<Integer> topKFrequentBucketSort(int[] nums, int k) {

        List<Integer> result = new ArrayList<>();

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<Integer>[] bucks = new List[nums.length + 1];
        for (int key : map.keySet()) {
            int freq = map.get(key);
            if (bucks[freq] == null) {
                bucks[freq] = new ArrayList<>();
            }
            bucks[freq].add(key);
        }

        for (int freq = nums.length; freq >= 0 && k > 0; freq--) {
            if (bucks[freq] != null) {
                k -= bucks[freq].size();
                result.addAll(bucks[freq]);
            }
        }
        return result;
    }

    public List<String> topKFrequent(String[] words, int k) {

        List<String> result = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            if (map.containsKey(words[i])) {
                map.put(words[i], map.get(words[i]) + 1);
            } else {
                map.put(words[i], 1);
            }
        }

        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((a, b) -> a.getValue() == b.getValue() ?
                b.getKey().compareTo(a.getKey()) :
                a.getValue() - b.getValue());

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            pq.offer(entry);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        while (!pq.isEmpty())
            result.add(0, pq.poll().getKey());

        return result;
    }

    public static void main(String[] args) {
        topKFrequentBucketSort(new int[] { 1, 1, 1, 2, 2, 3 }, 2).stream().forEach(System.out::println);
    }
}
