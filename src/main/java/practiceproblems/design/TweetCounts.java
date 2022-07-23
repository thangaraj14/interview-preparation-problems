package practiceproblems.design;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/tweet-counts-per-frequency/
 *
 * tricky tree map
 */
class TweetCounts {
    private Map<String, TreeMap<Integer, Integer>> tweetsCache;

    public TweetCounts() {
        tweetsCache = new HashMap<>();
    }

    public void recordTweet(String tweetName, int time) {

        tweetsCache.putIfAbsent(tweetName, new TreeMap<>());

        TreeMap<Integer, Integer> timeToFreq = tweetsCache.get(tweetName);
        timeToFreq.put(time, timeToFreq.getOrDefault(time, 0) + 1);

    }

    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        int interval = 1;
        if (!tweetsCache.containsKey(tweetName)) {
            return Collections.emptyList();
        }
        if (freq.equals("minute")) {
            interval = 60;
        }
        if (freq.equals("hour")) {
            interval = 3600;
        }
        if (freq.equals("day")) {
            interval = 86400;
        }

        int size = ((endTime - startTime) / interval) + 1;
        int[] cur = new int[size];
        TreeMap<Integer, Integer> temp = tweetsCache.get(tweetName);
        for (Map.Entry<Integer, Integer> entry : temp.subMap(startTime, endTime + 1).entrySet()) {
            int pos = (entry.getKey() - startTime) / interval;
            cur[pos] += entry.getValue();
        }
        List<Integer> res = new ArrayList<>();
        for (int num : cur) {
            res.add(num);
        }

        return res;
    }
}
