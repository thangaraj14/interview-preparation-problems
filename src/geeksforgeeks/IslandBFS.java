package geeksforgeeks;

class IslandBFS {
    private class Pair{
        int x;
        int y;
        Pair(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
    public int numIslands(char[][] grid) {
        int result=0;
        //i-1,j  i+1,j  i,j-1  j,j+1
        int[][] directions= new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
        for(int i=0;i<grid.length;i++){
            for(int j=0; j<grid[0].length;j++){
                if(grid[i][j]=='1'){
                    bfsUtil(grid,i,j,directions); 
                    result++;
                }
               
               
            }
        }
        return result;
    }
    
    public void bfsUtil(char[][] grid, int i, int j,  int[][] directions){
       grid[i][j]='0';
        Pair root=  new Pair(i,j);
        
        Queue<Pair> queue= new ArrayDeque<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            Pair temp= queue.poll();
         for(int[] dir: directions){
              
               int x= temp.x+dir[0];
                int y= temp.y+dir[1];
             if(isvalid(grid,x,y)){
                 grid[x][y]='0';
                 queue.offer(new Pair(x,y));
             }
         }
      }
    }
    
    public boolean isvalid(char[][] grid,int x, int y){
        if(x<0 || x>=grid.length || y<0 || y>=grid[0].length || grid[x][y]=='0') return false;
        return true;
    }
}