package dynamicProgramming;

class MaximumSquareDP {
    public int maximalSquare(char[][] matrix) {
        if(matrix==null || matrix.length==0) return 0;

        int[][]dp= new int[matrix.length+1][matrix[0].length+1];
        int result=0;

        
        for(int i=1;i<=matrix.length;i++){
            for(int j=1; j<=matrix[0].length;j++){
            if(matrix[i-1][j-1]=='1') {
                dp[i][j]=1+Math.min(dp[i-1][j], Math.min(dp[i][j-1],dp[i-1][j-1]));
                result=Math.max(result, dp[i][j]);
            }
            }
        }
        
        return result*result;
    }
}

