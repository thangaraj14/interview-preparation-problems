package practiceproblems.design;

import java.util.TreeMap;

public class MyCalendar {
    TreeMap<Integer, Integer> calendar;

    MyCalendar() {
        calendar = new TreeMap();
    }

    /**
     *  floor      ceiling
     * ... |----| ... |----| ...
     *        |---------|
     *       s         e
     * if s < floor.end or e > ceiling.start, there is an overlap.
     *
     * Another way to think of it:
     * If there is an interval start within the new book (must be the ceilingEntry) at all, or
     * books: |----|   |--|
     *             s |------| e
     *
     * books: |----|   |----|
     *             s |----| e
     * If the new book start within an interval (must be the floorEntry) at all
     * books: |-------|   |--|
     *        s |---| e
     *
     * books: |----|   |----|
     *         s |----| e
     * There is a overlap
     * @param start
     * @param end
     * @return
     */
    public boolean book(int start, int end) {
        Integer prev = calendar.floorKey(start),
                next = calendar.ceilingKey(start);
        if ((prev == null || calendar.get(prev) <= start) &&
                (next == null || end <= next)) {
            calendar.put(start, end);
            return true;
        }
        return false;
    }
}
