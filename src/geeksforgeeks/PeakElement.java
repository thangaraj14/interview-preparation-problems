package geeksforgeeks;

/**
 * https://leetcode.com/problems/find-peak-element/
 */
public class PeakElement {

    public int findPeakElementOld(int[] nums) {

        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int mid = (low + high) / 2;
            if (nums[mid] < nums[mid + 1]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    int findPeakElement(int[] nums) {
        int n = nums.length;
        int low = 0, high = n - 1;
        while (low + 2 <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid - 1] < nums[mid] && nums[mid] > nums[mid + 1]) {
                return mid;
            }
            if (nums[mid - 1] > nums[mid]) {
                high = mid;
            } else {
                low = mid;
            }
        }
        return nums[low] > nums[high] ? low : high;
    }

    public static void main(String[] args) {
        PeakElement pe = new PeakElement();
        int[] arr = { 1, 2, 1, 3, 5, 6, 4 };
        System.out.println(arr[pe.findPeakElement(arr)]);
    }

}
