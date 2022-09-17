package practiceproblems.design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.IntStream;

/**
 * https://leetcode.com/problems/snapshot-array/
 */
class SnapShotUtil {

    int[] arr;
    List<int[]> snapShot;
    int maxIndex = 0;

    /**
     * initializes an array-like data structure with the given length.  Initially, each element equals 0.
     */
    public SnapShotUtil(int length) {
        arr = new int[length];
        snapShot = new ArrayList<>();
    }

    /**
     * sets the element at the given index to be equal to val.
     */
    public void set(int index, int val) {
        arr[index] = val;
        maxIndex = Math.max(index, maxIndex);
    }

    /**
     * takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
     */
    public int snap() {
        snapShot.add(Arrays.copyOfRange(arr, 0, maxIndex + 1));
        return snapShot.size() - 1;
    }

    /**
     * returns the value at the given index, at the time we took the snapshot with the given snap_id
     */
    public int get(int index, int snap_id) {
        int[] arr = snapShot.get(snap_id);
        if (index >= arr.length) return 0;
        return arr[index];
    }


    private int count;
    private List<TreeMap<Integer, Integer>> shot = new ArrayList<>();

    public SnapShotUtil(int length, int k) {
        IntStream.range(0, length).forEach(i -> shot.add(new TreeMap<>()));
    }

    public void setOptimised(int index, int val) {
        shot.get(index).put(count, val);
    }

    public int snapOptimised() {
        return count++;
    }

    public int getOptimised(int index, int snap_id) {
        Integer key = shot.get(index).floorKey(snap_id);
        return key == null ? 0 : shot.get(index).get(key);
    }
}

