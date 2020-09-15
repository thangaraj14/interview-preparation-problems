package dynamicProgramming;

/**
 * http://www.geeksforgeeks.org/dynamic-programming-set-11-egg-dropping-puzzle/
 * <p>
 * https://www.youtube.com/watch?v=3hcaVyX00_4&t=258s
 */
public class EggDropping {
  // calculate minimum tries to try to find the floor which breaks the egg
    public int calculate(int eggs, int floors) {

        int[][] T = new int[eggs + 1][floors + 1];
        int c = 0;
        // We need one trial for one floor
        for (int i = 0; i <= floors; i++) {
            T[1][i] = i;
        }

        for (int e = 2; e <= eggs; e++) {
            for (int f = 1; f <= floors; f++) {
                T[e][f] = Integer.MAX_VALUE;
                for (int k = 1; k <= f; k++) { // for loop to go from 0-f floor
                    // if egg breaks then egg-1 and floor -1 ==> T[e - 1][k - 1]
                    // else no change in egg count and remaining floors which is f-k ==> T[e][f - k]
                    // k means which floor we are in -- > first floor , second floor
                    // 2 Eggs -> 3 Floors
                    //	• 2 Eggs -> 1 Floor-> 1,0 ,, 2,2(remaining floors)
                    //	• 2 Eggs -> 2nd Floor -> 1,1 ,, 2,1 (remaining floors)
                    //	  2 Eggs -> 3rd Floor -> 1,2 ,, 2,0 (remaining floors)
                    System.out.println("e - " + e + " :: f - " + f + " :: k - " + k);
                    System.out.println("T[" + (e - 1) + "][" + (k - 1) + "],T[" + e + "][" + (f - k) + "]");
                    c = 1 + Math.max(T[e - 1][k - 1], T[e][f - k]);
                    if (c < T[e][f]) {
                        T[e][f] = c;
                    }
                }
            }
        }
        return T[eggs][floors];
    }

    public static int superEggDrop(int K, int N) {
        int[][] dp = new int[N + 1][K + 1];
        int m = 0;
        while (dp[m][K] < N) {
            ++m;
            for (int k = 1; k <= K; ++k)
                dp[m][k] = dp[m - 1][k - 1] + dp[m - 1][k] + 1;
        }
        return m;
    }

    public static void main(String args[]) {
        EggDropping ed = new EggDropping();
        // System.out.println(ed.calculate(2, 6));
        System.out.println(superEggDrop(2, 6));
    }

}