package dynamicProgramming.matrix;

/**
 * http://www.geeksforgeeks.org/dynamic-programming-set-6-min-cost-path/
 */
public class MinCostPath {

	public int minPathSum1(int[][] grid) {

		for(int i=1;i<grid.length;i++){
			grid[i][0]+=grid[i-1][0];
		}

		for(int j=1;j<grid[0].length;j++){
			grid[0][j]+= grid[0][j-1];
		}
		for(int i=1;i<grid.length;i++){
			for(int j=1; j<grid[0].length;j++){
				grid[i][j]=Math.min(grid[i][j]+grid[i-1][j],grid[i][j]+grid[i][j-1]);
			}
		}
		return grid[grid.length-1][grid[0].length-1];
	}


	public int minPathSum(int[][] grid) {
		return recursionUtil(grid, grid.length-1, grid[0].length-1);
	}

	public int recursionUtil(int[][] grid, int row, int col){

		if(row == 0 && col == 0) return grid[row][col]; // this is the exit of the recursion

		/**
		 * if we put condition like if(row<0 || col<0) return 0, then when the control goes out of boundary for one condition like
		 * Math.min(recursionUtil(grid, -1, 1), recursionUtil(grid, 0, 0));
		 * we return 0 for recursionUtil(grid, -1, 1), and this is wrong as 0 will be taken as answer
 		 */

		if(row == 0) return grid[row][col] + recursionUtil(grid, row, col - 1); /** when we reach the first row, we could only move horizontally.*/

		if(col == 0) return grid[row][col] + recursionUtil(grid, row - 1, col); /** when we reach the first column, we could only move vertically.*/
		return grid[row][col] + Math.min(recursionUtil(grid, row - 1, col), recursionUtil(grid, row, col - 1)); /** we want the min sum path so we pick the cell with the less value */
	}

	public static void main(String args[]) {
		MinCostPath mcp = new MinCostPath();
		int[][] cost = { { 1, 2, 3 }, { 4, 8, 2 }, { 1, 5, 3 }, { 6, 2, 9 } };
		int result = mcp.minPathSum1(cost);
		System.out.println(result);
	}
}