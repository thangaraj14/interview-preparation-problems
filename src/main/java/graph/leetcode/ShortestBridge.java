package graph.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/shortest-bridge/
 */
public class ShortestBridge {

    // find one island location, add it to queue
    // then from queue get the shortest distance to another island
    public int shortestBridge(int[][] grid) {

        int m = grid.length;
        int n=grid[0].length;
        boolean[][] visited = new boolean[m][n];
        Deque<int[]> queue = new ArrayDeque<>();
        boolean isFound = false;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){

                if(!isFound && grid[i][j]==1){
                    dfs(grid, i,j,queue,visited);
                    isFound=true;
                    break;
                }

            }
            if (isFound) {
                break;
            }
        }
        int steps=0;
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while(!queue.isEmpty()){

            int size = queue.size();

            for(int i=0;i<size;i++){
                int[] temp = queue.poll();



                for(int[] dir:dirs){
                    int newX = temp[0]+dir[0];
                    int newY = temp[1]+dir[1];

                    if(newX<0 || newY<0 || newX>=m || newY>=n || visited[newX][newY])  continue;

                    if(grid[newX][newY] == 1) return steps;
                    visited[newX][newY]=true;
                    queue.offer(new int[]{newX,newY});
                }
            }
            steps++;
        }

        return -1;

    }


    public void dfs(int[][] grid, int i, int j, Deque<int[]> queue, boolean[][] visited){

        if(i<0 || j<0 || i>=grid.length || j>=grid[0].length || grid[i][j]==0 || visited[i][j]) return;

        queue.offer(new int[]{i,j});
        visited[i][j]=true;
        dfs(grid, i,j+1,queue,visited);
        dfs(grid, i+1,j,queue,visited);
        dfs(grid, i-1,j,queue,visited);
        dfs(grid, i,j-1,queue,visited);
    }
}
