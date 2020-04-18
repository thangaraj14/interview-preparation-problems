package geeksforgeeks;

class MinPathSum {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;
        int dp[][] = new int[grid.length][grid[0].length];
        return getMinPathSum(0, 0, grid, dp);
    }

    public int getMinPathSum(int i, int j, int[][] grid, int[][] dp) {
        if (i == grid.length - 1 && j == grid[0].length - 1)
            return grid[i][j];
        else if (i > grid.length - 1 || j > grid[0].length - 1)
            return Integer.MAX_VALUE;
        else {
            if (dp[i][j] != 0)
                return dp[i][j];
            dp[i][j] = grid[i][j] + Math.min(getMinPathSum(i + 1, j, grid, dp), getMinPathSum(i, j + 1, grid, dp));
        }
        return dp[i][j];
    }

    public int minPathSum1(int[][] grid) {
      
        for(int i=1;i<grid.length;i++){
            grid[i][0]+=grid[i-1][0];
        }
        
        for(int j=1;j<grid[0].length;j++){
            grid[0][j]+= grid[0][j-1];
        }
        for(int i=1;i<grid.length;i++){
            for(int j=1; j<grid[0].length;j++){
                grid[i][j]=Math.min(grid[i][j]+grid[i-1][j],grid[i][j]+grid[i][j-1]);
            }
        }
       return grid[grid.length-1][grid[0].length-1];
    }
}