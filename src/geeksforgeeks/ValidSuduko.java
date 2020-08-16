package geeksforgeeks;

class VulgarSuduko {
    public boolean isValidSudoku(char[][] board) {
        Set<String> seen = new HashSet<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    char number = board[i][j];
                    if (!seen.add(number + "seen in row" + i) || !seen.add(number + "seen in col" + j)
                            || !seen.add(number + "seen in block" + i / 3 + "-" + j / 3)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
    public static void main(String[] args) {
        char[][] board = { { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' }, { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' }, { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' }, { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' }, { '.', '.', '.', '.', '8', '.', '.', '7', '9' } };

        ValidSudoku vs = new ValidSudoku();
        System.out.println(vs.isValidSudoku(board));
    }
}