package binarysearch;

/* A Bitonic Sequence is a sequence of numbers which is first strictly increasing then after a point strictly decreasing.*/

interface MountainArray {
    public int get(int index);

    public int length();
}

public class BitonicSearch {

    /**
     * <a href="https://leetcode.com/problems/valid-mountain-array/">...</a>
     *
     * @param arr
     * @return
     */
    public boolean validMountainArray(int[] arr) {
        int left = 0;
        int right = arr.length;
        int peak = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            System.out.println("mid: " + mid);
            if (mid > 0 && arr[mid] > arr[mid - 1] && mid < arr.length - 1 && arr[mid + 1] < arr[mid]) {
                peak = mid;
                break;
            }

            if (mid < arr.length - 1 && arr[mid] < arr[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (peak == -1)
            return false;
        for (int i = 0; i < peak; i++) {
            if (arr[i] >= arr[i + 1])
                return false;
        }
        // peak to n - 1
        for (int i = peak; i < arr.length - 1; i++) {
            if (arr[i] <= arr[i + 1])
                return false;
        }
        return true;
    }

    //https://leetcode.com/problems/find-peak-element
    public int findPeakElement(int[] nums) {
        //if(nums.length)
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if ((mid == 0 || nums[mid - 1] < nums[mid]) && (mid == nums.length - 1 || nums[mid + 1] < nums[mid])) {
                return mid;
            } else if (mid == 0 || nums[mid - 1] < nums[mid] && nums[mid + 1] > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    //https://leetcode.com/problems/peak-index-in-a-mountain-array/description/
    public int peakIndexInMountainArray(int[] arr) {

        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > arr[mid - 1] && (arr[mid] > arr[mid + 1] || mid == arr.length - 1)) {
                return mid;
            }
            if (arr[mid] > arr[mid - 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return -1;
    }

    //https://leetcode.com/problems/find-in-mountain-array/
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int left = 0;
        int n = mountainArr.length();
        int right = n - 1;
        int peak = 0;
        int mid = 0;
        while (left < right) {
            mid = (left + right) / 2;
            if (mountainArr.get(mid) < mountainArr.get(mid + 1)) {
                left = mid + 1;

            } else {
                right = mid;
            }
        }
        peak = left;
        left = 0;
        right = peak;

        while (left <= right) {
            mid = (left + right) / 2;
            if (mountainArr.get(mid) < target) {
                left = mid + 1;
            } else if (mountainArr.get(mid) > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }

        left = peak;
        right = n - 1;

        while (left <= right) {
            mid = (left + right) / 2;
            if (mountainArr.get(mid) > target) {
                left = mid + 1;
            } else if (mountainArr.get(mid) < target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;

    }

    //https://leetcode.com/problems/longest-mountain-in-array
    // O(N) solution is fine for this constraint
    public int longestMountain(int[] A) {
        if (A == null || A.length < 3)
            return 0;
        int ans = 0;
        for (int i = 1; i < A.length - 1; i++) {
            if (A[i] > A[i - 1] && A[i] > A[i + 1]) {  // i is a peak
                int left = i - 1;    // find leftmost of the peak
                while (left > 0 && A[left - 1] < A[left])
                    left--;

                int right = i + 1;  // find rightmost of the peak
                while (right < A.length - 1 && A[right + 1] < A[right])
                    right++;

                ans = Math.max(ans, right - left + 1);  // get the width using left and rightmost

            }
        }
        return ans;
    }

    //https://leetcode.com/problems/find-peak-element-ii/
    // https://youtu.be/nGGp5XBzC4g
    public int[] findPeakGrid(int[][] mat) {

        int left = 0;
        int right = mat[0].length;
        int m = mat.length;
        //In this scenario, we'll employ binary search within the columns of the matrix.
        // Since the peak element is guaranteed to exist in one of the columns,
        // our search range will span from 0 to M, where M represents the total number of columns.
        int n = mat[0].length;
        while (left <= right) {
            int mid = (left + right) / 2;
            //Find the largest element in the ‘mid’ column: We will use the function findMaxIndex()
            // to find the largest element of the mid-th column and the function will return the row number
            int maxRow = getMaxElementRow(mat, mid);

            int prev = mid - 1 >= 0 ? mat[maxRow][mid - 1] : -1;
            int next = mid + 1 < n ? mat[maxRow][mid + 1] : -1;

            //If matrix[maxRowIndex][mid] > matrix[maxRowIndex][mid-1] && matrix[maxRowIndex][mid] > matrix[maxRowIndex][mid+1]:
            // This means matrix[maxRowIndex][mid] is the peak element. So, we will return its location
            if (mat[maxRow][mid] > prev && mat[maxRow][mid] > next) {
                return new int[]{maxRow, mid};
            }
            //For example, if matrix[i][j-1] is greater than the chosen largest element matrix[i][j],
            // we can conclude that matrix[i][j-1] is also greater than all the elements of the j-th column.
            // This is because matrix[i][j] is the largest element of j-th column.
            // Thus matrix[i][j-1] is now more likely to be the peak element.
            // The logic is also applied to matrix[i][j+1]. This is how the elimination is working.
            if (mat[maxRow][mid] < next) {

                left = mid + 1;
            } else {
                //If matrix[maxRowIndex][mid] < matrix[maxRowIndex][mid-1]: As we are in the right half of the peak element,
                // we have to eliminate this right half (i.e. high = mid-1). Because our peak element appears somewhere on the left side.
                right = mid - 1;
            }
        }

        return new int[]{-1, -1};

    }

    public int getMaxElementRow(int[][] mat, int col) {

        int rowNum = -1;
        int maxElem = Integer.MIN_VALUE;
        for (int i = 0; i < mat.length; i++) {
            //Although every element of the selected column might be a peak element,
            // the largest number has always the highest possibility of being one.
            // That is why, to reduce the checking operation, we are selecting the largest number.
            if (mat[i][col] > maxElem) {
                maxElem = mat[i][col];
                rowNum = i;
            }
        }

        return rowNum;
    }
}