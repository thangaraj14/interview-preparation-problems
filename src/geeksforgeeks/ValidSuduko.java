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
}