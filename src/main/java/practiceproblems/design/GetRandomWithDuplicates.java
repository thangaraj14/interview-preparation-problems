package practiceproblems.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/
 *
 * tricky
 */
public class GetRandomWithDuplicates {

    ArrayList<Integer> randomList;
    HashMap<Integer, Set<Integer>> cache;
    java.util.Random rand = new java.util.Random();

    /**
     * Initialize your data structure here.
     */

    public GetRandomWithDuplicates() {
        randomList = new ArrayList<>();
        cache = new HashMap<>();
    }

    /**
     * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
     */
    public boolean insert(int val) {
        cache.putIfAbsent(val,new LinkedHashSet<>());
        cache.get(val).add(randomList.size());
        randomList.add(val);
        return cache.get(val).size() == 1;
    }

    /**
     *This is the tricky part. We find the index of the element using the HashMap.
     *  We use the trick discussed in the intuition to remove the element from the list in O(1)O(1)O(1).
     *  Since the last element in the list gets moved around, we have to update its value in the HashMap.
     * We also have to get rid of the index of the element we removed from the HashMap.
     */
    public boolean remove(int val) {
        if (!cache.containsKey(val) || cache.get(val).isEmpty()) return false;
        int removeIndex = cache.get(val).iterator().next();

        // remove from linkedHashSet
        cache.get(val).remove(removeIndex);

        int lastVal = randomList.get(randomList.size() - 1);

        randomList.set(removeIndex, lastVal);

        cache.get(lastVal).add(removeIndex);

        cache.get(lastVal).remove(randomList.size() - 1);

        randomList.remove(randomList.size() - 1);

        return true;
    }

    /**
     * Get a random element from the collection.
     */
    public int getRandom() {
        return randomList.get(rand.nextInt(randomList.size()));
    }
}
