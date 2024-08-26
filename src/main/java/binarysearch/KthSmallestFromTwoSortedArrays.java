package binarysearch;

/**
 * this is same as median for 2 sorted arrays, with little bit modification
 *
 */
class KthSmallestFromTwoSortedArrays {


    public double findKthSmallestFromSortedArrays(int[] input1, int[] input2, int k) {
        //if input1 length is greater than switch them so that input1 is smaller than input2.
        // the reason is to do binary search on smaller array to reduce time complexity
        if (input1.length > input2.length) {
            return findKthSmallestFromSortedArrays(input2, input1,k);
        }
        int x = input1.length;
        int y = input2.length;
        int n = x + y;

        int low = 0;
        int high = x;
        while (low <= high) {
            int mid1 = (low + high) / 2;
            int mid2 = k - mid1;

            int left1 = (mid1 == 0) ? Integer.MIN_VALUE : input1[mid1 - 1];
            int right1 = (mid1 == x) ? Integer.MAX_VALUE : input1[mid1];
            int left2 = (mid2 == 0) ? Integer.MIN_VALUE : input2[mid2 - 1];
            int right2 = (mid2 == y) ? Integer.MAX_VALUE : input2[mid2];

            if (left1 <= right2 && left2 <= right1) {
                    return Math.max(left1, left2);
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
}
