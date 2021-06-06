package dsa;

import java.util.ArrayDeque;

/**
 * Design a hit counter which counts the number of hits received in the past 5 minutes.
 * <p>
 * Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.
 * <p>
 * It is possible that several hits arrive roughly at the same time.
 * <p>
 * Example:
 * <p>
 * HitCounter counter = new HitCounter();
 * <p>
 * // hit at timestamp 1.
 * counter.hit(1);
 * <p>
 * // hit at timestamp 2.
 * counter.hit(2);
 * <p>
 * // hit at timestamp 3.
 * counter.hit(3);
 * <p>
 * // get hits at timestamp 4, should return 3.
 * counter.getHits(4);
 * <p>
 * // hit at timestamp 300.
 * counter.hit(300);
 * <p>
 * // get hits at timestamp 300, should return 4.
 * counter.getHits(300);
 * <p>
 * // get hits at timestamp 301, should return 3.
 * counter.getHits(301);
 * <p>
 * Follow up:
 * What if the number of hits per second could be very large? Does your design scale?
 * <p>
 * https://leetcode.com/discuss/interview-question/178662/Design-a-Hit-Counter/
 * https://leetcode.com/problems/design-hit-counter/
 */
public class HitCounter {

    ArrayDeque<Integer> trac;
    static final int FIVE_MINUTES = 300;

    public HitCounter() {
        trac = new ArrayDeque<>();
    }

    public void hit(int timestamp) {
        trac.addLast(timestamp);
    }

    public int getHits(int timestamp) {
        while (!trac.isEmpty() && trac.getFirst() + FIVE_MINUTES <= timestamp) {
            trac.removeFirst();
        }
        return trac.size();
    }

    public static void main(String[] args) {
        HitCounter hitCounter = new HitCounter();
        hitCounter.hit(1);
        hitCounter.hit(2);
        hitCounter.hit(3);
        System.out.println(hitCounter.getHits(4));
        hitCounter.hit(301);
        System.out.println(hitCounter.getHits(301));
    }
}