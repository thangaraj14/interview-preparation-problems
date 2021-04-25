package dsa;

/**
 * https://leetcode.com/problems/trapping-rain-water/
 */
class WaterTrapping {

    static int findWater(int arr[], int n) {
        int result = 0;
        int leftMax = 0;
        int rightMax = 0;
        int left = 0;
        int right = n - 1;

        while (left < right) {
            if (arr[left] < arr[right]) {
                if (arr[left] > leftMax) {
                    leftMax = arr[left];
                } else {
                    result += leftMax - arr[left];
                }
                left++;
            } else {
                if (arr[right] > rightMax) {
                    rightMax = arr[right];
                } else {
                    result += rightMax - arr[right];
                }
                right--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        //1, 0, 0, 0
        int arr[] = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
        int n = arr.length;
        System.out.println("Maximum water that " + "can be accumulated is " + findWater(arr, n));
    }
}