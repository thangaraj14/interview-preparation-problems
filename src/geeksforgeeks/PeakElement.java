package geeksforgeeks;

/**
 * @author i312458
 */
public class PeakElement {
    public int findPeakElement(int[] nums) {

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

    public static void main(String[] args) {
        PeakElement pe = new PeakElement();
        int[] arr = { 1, 1, 1, 3, 2, 1, 2 };
        System.out.println(arr[pe.findPeakElement(arr)]);
    }

}
