package geeksforgeeks;

public class NQueens {

        public List<List<String>> solveNQueens(int n) {
            if(n==0) return Collections.emptyList();
    
            List<List<String>> result= new ArrayList<>();
            char[][] board= new char[n][n];
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    board[i][j]='.';
                }
            }
    
            nQueensHelper(0,n,board,result);
    
            return result;
        }
    
        public void nQueensHelper(int row, int n, char[][] board, List<List<String>> result) {
    
            if(row==board.length){
                result.add(construct(board));
                return;
            }
            for(int i=0;i<n;i++){
                // check if it can be placed in current i(col) of the row and explore possibilites
                if(isValidPlacement(row,i,board)){
                    board[row][i]='Q'; // if yes proceed to next row and explore all possibilites
                    nQueensHelper(row+1, n, board, result);
                    board[row][i]='.';
                }
    
            }
    
        }
    
        public boolean isValidPlacement(int row, int col, char[][] chess){
             // check all cols
            for (int i = 0; i < row; i++) {
                if (chess[i][col] == 'Q') {
                    return false;
                }
            }
            //check 45 degree
            for (int i = row - 1, j = col + 1; i >= 0 && j < chess.length; i--, j++) {
                if (chess[i][j] == 'Q') {
                    return false;
                }
            }
            //check 135
            for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
                if (chess[i][j] == 'Q') {
                    return false;
                }
            }
            return true;
        }
    
        private List<String> construct(char[][] chess) {
            List<String> path = new ArrayList<>();
            for (int i = 0; i < chess.length; i++) {
                path.add(new String(chess[i]));
            }
            return path;
        }
    
}