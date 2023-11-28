package practiceproblems;



import graph.leetcode.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/max-points-on-a-line
 *
 *
 * input: [[2,1],[3,2],[4,3],[5,4]]
 * slope:    ^    1/1   1/1   1/1    Slope is dy/dx, e.g. (5-2)/(4-1)=3/3=1/1
 * |        o                        GCD is used to map equal slopes to identical.
 * |      o      result: number of identical slopes + starting point = 3+1=4
 * |    o
 * |  o
 * +--------------------------
 *
 * input: [[2,1],[3,2],[6,3],[5,4]]
 * slope:    ^    1/1   2/1   1/1    We keep track of slopes related to the
 * |        o                        current point [2,1] in map.
 * |          o  result: number of identical slopes + starting point = 2+1=3
 * |    o
 * |  o
 * +--------------------------
 *
 * input: [[2,2],[3,2],[5,2],[6,2]]
 * slope:    ^    1/0   1/0   1/0    GCD returns dx for pair [dx, 0].
 * |                                 Then dx/dx / 0/dx = 1/0
 * |             result: 3 + 1 = 4
 * |  o o   o o
 * |
 * +--------------------------
 *
 * input: [[3,1],[3,2],[3,3],[3,4]]
 * slope:    ^    0/1   0/1   0/1    GCD retuns dy for [o,dy].
 * |    o                            Then 0/dy / dy/dy = 0/1
 * |    o        result: 3 + 1 = 4
 * |    o
 * |    o
 * +--------------------------
 *
 * input: [[2,1],[3,2],[2,1],[4,1],[5,4],[2,1],[2,1]]
 * slope:    ^    1/1   dup   1/0   1/1   dup   dup
 * |        o                       Identical points have no slope.
 * |                                We have 2 possible lines: 2 * 1/1 and 1 * 1/0.
 * |    o                           Keep track of duplicates and add them to max line.
 * |  0   o      result: 3 dups + max(2,1) + 1 = 6
 * +--------------------------
 *
 * input: [[1,1],[5,1],[2,2],[3,3],[4,4],[6,2],[7,3]]
 * slope:    ^    1/0   1/1   1/1   1/1   5/1   3/1    Slopes: max(1,3,1,1) = 3
 * slope:          ^   -3/1   1/-1  1/-3  1/1   1/1    Slopes: max(1,1,1,2) = 2
 * |       o                        The map tracks slopes for the current point only.
 * |     o       o                  So parallel lines do not sum up points.
 * |   o       o
 * | o       o    result: 0 dups + max(3,2) + 1 = 4
 * +--------------------------
 *
 * */
public class MaxPointsInLine {

    public int maxPoints(int[][] points) {
        if (points.length == 1) return 1;
        int result = 2;
        Map<Pair<Integer, Integer>, Integer> cache = new HashMap<>();

        for (int i = 0; i < points.length; i++) {
            int duplicate = 0;
            int count = 0;
            cache.clear();
            for (int j = i + 1; j < points.length; j++) {

                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];

                if (dx == 0 && dy == 0) {
                    duplicate++;
                    continue;
                }
                /**
                 * we need the slope: dx/dy. normally we can check dx/dy=1
                 * but float rounds up the end and produces slightly different results,
                 * so instead we keep both dx and dy as the key.
                 * to make them identical for the identical slope, use GCD: greatest common divisor
                 */

                int gcd = gcd(dx, dy);
                if (gcd != 0) {
                    dx /= gcd;
                    dy /= gcd;
                }

                // dx and dy define the slope.
                // we keep the map for the current point i, so the full key is point[i]+slope excludes parallel lines.
                // vertical line: dx==0, horizontal line: dy==0.
                // GCD will set vertical: dx=0, dy=1, horizontal: dx=1, dy=0
                Pair<Integer, Integer> line = new Pair<>(dx, dy);

                cache.put(line, cache.getOrDefault(line, 0) + 1);
                count = Math.max(count, cache.get(line));
            }

            result = Math.max(result, count + duplicate + 1);

        }

        return result;
    }

    public int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
