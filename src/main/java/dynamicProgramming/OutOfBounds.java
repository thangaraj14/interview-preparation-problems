package dynamicProgramming;

/**
 * https://leetcode.com/problems/out-of-boundary-paths/
 */
public class OutOfBounds {
    int [][] dirs= new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
    int mod = 1000000000 + 7;
    Integer[][][] cache;
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        cache= new Integer[m+1][n+1][maxMove+1];
        return recursionHelper(m,n,maxMove,startRow,startColumn);
    }

    public int recursionHelper(int m, int n, int maxMove, int startRow, int startColumn){

        if(startRow<0 || startRow>=m || startColumn<0 || startColumn>=n) return 1;
        if(maxMove==0) return 0;

        if(cache[startRow][startColumn][maxMove]!=null) return cache[startRow][startColumn][maxMove];

        int result=0;

        for(int[] dir:dirs){
            result= result+recursionHelper(m,n,maxMove-1,startRow+dir[0],startColumn+dir[1]) %mod;
            result%=mod;
        }

        return cache[startRow][startColumn][maxMove]= result;

    }
}
