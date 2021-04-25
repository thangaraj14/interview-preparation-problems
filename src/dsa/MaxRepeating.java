package dsa;// Java program to find the maximum repeating number

/**
 * https://www.geeksforgeeks.org/find-the-maximum-repeating-number-in-ok-time/#:~:text=Find%20the%20maximum%20repeating%20number%20in%20this%20array.,allowed%20is%20O(1).
 */
class MaxRepeating {

    static int maxRepeating(int[] arr, int n, int k) {
        // Iterate though input array, for every element
        // arr[i], increment arr[arr[i]%k] by k
        for (int i = 0; i < n; i++)
            arr[(arr[i] % k)] += k;

        // Find index of the maximum repeating element
        int max = arr[0];
        int result = 0;
        for (int i = 1; i < n; i++) {
            if (arr[i] > max) {
                max = arr[i];
                result = i;
            }
        }

		/* Uncomment this code to get the original array back 
		for (int i = 0; i< n; i++) 
		arr[i] = arr[i]%k; */

        // Return index of the maximum element
        return result;
    }

    /*Driver function to check for above function*/
    public static void main(String[] args) {

        int[] arr = { 2, 3, 3, 5, 3, 4, 1, 7 };
        int n = arr.length;
        int k = 8;
        System.out.println("Maximum repeating element is: " + maxRepeating(arr, n, k));
    }
}
