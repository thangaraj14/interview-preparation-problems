package practiceproblems;

/**
 * https://www.geeksforgeeks.org/count-minimum-steps-get-given-desired-array/
 */

// unsolved
class CountMinimumStepsToFormDesiredInputArray {
    static int arr[] = new int[]{16, 16, 16};

    // Returns count of minimum operations to covert a
    // zero array to arr array with increment and
    // doubling operations.
    // This function computes count by doing reverse
    // steps, i.e., convert arr to zero array.
    static int countMinOperations(int n) {
        // Initialize result (Count of minimum moves)
        int result = 0;

        // Keep looping while all elements of arr
        // don't become 0.
        while (true) {
            // To store count of zeroes in current
            // arr array
            int zero_count = 0;

            int i; // To find first odd element
            for (i = 0; i < n; i++) {
                // If odd number found
                if (arr[i] % 2 == 1) {
                    break;
                }

                // If 0, then increment zero_count
                else if (arr[i] == 0) {
                    zero_count++;
                }
            }

            // All numbers are 0
            if (zero_count == n) {
                return result;
            }

            // All numbers are even
            if (i == n) {
                // Divide the whole array by 2
                // and increment result
                for (int j = 0; j < n; j++)
                    arr[j] = arr[j] / 2;
                result++;
            }

            // Make all odd numbers even by subtracting
            // one and increment result.
            for (int j = i; j < n; j++) {
                if (arr[j] % 2 == 1) {
                    arr[j]--;
                    result++;
                }
            }
        }
    }

    public static void main(String[] args) {

        System.out.println(
                "Minimum number of steps required to \n" + "get the given target array is " + countMinOperations(
                        arr.length));

    }
} 
