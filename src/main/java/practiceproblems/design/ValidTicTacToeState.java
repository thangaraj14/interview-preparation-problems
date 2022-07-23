package practiceproblems.design;

/**
 * TODO- revision
 * https://leetcode.com/problems/valid-tic-tac-toe-state
 */
public class ValidTicTacToeState {
    public boolean validTicTacToe(String[] board) {
        // turns = 0 represents 'X' will move, otherwise, 'O' will move
        int turns = 0;

        // check whether 'X' wins or 'O' wins, or no players win
        boolean xWin = getWinCombination(board, 'X');
        boolean yWin = getWinCombination(board, 'O');

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i].charAt(j) == 'X') turns++;
                if (board[i].charAt(j) == 'O') turns--;
            }
        }

        /**
         * Four conditions will be the invalid tic tac toe board:
         * 1. there are more 'O' than 'X'
         * 2. the board has 2 more 'X' than 'O'
         * 3. number of 'X' is equal to number of 'O', but 'X' wins, it is impossible because if 'X' wins, the game is
              over, 'O' cannot play again, then number of 'O' MUST less than 'X'
         * 4. number of 'X' is more than number of 'O', but 'O' wins, it is impossible because if 'O' wins, the game is
              over, 'X' cannot play again, then number of 'X' CANNOT greater than 'O'
         * */
        return turns >= 0 && turns <= 1 && (turns != 0 || !xWin) && (turns != 1 || !yWin);
    }

    public boolean getWinCombination(String[] board, char target) {

        // check horizontal
        for (int i = 0; i < 3; i++) {
            if (board[i].charAt(0) == target && board[i].charAt(0) == board[i].charAt(1) && board[i].charAt(1) == board[i].charAt(2)) {
                return true;
            }
        }
        // check vertical
        for (int j = 0; j < 3; j++) {
            if (board[0].charAt(j) == target && board[0].charAt(j) == board[1].charAt(j) && board[1].charAt(j) == board[2].charAt(j)) {
                return true;
            }
        }

        // check diagonal
        if (board[0].charAt(0) == target && board[0].charAt(0) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(2)) {
            return true;
        }
        // check diagonal
        return board[2].charAt(0) == target && board[2].charAt(0) == board[1].charAt(1) && board[1].charAt(1) == board[0].charAt(2);

    }
}
