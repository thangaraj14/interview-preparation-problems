package dsa;

/**
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/binarysearch/MedianOfTwoSortedArrayOfDifferentLength.java
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 * <p>
 * https://www.youtube.com/watch?v=yD7wV8SyPrc&ab_channel=KeertiPurswani
 */

public class MedianOfTwoSortedArrays {

    public double findMedianSortedArraysTR(int input1[], int input2[]) {

        //if input1 length is greater than switch them so that input1 is smaller than input2.
        if (input1.length > input2.length) {
            return findMedianSortedArraysTR(input2, input1);
        }
        int x = input1.length;
        int y = input2.length;

        int low = 0;
        int high = x;
        while (low <= high) {
            int partitionX = (low + high) / 2;
            int partitionY = (x + y + 1) / 2 - partitionX;

            //if partitionX is 0 it means nothing is there on left side. Use -INF for maxLeftX
            //if partitionX is length of input then there is nothing on right side. Use +INF for minRightX
            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : input1[partitionX - 1];
            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : input2[partitionY - 1];

            int minRightX = (partitionX == x) ? Integer.MAX_VALUE : input1[partitionX];
            int minRightY = (partitionY == y) ? Integer.MAX_VALUE : input2[partitionY];
            System.out.println(maxLeftX + "" + maxLeftY + "" + minRightX + "" + minRightY);
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

        //Only we we can come here is if input arrays were not sorted. Throw in that scenario.
        throw new IllegalArgumentException();
    }

    double findMedianSortedArraysKP(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;

        if (n1 > n2) {
            return findMedianSortedArraysKP(nums2, nums1);    // Make sure arr1 is the shorter one.
        }

        int low = 0;
        int high = n1;
        while (low <= high) {
            int cut1 = (low + high) / 2;
            int cut2 = (n1 + n2 + 1) / 2 - cut1;

            int l1 = cut1 == 0 ? Integer.MIN_VALUE : nums1[cut1 - 1];
            int l2 = cut2 == 0 ? Integer.MIN_VALUE : nums2[cut2 - 1];

            int r1 = cut1 == n1 ? Integer.MAX_VALUE : nums1[cut1];
            int r2 = cut2 == n2 ? Integer.MAX_VALUE : nums2[cut2];
            System.out.println(l1 + "" + l2 + "" + r1 + "" + r2);
            if (l1 > r2) {
                high = cut1 - 1;
            } else if (l2 > r1) {
                low = cut1 + 1;
            } else {
                return (n1 + n2) % 2 == 0 ? ((double) Math.max(l1, l2) + Math.min(r1, r2)) / 2 : Math.max(l1, l2);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] x = { 1, 5, 8, 10, 18, 20 };
        int[] y = { 2, 3, 6, 7 };

        MedianOfTwoSortedArrays mm = new MedianOfTwoSortedArrays();
        System.out.println(mm.findMedianSortedArraysTR(x, y));
        System.out.println("----------------");
        System.out.println(mm.findMedianSortedArraysKP(x, y));
    }
}
