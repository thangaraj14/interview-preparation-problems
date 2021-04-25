package practiceproblems;

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
}
