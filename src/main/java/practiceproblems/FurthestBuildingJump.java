package practiceproblems;

import java.util.PriorityQueue;

/**
 * tricky priority queue
 */
public class FurthestBuildingJump {

    public int furthestBuilding(int[] heights, int bricks, int ladders) {


        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        for (int reach = 0; reach < heights.length - 1; reach++) {

            if (heights[reach] >= heights[reach + 1]) continue;

            int diff = heights[reach + 1] - heights[reach];

            if (diff <= bricks) {
                bricks -= diff;
                queue.offer(diff);
            } else if (ladders > 0) {

                /**
                 * If, however, bricks has become negative, then we'll need to make bricks positive again by reclaiming some bricks;
                 * we do this by removing the largest brick allocation from the heap and subtracting 1 from ladders to cover the removed brick allocation.
                 * This works because one of two cases is true; either there's a previous climb with more bricks to reclaim,
                 * or we've just added the largest climb onto the max-heap. So when we remove the maximum from the max-heap,
                 * we'll definitely get at least as many bricks as we just subtracted to make bricks non-negative again.
                 */
                if (!queue.isEmpty()) {
                    bricks += queue.poll();
                    if (bricks >= diff) {
                        bricks -= diff;
                        queue.offer(diff);
                    }
                }
                ladders--;
            } else {
                return reach;
            }
        }
        return heights.length - 1;
    }
}
