package geeksforgeeks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/top-k-frequent-elements/discuss/445815/Java8-Lambda-solution-3-lines-code
 */
public class TopKFrequentElements {

    public List<Integer> topKFrequent(int[] nums, int k) {
        if (nums.length == 0) {
            return Collections.emptyList();
        }

        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(
                (obj1, obj2) -> obj2.getValue() - obj1.getValue());
        Map<Integer, Integer> map = new HashMap<>();

        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> mapEntry : map.entrySet()) {
            pq.add(mapEntry);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(pq.remove().getKey());
        }
        return result;
    }

    public List<Integer> topKFrequentBucketSort(int[] nums, int k) {

        List<Integer> result = new ArrayList<>();

        HashMap<Integer, Integer> map = new HashMap<>(); //Key: val,  Val: #of freq
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        List<Integer>[] bucks = new List[nums.length + 1]; // index : freq; val: set of key （格式特殊）
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
}
