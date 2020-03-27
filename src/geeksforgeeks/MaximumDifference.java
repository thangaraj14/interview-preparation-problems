package geeksforgeeks;

/**
 * https://www.geeksforgeeks.org/maximum-difference-between-two-elements/
 */
class MaximumDifference {

    static int maxDiff(int arr[], int arr_size) {
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
        int arr[] = { 2, 4, 1, 3, 10, 8, 5 };
        System.out.println("Maximum difference is " + maxDiff(arr, arr.length));
    }
}