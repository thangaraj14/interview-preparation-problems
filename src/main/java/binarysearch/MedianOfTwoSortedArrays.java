package binarysearch;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://www.youtube.com/watch?v=F9c7LpRZWVQ
 * TODO revise
 * tricky binary search
 */
public class MedianOfTwoSortedArrays {

    public static void main(String[] args) {
        int[] x = {1, 3, 8, 9, 15, 17, 30};
        int[] y = {7, 11, 18, 19, 21, 25};
        // 1,3,7,8,9,11,15,18,19,21,25
        MedianOfTwoSortedArrays mm = new MedianOfTwoSortedArrays();
        System.out.println(mm.findMedianSortedArraysEff(x, y) + " - " + mm.findMedianSortedArrays(x, y));
    }

    /**
     * Each half contains (n/2) elements.
     * Each half also contains x elements from the first array i.e. arr1[] and (n/2)-x elements from the second array i.e. arr2[].
     * The value of x might be different for the two halves.
     * For example, in the above array, the left half contains 3 elements from arr1[] and 2 elements from arr2[].
     *
     * @param input1
     * @param input2
     * @return
     */
    public double findMedianSortedArraysBruteForce(int[] input1, int[] input2) {
        int[] merged = new int[input1.length + input2.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < input1.length && j < input2.length) {
            if (input1[i] < input2[j]) {
                merged[k++] = input1[i++];
            } else {
                merged[k++] = input2[j++];
            }
        }
        while (i < input1.length) {
            merged[k++] = input1[i++];
        }
        while (j < input2.length) {
            merged[k++] = input2[j++];
        }

        if (merged.length % 2 == 0) {
            return (merged[merged.length / 2] + merged[merged.length / 2 - 1]) / 2.0;
        } else {
            return merged[merged.length / 2];
        }
    }

    public double findMedianSortedArrays(int[] input1, int[] input2) {
        //if input1 length is greater than switch them so that input1 is smaller than input2.
        // the reason is to do binary search on smaller array to reduce time complexity
        if (input1.length > input2.length) {
            return findMedianSortedArrays(input2, input1);
        }
        int x = input1.length;
        int y = input2.length;
        int n = x + y;
        int leftHalf = (n + 1) / 2; //  the reason is this works for both odd and even
        // if the n is 10, 10+1/2 = median is 5, if n is 9, 9+1/2 = median 5

        // the whole idea is to partition the 2 arrays so that the left side and right side have same number of elements
        // let's take example A= 1,3,7 and B= 2,6,8,9,10

        // first take (low + high) / 2 for A it'd be 1 in this example. we partition at 1, [1 ||, 3,7] (1 element on left and 2 on right)
        // for B we need to do this,  {(x + y + 1) / 2 - partitionX} = (3+5+1/2)-1=3 [2,6,8||,9,10] (3 elements on left and 2 on right)
        // add the total left and right elements for both arrays would come to be equal 3+1(left partition) and 2+2(right partition)

        int low = 0;
        int high = x;
        while (low <= high) {
            int mid1 = (low + high) / 2;
            int mid2 = leftHalf - mid1;

            int left1 = (mid1 == 0) ? Integer.MIN_VALUE : input1[mid1 - 1];
            int right1 = (mid1 == x) ? Integer.MAX_VALUE : input1[mid1];
            int left2 = (mid2 == 0) ? Integer.MIN_VALUE : input2[mid2 - 1];
            int right2 = (mid2 == y) ? Integer.MAX_VALUE : input2[mid2];

            if (left1 <= right2 && left2 <= right1) {
                if (n % 2 == 0) {
                    return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
                } else {
                    return Math.max(left1, left2);
                }
            } else if (left1 > right2) {
                //If l1 > r2: This implies that we have considered more elements from arr1[] than necessary.
                // So, we have to take less elements from arr1[] and more from arr2[].
                // In such a scenario, we should try smaller values of x.
                // To achieve this, we will eliminate the right half (high = mid-1).
                high = mid1 - 1;
            }
            //This implies that we have considered more elements from arr2[] than necessary.
            // So, we have to take less elements from arr2[] and more from arr1[].
            // In such a scenario, we should try bigger values of x.
            // To achieve this, we will eliminate the left half (low = mid+1).
            else low = mid1 + 1;

        }
        return -1.0;
    }

    public double findMedianSortedArraysEff(int[] nums1, int[] nums2) {

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());


        for (int value : nums1) {
            maxHeap.add(value);
        }

        for (int i : nums2) {
            maxHeap.add(i);
        }

        int val = 0;
        //division refers to integer division, this modified formula (n1+n2+1) / 2 will be valid for both cases of odd and even.
        val = (nums1.length + nums2.length + 1) / 2;
        for (int k = 0; k < val; k++) {
            minHeap.add(maxHeap.poll());
        }

        if (minHeap.size() == maxHeap.size()) {
            return (double) (minHeap.peek() + maxHeap.peek()) / 2;
        } else {
            return minHeap.peek();
        }
    }
}
