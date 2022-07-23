package practiceproblems.design;

import java.util.ArrayDeque;
import java.util.Deque;

public class HitCounter {
    Deque<Integer> track;
    /**
     * Initialize your data structure here.
     */
    private final int FIVE_MINUTES = 300;

    public HitCounter() {
        track = new ArrayDeque<>();
    }

    /**
     * Record a hit.
     *
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public void hit(int timestamp) {
        track.addLast(timestamp);
    }

    /**
     * Return the number of hits in the past 5 minutes.
     *
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public int getHits(int timestamp) {
        while (!track.isEmpty() && track.peekFirst() + FIVE_MINUTES <= timestamp) {
            track.removeFirst();
        }
        return track.size();
    }

}