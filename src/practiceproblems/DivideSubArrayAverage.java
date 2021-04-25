package practiceproblems;

/**
 * https://www.geeksforgeeks.org/divide-array-two-sub-arrays-averages-equal/
 *
 * Given an integer array, the task is to divide an integer array into
 * two sub-arrays to make their averages equal if possible.
 *
 * Input : arr[] = {1, 5, 7, 2, 0};
 * Output : (0  1) and (2  4)
 * Subarrays arr[0..1] and arr[2..4] have
 * same average.
 *
 * Input : arr[] = {4, 3, 5, 9, 11};
 * Output : Not possible
 */
class DivideSubArrayAverage {

    static void findSubarrays(int arr[], int n) {
        int sum = 0;
        for (int i = 0; i < n; i++)
            sum += arr[i];

        boolean found = false;
        int lsum = 0;
        for (int i = 0; i < n - 1; i++) {
            lsum += arr[i];
            int rsum = sum - lsum;

            if (lsum * (n - i - 1) == rsum * (i + 1)) {
                System.out.println("found");
                found = true;
            }
        }

        // If no subarrays found
        if (!found) {
            System.out.println("Not found");
        }
    }

    public static void main(String[] args) {
        int[] arr = { 1, 5, 7, 2, 0 };
        int n = arr.length;
        findSubarrays(arr, n);
    }
}
