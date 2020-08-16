package geeksforgeeks;

// The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. 
//The dungeon consists of M x N rooms laid out in a 2D grid.
//Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.

// The knight has an initial health point represented by a positive integer.
//If at any point his health point drops to 0 or below, he dies immediately.
//Input
// |-2   -3  3 |
// |-5	 -10  1 |
// |10	 30	 -5 |

//output 7

// the trick here is to  go bottom up, start from the last cell, 
// inorder to reach there he should have atleast 6 as health, so that 
// when he reaches -5(energy is consumed) and he's left with +1 health
// likewise if we backtrack from end to start, we'll need +7 as min initial health to
// play the game
public class DungeonGame {
    public int calculateMinimumHP(int[][] dungeon) {
        
        int[][] dp = new int[dungeon.length][dungeon[0].length];
        
        int m = dungeon.length;
        int n = dungeon[0].length;
         
        dp[m-1][n-1] = Math.max(1, 1-dungeon[m-1][n-1]);
         
        // Populate the last column
        for(int i=m-2;i>=0;i--){
            dp[i][n-1] = Math.max(1, dp[i+1][n-1]-dungeon[i][n-1]);
        } 
        
        // Populate the last row
        for(int i=n-2;i>=0;i--){
            dp[m-1][i] = Math.max(1, dp[m-1][i+1]-dungeon[m-1][i]);
        }  

        // to achieve the answer, we need to setup the last row and last column
        // we know to reach last cell we need 6 as energy, let's say that comes 
        // from cell above it, that cell's original val is +1, so we must have
        // 5 energy when we reach there and adding it up, it became 6
        // the reason to put 1 on last row is, the value in that cell is 30
        // so to reach last cell from that cell, we need only 6 energy(min)
        // to have 6 from +30, player should have health of -24 and player cannot
        // have neg val, so we put 1 as filler
        // |*     *   2 |
        // |*	  *   5 |
        // |1	  1	  6 |
         
        // Populate the rest by taking max of bottom and right (reverse of down and left)

        for(int i=m-2;i>=0;i--){
            for(int j=n-2;j>=0;j--){
                dp[i][j] = Math.max(1, Math.min(dp[i+1][j], dp[i][j+1])-dungeon[i][j]);
            }    
        } 
         

        return dp[0][0]; 
     }
}