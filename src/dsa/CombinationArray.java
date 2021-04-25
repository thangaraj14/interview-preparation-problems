package dsa;

import java.util.*;

/**
 * https://www.geeksforgeeks.org/combinations-from-n-arrays-picking-one-element-from-each-array/
 */
class CombinationArray {

    static void print(List<Integer>[] arr) {

        int n = arr.length;

        int[] indices = new int[n];

        while (true) {

            // Print current combination
            for (int i = 0; i < n; i++)
                System.out.print(arr[i].get(indices[i]) + " ");

            System.out.println();

            // Find the rightmost array that has more
            // elements left after the current element
            // in that array
            int next = n - 1;
            while (next >= 0 && (indices[next] + 1 >= arr[next].size()))
                next--;

            // No such array is found so no more
            // combinations left
            if (next < 0) {
                return;
            }

            // If found move to next element in that
            // array
            indices[next]++;

            // For all arrays to the right of this
            // array current index again points to
            // first element
            for (int i = next + 1; i < n; i++)
                indices[i] = 0;
        }
    }

    public static void main(String[] args) {

        // Initializing a vector with 3 empty vectors
        List<Integer>[] arr = new ArrayList[3];
        for (int i = 0; i < arr.length; i++)
            arr[i] = new ArrayList<>();

        // Now entering data
        // [[1, 2, 3], [4], [5, 6]]
        arr[0].add(1);
        arr[0].add(2);
        arr[0].add(3);

        arr[1].add(4);

        arr[2].add(5);
        arr[2].add(6);

        print(arr);
    }
}

