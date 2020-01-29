package geeksforgeeks;
class Solution {
    public void solve(char[][] board) {
        if(board==null || board.length==0) return;
        
        Queue<Pair> queue= new ArrayDeque<>();
        int[][] dirs= { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]=='O'){
                    if(i==board.length-1 || j== board[0].length-1 || i==0 || j==0){ 
                        board[i][j]='1';
                         queue.offer(new Pair(i,j,0));
                    }
                }
            }
        }
        
        while(!queue.isEmpty()){
            Pair temp= queue.poll();
            
            for(int[] dir: dirs){
                int newx=temp.x+dir[0];
                int newy= temp.y+dir[1];
                
                if(isValid(newx,newy,board) && board[newx][newy]=='O'){
                    board[newx][newy]='1';
                    queue.offer(new Pair(newx, newy,0));
                }
            }
           
        }
        
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]=='1'){
                    board[i][j]= 'O';
                }else if( board[i][j]== 'O'){
                    board[i][j]='X';
                }
            }
        }
        
    }
    
    public boolean isValid(int x, int y, char[][] board){
        if(x<0 || x>=board.length || y<0 || y>=board[0].length) return false;
        return true;
    }
}

class Pair {
    int x;
    int y;
    int level;
    
    public Pair(int x, int y, int level){
        this.x=x;
        this.y=y;
        this.level=level;
    }
}