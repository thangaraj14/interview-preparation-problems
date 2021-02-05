package geeksforgeeks;

/**
 * https://www.geeksforgeeks.org/longest-subarray-sum-elements-atmost-k/
 */
// array is non-negative
class LongestSubArraySumUtmostK {

    public static int utMostSum(int arr[], int n, int target) {
        int sum = 0;
        int count = 0;
        int maxCount = 0;

        for (int i = 0; i < n; i++) {
            if ((sum + arr[i]) <= target) {
                sum += arr[i];
                count++;
            } else if (sum != 0) {
                sum = sum - arr[i - count] + arr[i]; // shrinking the window
            }
            maxCount = Math.max(count, maxCount);
        }
        return maxCount;
    }

    public static void main(String[] args) {
        int arr[] = { 1, 2, 1, 0, 1, 1, 0 };
        int n = arr.length;
        int k = 4;

        System.out.print(utMostSum(arr, n, k));
    }
}
