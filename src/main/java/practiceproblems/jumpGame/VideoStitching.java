package practiceproblems.jumpGame;

/**
 * https://leetcode.com/problems/video-stitching/
 *
 * Observation 1: We will always pick a clip with the maximum coverage if they have same starting points.
 *
 * eg: [0,3],[0,7],[0,5] -> We pick [0,7] , there is no point of picking others as the problem states that we need to minimize the number of clips picked,
 * and this can only be done if we maximize the gap between start and end point of each clip.
 *
 * Observation 2: Once we start picking the clips from the minimum starting point,
 * we only increase the count if we find a starting point greater than previously selected clip's end,
 * from then on we keep maximizing the reachable end without increasing count.
 */
public class VideoStitching {
    public int videoStitching(int[][] clips, int time) {

        int[] clippings = new int[time + 1];

        for (int[] clip : clips) {
            if (clip[0] > time) continue;

            clippings[clip[0]] = Math.max(clippings[clip[0]], clip[1]);
        }

        int currJumpIndex = 0;
        int maxJumIndex = 0;
        int steps = 0;

        for (int i = 0; i <= time; i++) {

            if (i > maxJumIndex) break;

            maxJumIndex = Math.max(maxJumIndex, clippings[i]);

            if (i >= currJumpIndex) {

                steps++;

                currJumpIndex = maxJumIndex;
                if (maxJumIndex >= time) return steps;
            }
        }

        return maxJumIndex >= time ? steps : -1;
    }
}
