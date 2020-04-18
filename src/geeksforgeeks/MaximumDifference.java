package geeksforgeeks;

/**
 * https://www.geeksforgeeks.org/maximum-difference-between-two-elements/
 */
class MaximumDifference {

    static int maxDiff(int arr[], int n) {

        int diff = arr[1] - arr[0];
        int previousSum = diff;
        int maxSum = diff;

        for (int i = 1; i < n - 1; i++) {

            diff = arr[i + 1] - arr[i];

            if (previousSum > 0)
                previousSum += diff;
            else
                previousSum = diff;

            if (previousSum > maxSum)
                maxSum = previousSum;
        }
        return maxSum;
    }
    static int maxDiff1(int arr[], int arr_size) {
        int maxDiff = 0;
        int minElement = arr[0];
        for (int i = 1; i < arr_size; i++) {
            if (arr[i] - minElement > maxDiff) {
                maxDiff = arr[i] - minElement;
            }
            if (arr[i] < minElement) {
                minElement = arr[i];
            }
        }
        return maxDiff;
    }
    public static void main(String[] args) {
        int arr[] = {2, 4, 1, 3, 10, 8, 5};
        int n = arr.length;

        System.out.print("Maximum difference is " + maxDiff(arr, n));
    }
}