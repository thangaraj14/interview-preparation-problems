package geeksforgeeks;
//https://leetcode.com/problems/queens-that-can-attack-the-king/submissions/
class QueensAttackKing {
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        List<List<Integer>> result= new ArrayList<>();
        boolean[][] visited= new boolean[8][8];
        int[][]dirs= {{1,0},{-1,0},{0,1},{0,-1},{-1,-1},{1,1},{1,-1},{-1,1}};
        
      for(int[] qu:queens){
          visited[qu[0]][qu[1]]=true;
      }
        
        for(int[] dir: dirs){
            List<Integer> temp= findQueensPosistions(king,dir[0],dir[1],visited);
            if(temp!=null) result.add(temp);
        }
        
        return result;
        
    }
    
    public List<Integer> findQueensPosistions(int[] king, int x, int y, boolean[][] visited){
        int newX= x+king[0];
        int newY= y+king[1];
        
        while(newX<8 && newY<8 && newX>=0 && newY>=0){
            if(visited[newX][newY]) return Arrays.asList(newX,newY);
            newX+=x;
            newY+=y;
            
        }
        
        return null;
    }
}