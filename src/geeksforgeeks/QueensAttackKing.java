package geeksforgeeks;

import java.util.*;
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
        // going to walk along x,y only not 8 directions at smae time;
        while(newX<8 && newY<8 && newX>=0 && newY>=0){
            if(visited[newX][newY]) return Arrays.asList(newX,newY); // returns when first queen is met in
                                                                     // row or column
            newX+=x;
            newY+=y;
            
        }
        
        return null;
    }

    public static void main(String[] args) {
        int[][] queens= {{0,1},{1,0},{4,0},{0,4},{3,3},{2,4}};
        int[] king= {0,0};
        new QueensAttackKing().queensAttacktheKing(queens, king);
    }
}