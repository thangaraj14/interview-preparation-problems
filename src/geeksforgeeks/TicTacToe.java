package geeksforgeeks;

public class TicTacToe {
    private int[] rows;
    private int[] cols;
    private int diagonal;
    private int antiDiagonal;

    /**
     * Initialize your data structure here.
     */
    public TicTacToe(int n) {
        rows = new int[n];
        cols = new int[n];
    }

    /**
     * if one player keeps adding the coin in one row then that row's count will be 3
     */
    public int move(int row, int col, int player) {
        int toAdd = player == 1 ? 1 : -1;

        rows[row] += toAdd;
        cols[col] += toAdd;
        if (row == col) {
            diagonal += toAdd;
        }

        if (col + row == cols.length - 1) {
            antiDiagonal += toAdd;
        }

        int size = rows.length;
        if (Math.abs(rows[row]) == size || Math.abs(cols[col]) == size || Math.abs(diagonal) == size
                || Math.abs(antiDiagonal) == size) {
            return player;
        }

        return 0;
    }

    public static void main(String[] args) {
        TicTacToe toe = new TicTacToe(3);
        toe.move(0, 0, 1);
        toe.move(0, 2, 2);
        toe.move(2, 2, 1);
        toe.move(1, 1, 2);
        toe.move(2, 0, 1);
        toe.move(1, 0, 2);
        toe.move(2, 1, 1);

    }

}