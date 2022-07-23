package practiceproblems;

/**
 * https://leetcode.com/problems/container-with-most-water/
 * tricky
 */
public class ContainerWithMostWater {

    public int maxArea(int[] height) {
        if (height == null || height.length == 0) return 0;
        int i = 0;
        int j = height.length - 1;
        int result = 0;

        while (i < j) {

            int waterVol = (j - i) * Math.min(height[i], height[j]); // width * height
            result = Math.max(waterVol, result);

            if (height[i] > height[j]) { // the reason we are moving lesser side is
                // j-i is going to be decreasing, so we need to
                // maintain higher side to have max value
                j--;
            } else {
                i++;
            }
        }
        return result;
    }
}
