package geeksforgeeks;

/**
 * https://www.geeksforgeeks.org/k-th-element-two-sorted-arrays/
 */
class KthSmallestFromTwoSortedArrays {

    public static int findKth(int[] A, int i, int[] B, int j, int k) {

        if ((A.length - i) > (B.length - j)) {
            return findKth(B, j, A, i, k);
        }

        if (i >= A.length) {
            return B[j + k - 1];
        }
        if (k == 1) {
            return Math.min(A[i], B[j]);
        }

        int aMid = Math.min(k / 2, A.length - i);
        int bMid = k - aMid;

        if (A[i + aMid - 1] <= B[j + bMid - 1]) {
            return findKth(A, i + aMid, B, j, k - aMid);
        }

        return findKth(A, i, B, j + bMid, k - bMid);
    }

    public static void main(String[] args) {
        int arr1[] = { 1, 6, 8, 9, 15 };
        int arr2[] = { 3, 5, 10, 14, 20 };

        int k = 6;
        int ans = findKth(arr1, 0, arr2, 0, k);
        if (ans == -1) {
            System.out.println("Invalid query");
        } else {
            System.out.println(ans);
        }
    }
}
