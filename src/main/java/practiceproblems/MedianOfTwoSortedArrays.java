package practiceproblems;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/binarysearch/MedianOfTwoSortedArrayOfDifferentLength.java
 * TODO revise
 * tricky binary search
 */
public class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] input1, int[] input2) {
        //if input1 length is greater than switch them so that input1 is smaller than input2.
        if (input1.length > input2.length) {
            return findMedianSortedArrays(input2, input1);
        }
        int x = input1.length;
        int y = input2.length;

        // the whole idea is to partition the 2 arrays so that the left side and right side have same number of elements
        // let's take example A= 1,3,7 and B= 2,6,8,9,10
        // so if we assume both are combined the median would be at 6(6+7/2= 6.5 to be exact)

        // first take (low + high) / 2 for A it'd be 1 in this example. we partition at 1, [1 ||, 3,7] (1 element on left and 2 on right)
        // for B we need to do this,  {(x + y + 1) / 2 - partitionX} = (3+5+1/2)-1=3 [2,6,8||,9,10] (3 elements on left and 2 on right)
        // add the total left and right elements for both arrays would come to be equal 3+1(left partition) and 2+2(right partition)

        // the reason to add 1 ((x + y + 1) / 2 ) is to account for both odd and even lengths
        // for odd length the left half should be greater, the +1 is to adjust for that

        int low = 0;
        int high = x;
        while (low <= high) {
            int partitionX = (low + high) / 2;
            int partitionY = (x + y + 1) / 2 - partitionX;

            //if partitionX is 0 it means nothing is there on left side to partition. [|| 1]  Use -INF for maxLeftX
            //if partitionX is length of input then there is nothing on right side. Use +INF for minRightX

            // to understand it further let's take an edge case A=[2] B=[1,3]
            // partitionX=0+1/2 => 0
            // partitionY= 1+2+1/2 - 0 => 2 , the partition would look like this
            // A= [-inf || 2]
            // B= [2,3 || +inf]
            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : input1[partitionX - 1];
            int minRightX = (partitionX == x) ? Integer.MAX_VALUE : input1[partitionX];

            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : input2[partitionY - 1];
            int minRightY = (partitionY == y) ? Integer.MAX_VALUE : input2[partitionY];

            // for the example given above, at right partition the elements would be grouped like below
            //      A=>    1,3 || 7
            //      B=>    2,6 || 8,9,10
            // 3<8 and 6<7 so we take max of left and min of right

            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                //We have partitioned array at correct place
                // Now get max of left elements and min of right elements to get the median in case of even length combined array size
                // or get max of left for odd length combined array size.
                if ((x + y) % 2 == 0) {
                    return ((double) Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2;
                } else {
                    return Math.max(maxLeftX, maxLeftY);
                }
            } else if (maxLeftX > minRightY) { //we are too far on right side for partitionX. Go on left side.
                high = partitionX - 1;
            } else { //we are too far on left side for partitionX. Go on right side.
                low = partitionX + 1;
            }
        }

        //Only  we can come here is if input arrays were not sorted. Throw in that scenario.
        throw new IllegalArgumentException();
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

        if ((nums1.length + nums2.length) % 2 == 0) {
            val = (nums1.length + nums2.length) / 2;
        } else {
            val = (nums1.length + nums2.length) / 2 + 1;
        }

        for (int k = 0; k < val; k++) {
            minHeap.add(maxHeap.poll());
        }

        if (minHeap.size() == maxHeap.size()) {
            return (double) (minHeap.peek() + maxHeap.peek()) / 2;
        } else {
            return minHeap.peek();
        }
    }

    public static void main(String[] args) {
        int[] x = {1, 3, 8, 9, 15, 17, 30};
        int[] y = {7, 11, 18, 19, 21, 25};
        // 1,3,7,8,9,11,15,18,19,21,25
        MedianOfTwoSortedArrays mm = new MedianOfTwoSortedArrays();
        System.out.println(mm.findMedianSortedArraysEff(x, y) + " - " + mm.findMedianSortedArrays(x, y));
    }
}
