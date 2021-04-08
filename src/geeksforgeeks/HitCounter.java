package geeksforgeeks;

import java.util.ArrayDeque;

public class HitCounter {
    ArrayDeque trac;
    /** Initialize your data structure here. */
    private final int FIVE_MINUTES = 300;
    public HitCounter() {
        trac = new ArrayDeque<Integer>();
    }

    /**
     * Record a hit.
        @param timestamp - The current timestamp (in seconds granularity).
     */
    public void hit(int timestamp) {
        trac.addLast(timestamp);
    }

    /**
     * Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity).
     */
    public int getHits(int timestamp) {
        while(trac.size() > 0 && ( int) trac.getFirst() + FIVE_MINUTES <= timestamp) {
            trac.removeFirst();
        } return trac.size();
    }

}