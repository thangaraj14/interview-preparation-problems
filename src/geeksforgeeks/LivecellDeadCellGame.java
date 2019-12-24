package geeksforgeeks;
public class LivecellDeadCellGame {
    //https://leetcode.com/problems/game-of-life/discuss/73366/Clean-O(1)-space-O(mn)-time-Java-Solution
    public void gameOfLife(int[][] board) {
        int[][] dir ={{1,-1},{1,0},{1,1},{0,-1},{0,1},{-1,-1},{-1,0},{-1,1}};
        int row=board.length;
        int col=board[0].length;
        for(int i=0; i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                int liveCells=0;
                for(int k=0;k<dir.length;k++){
                    if(i+dir[k][0]>=row || j+dir[k][1]>=col || i+dir[k][0]<0 ||  j+dir[k][1]<0) continue;
                    if(board[i+dir[k][0]][j+dir[k][1]]==1 || board[i+dir[k][0]][j+dir[k][1]]==2) liveCells++;
                }
                
                if(board[i][j]==0 && liveCells==3) board[i][j]=3;
                if(board[i][j]==1 && (liveCells<2 || liveCells>3)) board[i][j]=2;
            }
        }
        
         for(int i=0; i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
               board[i][j]%=2; 
            }
         }
        
    }
}