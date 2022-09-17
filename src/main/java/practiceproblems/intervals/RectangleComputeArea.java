package practiceproblems.intervals;

/**
 * https://leetcode.com/problems/rectangle-area/submissions/
 *
 * Calculate the area of each rectangle at first. Judge whether they have intersection.
 * If not, return the sum area of them.
 * Otherwise, count the intersection area and subtract it by one time of total area.
 */
public class RectangleComputeArea {

    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {


        int rectOneArea = (ax2 - ax1) * (ay2 - ay1);
        int rectTwoArea = (bx2 - bx1) * (by2 - by1);


        int[] overlapping = new int[4];

        overlapping[0] = Math.max(ax1, bx1);
        overlapping[1] = Math.max(ay1, by1);

        overlapping[2] = Math.min(ax2, bx2);
        overlapping[3] = Math.min(ay2, by2);

        int overlapLength = overlapping[2] - overlapping[0];
        int overlapWidth = overlapping[3] - overlapping[1];


        return rectOneArea + rectTwoArea - (overlapLength <= 0 || overlapWidth <= 0 ? 0 : overlapLength * overlapWidth);
    }
}
