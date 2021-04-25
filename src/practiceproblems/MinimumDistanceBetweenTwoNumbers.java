package practiceproblems;


/*
https://www.geeksforgeeks.org/find-the-minimum-distance-between-two-numbers/

Given an unsorted array arr[] and two numbers x and y, find the minimum distance between x and y in arr[].
The array might also contain duplicates.
You may assume that both x and y are different and present in arr[].

Input: arr[] = {2, 5, 3, 5, 4, 4, 2, 3},
x = 3, y = 2
Output: Minimum distance between 3
and 2 is 1.
Explanation:3 is at index 7 and 2 is at
index 6, so the distance is 1
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
        int arr[] = {3, 5, 4, 2, 6, 5, 6, 6, 5, 4, 8, 3};
        int arr1[] = { 3, 5, 4, 3, 1, 2, 4, 6, 5, 6, 6, 5, 4, 8, 3 };
        int n = arr.length;
        int x = 3;
        int y = 6;

        System.out.println("Minimum distance between " + x + " and " + y + " is " + min.minDist(arr, n, x, y));
    }
} 
