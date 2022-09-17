package practiceproblems;

/**
 * https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60
 */
public class PairDivisibleBy60 {
    public int numPairsDivisibleBy60(int[] time) {
        //brute force - O(n ^ 2) time - TLE
        // int n = time.length;
        // int result = 0;
        // for(int i = 0; i < n; i++){
        //     for(int j = i + 1; j < n; j++){
        //         if((time[i] + time[j]) % 60 == 0)
        //             result++;
        //     }
        // }
        // return result;

        //2 SUM PROBLEM WITH K = 60
        //O(n) time
        /**
         *  the solution is (time[i]+time[j]%60 == 0)
         *  if we replace time[j] = 20
         *  then we can have time[i] as either 40 , 100 or 160
         *  the 40, 100 and 160 if we mod with 60 the results would be 40
         *
         *  let's take this arr =                         [60, 30, 20, 150, 120, 100, 30]
         *  if we mod with 60 then the arr would look like [0, 30, 20, 30, 0, 40, 30]
         */
        int[] c = new int[60]; //because we know its going upto 0 to 59
        int result = 0;
        for (int t : time) {
            // System.out.println(t % 60)
            if (t % 60 == 0)  //completely divisible on its own
                result += c[0];
            else
                result += c[60 - t % 60]; //to keep it in range
            c[t % 60] += 1;
        }
        return result;
    }
}
