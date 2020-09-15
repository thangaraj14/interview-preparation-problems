package geeksforgeeks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TopKFrequentElement{
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
 
        Map<Integer, Integer> map = new HashMap<>(); //Key: val,  Val: #of freq
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num)+1);
            }else {
                map.put(num, 1);
            }
        }
        
        List<Integer>[] bucks = new List[nums.length+1]; // index : freq; val: set of key 
        for (int key : map.keySet()) {
            int freq = map.get(key);
            if (bucks[freq] == null) {
                bucks[freq] = new ArrayList<>();
            }
            bucks[freq].add(key);
        }
        
        for (int freq = nums.length; freq >=0 && k > 0; freq--) {
            if (bucks[freq] != null) {
                k -=bucks[freq].size();
                result.addAll(bucks[freq]);
            }
        }
        return result;
    }
}