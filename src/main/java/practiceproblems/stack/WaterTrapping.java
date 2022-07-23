package practiceproblems.stack;

/**
 * pseudocode : GetTotalWater(H)
 * total <- 0
 * for every i in H
 * leftMax <- findMax(0,i);
 * rightMax <- findMax(i,n);
 * permissibleWaterStored <- min(leftMax,rightMax);
 * waterStored <- permissibleWaterStored-H[i];
 * total <- total+waterStored;
 * return total;
 */
class WaterTrapping {

    public static int trap(int[] height) {

        int[] maxLeft = new int[height.length];
        int[] maxRight = new int[height.length];
        int maxHeight = 0;
        int minHeight = 0;

        for (int i = 0; i < height.length; i++) {
            maxHeight = Math.max(maxHeight, height[i]);
            maxLeft[i] = maxHeight;

        }
        maxHeight = 0;
        for (int i = height.length - 1; i >= 0; i--) {
            maxHeight = Math.max(maxHeight, height[i]);
            maxRight[i] = maxHeight;
        }
        int result = 0;
        for (int i = 0; i < height.length; i++) {
            minHeight = Math.min(maxLeft[i], maxRight[i]);
            height[i] = Math.max(0, minHeight - height[i]);
            result += height[i];
        }

        return result;

    }

    /**
     * https://www.youtube.com/watch?v=EdR3V5DBgyo
     *
     * @param height
     * @return
     */
    public int trapAnother(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int left = 0;
        int right = height.length - 1; // Pointers to both ends of the array.
        int maxLeft = 0;
        int maxRight = 0;

        int totalWater = 0;
        while (left < right) {
            // Water could, potentially, fill everything from left to right, if there is nothing in between.
            if (height[left] <= height[right]) {
                // If the current elevation is greater than the previous maximum, water cannot occupy that point at all.
                // However, we do know that everything from maxLeft to the current index, has been optimally filled, as we've
                // been adding water to the brim of the last maxLeft.
                maxLeft = Math.max(maxLeft, height[left]);
                totalWater += maxLeft - height[left];

                // Increment left, we'll now look at the next point.
                left++;
                // If the height at the left is NOT greater than height at the right, we cannot fill from left to right without over-
                // flowing; however, we do know that we could potentially fill from right to left, if there is nothing in between.
            } else {
                // Similarly to above, we see that we've found a height greater than the max, and cannot fill it whatsoever, but
                // everything before is optimally filled
                maxRight = Math.max(maxRight, height[right]);
                totalWater += maxRight - height[right];

                // Decrement left, we'll look at the next point.
                right--;
            }
        }
        // Return the sum we've been adding to.
        return totalWater;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1};
        System.out.println("Maximum water that " + "can be accumulated is " + trap(arr));
    }
}