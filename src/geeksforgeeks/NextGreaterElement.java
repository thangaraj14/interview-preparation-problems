package geeksforgeeks;

import java.util.Stack;

/**
 * https://www.geeksforgeeks.org/next-greater-element/
 * <p>
 * https://www.geeksforgeeks.org/find-next-greater-number-set-digits/
 */
class NextGreaterElement {

    static int arr[] = { 4, 5, 2, 25 };

    // 9,1,2,3,4,5,6,7
    public static void printNGE() {
        Stack<Integer> s = new Stack<>();
        int[] nge = new int[arr.length];

        for (int i = arr.length - 1; i >= 0; i--) {

            while (!s.empty() && s.peek() <= arr[i]) {
                s.pop();
            }
            nge[i] = s.empty() ? -1 : s.peek();
            s.push(arr[i]);

        }
        for (int i = 0; i < arr.length; i++)
            System.out.println(arr[i] + " --> " + nge[i]);

    }

//     Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
//     Output: [-1,3,-1]
// Explanation:
//     For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
//     For number 1 in the first array, the next greater number for it in the second array is 3.
//     For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
    public int[] nextGreaterElement(int[] findNums, int[] nums) {
        int[] ret = new int[findNums.length];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = nums.length - 1; i >= 0; i--) {
            while(!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();
            }
            if(stack.isEmpty()) map.put(nums[i], -1);
            else map.put(nums[i], stack.peek());
            stack.push(nums[i]);
        }
        for(int i = 0; i < findNums.length; i++) {
            ret[i] = map.get(findNums[i]);
        }
        return ret;
    }

//     Input: [1,2,1]
// Output: [2,-1,2]
// Explanation: The first 1's next greater number is 2; 
// The number 2 can't find next greater number; 
// The second 1's next greater number needs to search circularly, which is also 2.
    public static int[] nextGreaterElementCircular(int[] arr){
        if(nums==null || nums.length==0) return new int[0];
        int[] result= new int[nums.length];
        int n= nums.length;
        Arrays.fill(result, -1);
        Deque<Integer> deque= new ArrayDeque<>();
        // to mimic the circular array we iterate for 2*n because input [1,2,1] will be like [1,2,1,1,2,1]
        // and we take mod of 'n' to update the correct index
        for(int i=0;i<2*nums.length;i++){
            
            while(!deque.isEmpty() && nums[deque.getLast()]<nums[i%n]){
                result[deque.removeLast()]=nums[i%n];
            }
            deque.addLast(i%n);
           
        }
        
        return result;
    }

    public static void main(String[] args) {
        printNGE();
    }
}
