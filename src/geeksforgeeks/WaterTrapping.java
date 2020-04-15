package geeksforgeeks;

/**
 * https://leetcode.com/problems/trapping-rain-water/
 */
class WaterTrapping {

    static int findWater(int arr[], int n) {
        int result = 0;
        int leftMax = 0;
        int rightMax = 0;
        int low = 0;
        int high = n - 1;

        while (low < high) {
            if (arr[low] < arr[high]) {
                if (arr[low] > leftMax) {
                    leftMax = arr[low];
                } else {
                    result += leftMax - arr[low];
                }
                low++;
            } else {
                if (arr[high] > rightMax) {
                    rightMax = arr[high];
                } else {
                    result += rightMax - arr[high];
                }
                high--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int arr[] = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1 };
        int n = arr.length;
        System.out.println("Maximum water that " + "can be accumulated is " + findWater(arr, n));
    }
}