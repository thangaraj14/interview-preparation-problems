package practiceproblems.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/design-log-storage-system/
 * tricky
 */
public class LogSystem {

    private final String MIN_TIMESTAMP = "2000:01:01:00:00:00";
    private final String MAX_TIMESTAMP = "2017:12:31:23:59:59";

    HashMap<String, Integer> indices;
    TreeMap<String, List<Integer>> logStorage;

    public LogSystem() {
        indices = new HashMap<>();
        indices.put("Year", 4);
        indices.put("Month", 7);
        indices.put("Day", 10);
        indices.put("Hour", 13);
        indices.put("Minute", 16);
        indices.put("Second", 19);

        logStorage = new TreeMap<>();
    }

    /**
     * Stores the given log (id, timestamp) in your storage system.
     */
    public void put(int id, String timestamp) {
        logStorage.putIfAbsent(timestamp, new ArrayList<>());
        logStorage.get(timestamp).add(id);
    }

    /**
     * Returns the IDs of the logs whose timestamps are within the range from start to end inclusive.
     * start and end all have the same format as timestamp, and granularity means how precise the range should be (i.e. to the exact Day, Minute, etc.).
     * For example, start = "2017:01:01:23:59:59", end = "2017:01:02:23:59:59", and granularity = "Day"
     * means that we need to find the logs within the inclusive range from Jan. 1st 2017 to Jan. 2nd 2017,
     * and the Hour, Minute, and Second for each log entry can be ignored.
     */
    public List<Integer> retrieve(String start, String end, String granularity) {

        int index = indices.get(granularity);

        String startTime = start.substring(0, index) + MIN_TIMESTAMP.substring(index);
        String endTime = end.substring(0, index) + MAX_TIMESTAMP.substring(index);

        List<Integer> result = new ArrayList<>();

        for (Map.Entry<String, List<Integer>> entry : logStorage.subMap(startTime, true, endTime, true).entrySet()) {
            result.addAll(entry.getValue());
        }
        return result;
    }
}
