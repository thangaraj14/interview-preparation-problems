package geeksforgeeks;


/*https://www.geeksforgeeks.org/find-the-minimum-distance-between-two-numbers/*/

class MinimumDistanceBetweenTwoNumbers {

    int minDist(int arr[], int n, int x, int y) {

        int i = 0;
        int minDist = Integer.MAX_VALUE;
        int prev = 0;

        // Find the first occurrence of any of the two numbers (x or y)
        // and store the index of this occurrence in prev
        for (i = 0; i < n; i++) {
            if (arr[i] == x || arr[i] == y) {
                prev = i;
                break;
            }
        }

        for (; i < n; i++) {
            if (arr[i] == x || arr[i] == y) {
                // check if current element and prev element are different
                // Also check if this value is smaller than minimum distance
                // so far
                if (arr[prev] != arr[i] && (i - prev) < minDist) {
                    minDist = i - prev;
                }
                prev = i;
            }
        }
        return minDist;
    }

    public static void main(String[] args) {
        MinimumDistanceBetweenTwoNumbers min = new MinimumDistanceBetweenTwoNumbers();
<<<<<<< HEAD
        int arr[] = {3, 5, 4, 2, 6, 5, 6, 6, 5, 4, 8, 3};
=======
        int arr[] = { 3, 5, 4, 3, 1, 2, 4, 6, 5, 6, 6, 5, 4, 8, 3 };
>>>>>>> fa44d45e65bd24e807ebda00da7c1fd078295163
        int n = arr.length;
        int x = 3;
        int y = 6;

        System.out.println("Minimum distance between " + x + " and " + y + " is " + min.minDist(arr, n, x, y));
    }
} 
