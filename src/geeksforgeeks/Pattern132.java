package geeksforgeeks;

import java.util.Arrays;

/*https://leetcode.com/problems/132-pattern/discuss/94089/Java-solutions-from-O(n3)-to-O(n)-for-%22132%22-pattern-(updated-with-one-pass-slution)*/
public class Pattern132 {

    public static boolean find132pattern(int[] arr) {

        int[] temp = Arrays.copyOf(arr, arr.length);

        for (int i = 1; i < arr.length; i++) {
            temp[i] = Math.min(arr[i - 1], temp[i - 1]);
        }
        // {3, 3, 3, 1, 1, 1, 1, 1}

        for (int j = arr.length - 1, top = arr.length; j >= 0; j--) {
            if (arr[j] <= temp[j])
                continue;
            while (top < arr.length && temp[top] <= temp[j])
                top++;
            if (top < arr.length && arr[j] > temp[top])
                return true;
            temp[--top] = arr[j];
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = { 3, 4, 1, 2, 9, 6, 7, 8 };
        find132pattern(arr);
    }
}
