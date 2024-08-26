package binarysearch;

// https://takeuforward.org/data-structure/aggressive-cows-detailed-solution/

import java.util.Arrays;

/**
 * Example 1:
 * Input Format:
 *  N = 6, k = 4, arr[] = {0,3,4,7,10,9}
 * Result:
 *  3
 * Explanation:
 *  The maximum possible minimum distance between any two cows will be 3 when 4 cows are placed at positions {0, 3, 7, 10}.
 *  Here the distances between cows are 3, 4, and 3 respectively.
 *  We cannot make the minimum distance greater than 3 in any ways.
 */
public class AggressiveCows {

    public int aggressiveCows(int[] stalls, int cows){
        //To arrange the cows in a consecutive manner while ensuring a certain distance between them,
        // the initial step is to sort the stalls based on their positions.
        // In a sorted array, the minimum distance will always be obtained from any two consecutive cows
        Arrays.sort(stalls);
        //The minimum possible distance between two cows is 1 as the minimum distance between 2 consecutive stalls is 1.
        //The maximum possible distance between two cows is = max(stalls[])-min(stalls[]). This case occurs when we place 2 cows at two ends of the sorted stalls array.
        int right = stalls[stalls.length-1] - stalls[0];
        int left=1;

        while(left<right){
            int mid= left+ (right-left)/2;
            if(isPossible(stalls,mid,cows)){
                left=mid+1;
            }else{
                right=mid;
            }
        }
        //When the binary search ends, right will be the largest distance that satisfies the condition, and left will be one more than this value.
        return right;
    }

    public int aggressiveCowsBruteForce(int[] stalls, int cows){
        Arrays.sort(stalls);
        int limit = stalls[stalls.length-1] - stalls[0];

        for(int i=1; i<limit; i++){
            if(!isPossible(stalls, i, cows)){
                return i-1;
            }
        }

        return -1;
    }

    private boolean isPossible(int[] stalls, int distance, int cows) {
        int count = 1;
        int lastPosition = stalls[0];
        for(int j=1; j<stalls.length; j++){
            if(stalls[j] - lastPosition >= distance){
                count++;
                lastPosition = stalls[j];
            }
        }

        return count >= cows;
    }
}
