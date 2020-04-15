package geeksforgeeks;

/**
 * https://codepumpkin.com/find-unique-array-element/#XORApproach
 */
public class UniqueElementsInArray {

    /**
     * @param inputArray
     *
     * @return returns unique Element in the array.
     * -1 if no unique element is available in the array.
     */
    // 0 0 : 0 || 1 1 : 0
    // returns 0 if both are same
    public static int xorApproach(int[] inputArray) {
        int result = 0;
        for (int i = 0; i < inputArray.length; i++) {
            result = result ^ inputArray[i];
        }

        return (result > 0 ? result : -1);
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 2, 1, 4, 3 };
        xorApproach(nums);
    }
}
