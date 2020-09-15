package geeksforgeeks;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array A of integers, return the number of (contiguous, non-empty) subarrays that have a sum divisible by K.
 * Input: A = [4,5,0,-2,-3,1], K = 5
   Output: 7
Explanation: There are 7 subarrays with a sum divisible by K = 5:
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 */
public class SubArraySumDivisibleByK{

    public int subarraysDivByK(int[] A, int K) {
        Map<Integer, Integer> count = new HashMap<>();
        count.put(0, 1); //if the first prefix sum is 0, it need to be counted as a mod of K, right? Rest of prefix sum from 1 to K-1 don't include the first prefix sum
        int prefix = 0, res = 0;
        for (int a : A) {
            //(prefix+a%K+K)%K is just a trick to make the remainder positive.
            prefix = (prefix + a % K + K) % K;
            res += count.getOrDefault(prefix, 0);
            count.put(prefix, count.getOrDefault(prefix, 0) + 1);
        }
        return res; 
    }
    // A = [4,5,0,-2,-3,1], K = 5
    // step 1 : {0:1}          a=4    sum=4  mod=4  count = 0+0 =0
    // step 2 : {0:1,4:1}      a=5    sum=9  mod=4  count = 0+1 =1
    // step 3 : {0:1,4:2}      a=0    sum=9  mod=4  count = 1+2 =3
    // step 4 : {0:1,4:3}      a=-2   sum=7  mod=2  count = 3+0 =3  
    // step 6 : {0:1,4:3,2:1}  a=-3   sum=4  mod=4  count = 3+3 =6
    // step 7 : {0:1,4:4,2:1}  a=1    sum=5  mod=0  count = 6+1 =7
    public int subarraysDivByKOptimised(int[] A, int K) {
        int[] map = new int[K];
       map[0]=1;
        int prefix = 0, res = 0;
        for (int a : A) {
            prefix = (prefix + a % K + K) % K;
            res +=map[prefix];
            map[prefix]++;
        }
        return res; 
    }
}
