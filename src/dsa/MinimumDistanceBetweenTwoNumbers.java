package dsa;

/**
 * https://www.geeksforgeeks.org/find-the-minimum-distance-between-two-numbers/
 */

class MinimumDistanceBetweenTwoNumbers {

    int minDist(int arr[], int n, int x, int y) {
        Integer xIndex = null;
        Integer yIndex = null;
        int minDist = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            if (arr[i] == x) {
                xIndex = i;
            }
            if (arr[i] == y) {
                yIndex = i;
            }

            if (xIndex != null && yIndex != null) {
                minDist = Math.min(minDist, Math.abs(xIndex - yIndex));
            }
        }
        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }

    public static void main(String[] args) {
        MinimumDistanceBetweenTwoNumbers min = new MinimumDistanceBetweenTwoNumbers();
        int arr[] = { 3, 5, 4, 2, 6, 5, 6, 6, 5, 4, 8, 3 };
        int n = arr.length;
        int x = 3;
        int y = 6;

        System.out.println("Minimum distance between " + x + " and " + y + " is " + min.minDist(arr, n, x, y));
    }
} 
