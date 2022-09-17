package practiceproblems.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoSum {

    // all numbers
    private List<Integer> list = new ArrayList<>();

    // every number's occurrence
    private Map<Integer, Integer> map = new HashMap<>();

    // record max and minimum value
    int max, min;

    /**
     * Initialize your data structure here.
     */
    public TwoSum() {
    }

    /**
     * Add the number to an internal data structure..
     */
    public void add(int number) {
        list.add(number);

        min = Math.min(number, min);
        max = Math.max(number, max);

        map.put(number, map.getOrDefault(number, 0) + 1);
    }

    /**
     * Find if there exists any pair of numbers which sum is equal to the value.
     */
    public boolean find(int value) {
        if (value < 2 * min || value > 2 * max) {
            return false;
        }

        for (int num : list) {
            int num2 = value - num;

            if ((num == num2 && map.getOrDefault(num, 0) > 1) ||
                    (num != num2 && map.containsKey(num2))) {
                return true;
            }
        }

        return false;
    }
}
