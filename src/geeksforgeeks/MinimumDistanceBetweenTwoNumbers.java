package geeksforgeeks;


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
        // sort of like sliding window tracks the first occurence 
        // of the element and check if both are not same and calculates the distance
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
        int arr[] = {3, 5, 4, 2, 6, 5, 6, 6, 5, 4, 8, 3};
        int arr1[] = { 3, 5, 4, 3, 1, 2, 4, 6, 5, 6, 6, 5, 4, 8, 3 };
        int n = arr.length;
        int x = 3;
        int y = 6;

        System.out.println("Minimum distance between " + x + " and " + y + " is " + min.minDist(arr, n, x, y));
    }
} 
