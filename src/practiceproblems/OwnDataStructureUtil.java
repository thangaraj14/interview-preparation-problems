package practiceproblems;

import java.util.*;

/**
 * https://leetcode.com/problems/insert-delete-getrandom-o1/
 */
class OwnDataStructureUtil {
    ArrayList<Integer> list;
    HashMap<Integer, Integer> map;
    Random rand = new Random();

    public OwnDataStructureUtil() {
        list = new ArrayList<>();
        map = new HashMap<>();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        map.put(val, list.size());
        list.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int loc = map.get(val);
        if (loc < list.size() - 1) { // not the last one than swap the last one with this val
            int lastOne = list.get(list.size() - 1);
            list.set(loc, lastOne);
            map.put(lastOne, loc);
        }
        map.remove(val);
        list.remove(list.size() - 1);
        return true;
    }

    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }

    public static void main(String[] args) {
        OwnDataStructureUtil ds = new OwnDataStructureUtil();
        ds.insert(10);
        ds.insert(20);
        ds.insert(30);
        ds.insert(40);
        ds.remove(20);
        ds.insert(50);
        System.out.println(ds.getRandom());
    }
}
