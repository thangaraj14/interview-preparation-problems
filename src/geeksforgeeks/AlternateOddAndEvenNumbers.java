package geeksforgeeks;
// A JAVA program to put positive numbers at even indexes
// (0, 2, 4,..) and negative numbers at odd indexes (1, 3, 
// 5, ..) 

import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/rearrange-positive-and-negative-numbers-publish/
 * An array contains both positive and negative numbers in random order.
 * Rearrange the array elements so that positive and negative numbers are placed alternatively.
 * If there are more positive numbers they appear at the end of the array. 
 * If there are more negative numbers, they too appear in the end of the array.
 *  [-1, 2, -3, 4, 5, 6, -7, 8, 9]
 *  output [9, -7, 8, -3, 5, -1, 2, 4, 6] or[4, -3, 5, -1, 6, -7, 2, 8, 9]
 */
class AlternateOddAndEvenNumbers {

    static void rearrange(int arr[], int n) {
        //-1, 2, -3, 4, 5, 6, -7, 8, 9
        int i = 0, temp = 0;
        for (int j = 0; j < n; j++) {
            if (arr[j] < 0) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            }
        }
       // we have seggregated positive and negative elements
        System.out.println(Arrays.toString(arr));
        int pos = i , neg = 0;
      // pos indicates start of positive integer, neg starts from 0;

        while (pos < n && neg < pos && arr[neg] < 0) {
            temp = arr[neg];
            arr[neg] = arr[pos];
            arr[pos] = temp;
            pos++;
            neg += 2; // need to skip next element as output should be alternative
        }
    }

    static void printArray(int arr[], int n) {
        for (int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");
    }

    public static void main(String[] args) {
        int arr[] = { -1, 2, -3, 4, 5, 6, -7, 8, 9 };
        int n = arr.length;
        rearrange(arr, n);
        System.out.println("Array after rearranging: ");
        printArray(arr, n);
    }
}
