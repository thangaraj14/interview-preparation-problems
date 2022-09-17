package practiceproblems.intervals;

/**
 * https://leetcode.com/problems/rectangle-overlap
 *
 * For overlapping questions:
 *
 * Interval A = [leftA, rightA]
 * Interval B = [leftB, rightB]
 * Overlapping region:  [max(leftA, leftB) , min(rightA, rightB)]
 *
 * which means if(max(leftA, leftB) < min(rightA, rightB)), there is an overlap.
 * So the code of this problem is to check whether x is overlapped && y is overlapped.
 *
 *
 *  ------------                -------------
 *  0           1               2           3
 *
 * start = Math.max(start1, start2) = 2
 * end =  Math.min(end1, end2) = 1
 *
 * does not overlap as start > end
 *
 *             -------------
 *             1           3
 *  ------------
 * 0           2
 *
 * start = Math.max(start1, start2) = 1
 * end =  Math.min(end1, end2) = 2
 *
 * overlap as start < end
 */
public class RectangleOverlap {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {

        int x1 = rec1[0];
        int y1 = rec1[1];

        int x2 = rec1[2];
        int y2 = rec1[3];

        int r2X1 = rec2[0];
        int r2Y1 = rec2[1];

        int r2X2 = rec2[2];
        int r2Y2 = rec2[3];

        boolean overlapX = false;
        boolean overlapY = false;

        if (Math.max(x1, r2X1) < Math.min(x2, r2X2)) {
            overlapX = true;
        }

        if (Math.max(y1, r2Y1) < Math.min(y2, r2Y2)) {
            overlapY = true;
        }

        return overlapX && overlapY;
    }
}
