package microsoftassesment;

import java.util.Arrays;

/**
 * Alexa is given n piles of equal or unequal heights. In one step, Alexa can remove any number of boxes from the pile which has the maximum height and try to make it equal to the one which is just lower than the maximum height of the stack.
 * Determine the minimum number of steps required to make all of the piles equal in height.
 *
 * Input: piles = [5, 2, 1]
 * Output: 3
 * Explanation:
 * Step 1: reducing 5 -> 2 [2, 2, 1]
 * Step 2: reducing 2 -> 1 [2, 1, 1]
 * Step 3: reducing 2 -> 1 [1, 1, 1]
 * So final number of steps required is 3.
 */
public class MinStepsToMakePileSameHeight {

    public int minSteps(int[]arr){
        int result=0;
        int distinctNumbers=0;
        Arrays.sort(arr);
        //1,2,5
        for(int i=1;i<arr.length;i++){
            if(arr[i]!=arr[i-1]) { // i=1=> 1 != 2, i=2 => 2!=5
                distinctNumbers++; // else increase the distance count
            }

            result+=distinctNumbers; // even if elements are same, we need to add it to result
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] tests= new int[][]{
        {1, 2, 2, 2, 3, 4, 5, 6, 4, 12, 4},
        {1, 2, 3},
        {1, 2, 5},
        {1, 2, 3, 4, 5}};

        for(int[] test: tests){
            System.out.println(new MinStepsToMakePileSameHeight().minSteps(test));
        }
    }


}
