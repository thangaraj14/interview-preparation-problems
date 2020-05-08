package geeksforgeeks;

/**
 * https://www.geeksforgeeks.org/find-number-of-islands/
 */
class Islands {

    private int n;
    private int m;

    public int numIslands(int[][] grid) {
        int count = 0;
        n = grid.length;
        if (n == 0) {
            return 0;
        }
        m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                if (grid[i][j] == 1) {
                    DFSMarking(grid, i, j);
                    ++count;
                }
        }
        return count;
    }

    private void DFSMarking(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= n || j >= m || grid[i][j] != 1) {
            return;
        }
        grid[i][j] = 0;
        DFSMarking(grid, i + 1, j);
        DFSMarking(grid, i - 1, j);
        DFSMarking(grid, i, j + 1);
        DFSMarking(grid, i, j - 1);
    }

    public static void main(String[] args) {
        int M[][] = new int[][] { { 1, 1, 1, 1, 0 }, { 1, 1, 0, 1, 0 }, { 1, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0 } };
        Islands I = new Islands();
        System.out.println("Number of islands is: " + I.numIslands(M));
    }
}
