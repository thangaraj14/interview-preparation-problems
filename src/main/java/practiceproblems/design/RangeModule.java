package practiceproblems.design;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * tricky tree set
 * https://leetcode.com/problems/range-module/
 */
public class RangeModule {
    TreeSet<int[]> ts;

    public RangeModule() {
        this.ts = new TreeSet<>((a, b) -> a[1] - b[1]);
    }

    public void addRange(int left, int right) {
        Iterator<int[]> iter = ts.tailSet(new int[]{0, left}, true).iterator();
        while (iter.hasNext()) {
            int[] temp = iter.next();
            if (temp[0] > right) break;

            left = Math.min(left, temp[0]);
            right = Math.max(right, temp[1]);
            iter.remove();
        }

        ts.add(new int[]{left, right});
    }

    public boolean queryRange(int left, int right) {
        int[] ceiling = ts.ceiling(new int[]{0, right});
        return ceiling != null && ceiling[0] <= left;
    }

    public void removeRange(int left, int right) {
        Iterator<int[]> iter = ts.tailSet(new int[]{0, left}, false).iterator();

        int[] front = null;
        int[] back = null;

        while (iter.hasNext()) {
            int[] temp = iter.next();
            if (temp[0] >= right) break;

            if (temp[0] < left) front = new int[]{temp[0], left};
            if (right < temp[1]) back = new int[]{right, temp[1]};
            iter.remove();
        }

        if (front != null) ts.add(front);
        if (back != null) ts.add(back);
    }

    public static void main(String[] args) {
        RangeModule rm = new RangeModule();
        rm.addRange(10, 20);
        rm.addRange(11, 25);
        rm.removeRange(9, 16);
        rm.queryRange(10, 14);
        rm.queryRange(13, 15);
        rm.queryRange(16, 17);

    }
}
