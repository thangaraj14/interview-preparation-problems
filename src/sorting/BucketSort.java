package sorting;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class BucketSort {

    public static void main(String[] args) {
        int[] arr = { 37, 74, 12, 45, 67, 99, 65, 29, 32, 9, 15, 4 };
        System.out.println("Original array- " + Arrays.toString(arr));
        bucketSort(arr, 10);
        System.out.println("Sorted array after bucket sort- " + Arrays.toString(arr));
    }

    private static void bucketSort(int[] arr, int bucketSize) {
        // Create bucket array for storing lists
        List<Integer>[] buckets = new List[bucketSize];
        // Linked list with each bucket array index
        // as there may be hash collision
        for (int i = 0; i < bucketSize; i++) {
            buckets[i] = new LinkedList<>();
        }
        // calculate hash and assign elements to proper bucket
        for (int num : arr) {
            buckets[hash(num, bucketSize)].add(num);
        }
        // sort buckets
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
        }

        int index = 0;
        // Merge buckets to get sorted array
        for (List<Integer> bucket : buckets) {
            for (int num : bucket) {
                arr[index++] = num;
            }
        }
    }

    // hash function used for element distribution
    private static int hash(int num, int bucketSize) {
        return num / bucketSize;
    }
}

