package geeksforgeeks;

public class DesignTicTacToe {
    private int[] rows;
    private int[] cols;
    private int diagonal;
    private int antiDiagonal;

    /** Initialize your data structure here. */
    public DesignTicTacToe(int n) {
        rows = new int[n];
        cols = new int[n];
    }

    /** Player {player} makes a move at ({row}, {col}).
    @param row The row of the board.
    @param col The column of the board.
    @param player The player, can be either 1 or 2.
    @return The current winning condition, can be either:
            0: No one wins.
            1: Player 1 wins.
            2: Player 2 wins. */
    public int move(int row, int col, int player) {
        int toAdd = player == 1 ? 1 : -1;
        // if A player makes a move on 0,0 we are going to increment index posistion of row and col at 0,-
        // if B player makes a move on 0,2  we are going to decrement index posistion at row and col at 0,2
        // after that the count of row at 0 will be 0(balanced moves) like wise if a row or col has value n only
        // a player can be adjudged a winner
        rows[row] += toAdd;
        cols[col] += toAdd;
        if (row == col) {
            diagonal += toAdd;
        }

        if (col + row == cols.length - 1) {
            antiDiagonal += toAdd;
        }

        int size = rows.length;
        if (Math.abs(rows[row]) == size ||
            Math.abs(cols[col]) == size ||
            Math.abs(diagonal) == size  ||
            Math.abs(antiDiagonal) == size) {
            return player;
        }

        return 0;
    }

    public static void main(String[] args) {
        DesignTicTacToe toe=new DesignTicTacToe(3);
        toe.move(0, 0, 1);
        toe.move(0, 2, 2);
        toe.move(2, 2, 1);
        toe.move(1, 1, 2);
        toe.move(2, 0, 1);
        toe.move(1, 0, 2);
        toe.move(2, 1, 1);
    }
}