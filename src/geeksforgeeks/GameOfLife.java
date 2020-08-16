package geeksforgeeks;

/**
 * https://forum.letstalkalgorithms.com/t/game-of-life/516/2
 * <p>
 * https://leetcode.com/problems/game-of-life/
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
 *
 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population..
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 *
 * Input:
 * [
 *   [0,1,0],
 *   [0,0,1],
 *   [1,1,1],
 *   [0,0,0]
 * ]
 * Output:
 * [
 *   [0,0,0],
 *   [1,0,1],
 *   [0,1,1],
 *   [0,1,0]
 * ]
 */
public class GameOfLife {

    public void gameOfLife(int[][] board) {

    int[][] dir ={{1,-1},{1,0},{1,1},{0,-1},{0,1},{-1,-1},{-1,0},{-1,1}};
    int row=board.length;
    int col=board[0].length;

    for(int i=0; i<board.length;i++){
        for(int j=0;j<board[0].length;j++){
            int liveCells=0;
            for(int k=0;k<dir.length;k++){
                int i1=i+dir[k][0];
                int j1=j+dir[k][1];
                if(i1>=row || j1>=col || i1<0 ||  j1<0) continue; // border conditions

                if(board[i1][j1]==1 || board[i1][j1]==2) liveCells++;
            }

            if(board[i][j]==0 && liveCells==3) board[i][j]=3; //Any dead cell with exactly three live neighbors becomes a live cell

            if(board[i][j]==1 && (liveCells<2 || liveCells>3)) board[i][j]=2; //live cell with fewer than two live neighbors dies || Any live cell with more than three live neighbors dies
        }
    }

    for(int i=0; i<board.length;i++){
        for(int j=0;j<board[0].length;j++){
            board[i][j]%=2;
        }
    }

}
}