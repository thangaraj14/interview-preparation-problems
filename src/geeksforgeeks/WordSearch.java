package geeksforgeeks;

/**
 * https://leetcode.com/problems/word-search/
 */
public class WordSearch {

    public static boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // to find out first char is in board
                if ((word.charAt(0) == board[i][j]) && search(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean search(char[][] board, String word, int i, int j, int index) {
        if (index == word.length()) {
            return true;
        }

        if (i >= board.length || i < 0 || j >= board[i].length || j < 0 || board[i][j] != word.charAt(index)) {
            return false;
        }

        board[i][j] = ' ';
        if (search(board, word, i - 1, j, index + 1) || search(board, word, i + 1, j, index + 1) || search(board, word,
                i, j - 1, index + 1) || search(board, word, i, j + 1, index + 1)) {
            return true;
        }
        // resetting to old char since its DFS
        board[i][j] = word.charAt(index);
        return false;
    }

    public static void main(String[] args) {
        char[][] board = { { 'C', 'A', 'A' }, { 'A', 'A', 'A' }, { 'B', 'C', 'D' } };
        System.out.println(exist(board, "AAB"));
    }
}
