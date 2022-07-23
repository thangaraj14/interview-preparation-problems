

package reflections.game.internal;

class Board {
    private Cell[][] cells;
    private BoardDimensions dimensions;

    public Board(BoardDimensions boardDimensions) {
        this.dimensions = boardDimensions;
        this.cells = new Cell[boardDimensions.getNumberOfColumns()][boardDimensions.getNumberOfRows()];
        initAllCells();
    }

    private void initAllCells() {
        for (int r = 0; r < dimensions.getNumberOfRows(); r++) {
            for (int c = 0; c < dimensions.getNumberOfColumns(); c++) {
                this.cells[c][r] = new Cell();
            }
        }
    }

    public void updateCell(int row, int column, Sign sign) {
        this.cells[column][row].setSign(sign);
    }

    public Sign checkWinner() {
        // Check rows
        for (int r = 0; r < dimensions.getNumberOfRows(); r++) {
            Sign sign = getRowWinner(r);
            if (sign != Sign.EMPTY) {
                return sign;
            }
        }

        // Check columns
        for (int c = 0; c < dimensions.getNumberOfColumns(); c++) {
            Sign sign = getColumnWinner(c);
            if (sign != Sign.EMPTY) {
                return sign;
            }
        }

        // Check diagonal
        Sign sign = getDiagonalWinner(0, 0, 1, 1);
        if (sign != Sign.EMPTY) {
            return sign;
        }

        // Check diagonal
        return getDiagonalWinner(0, dimensions.getNumberOfColumns() - 1, -1, 1);
    }

    public boolean isCellEmpty(int row, int column) {
        return this.cells[column][row].isEmpty();
    }

    public char getPrintableCellSign(int row, int column) {
        return this.cells[column][row].getSign().getValue();
    }

    public boolean isBoardFull() {
        for (int r = 0; r < dimensions.getNumberOfRows(); r++) {
            for (int c = 0; c < dimensions.getNumberOfColumns(); c++) {
                if (this.cells[c][r].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private Sign getColumnWinner(int currentColumn) {
        Sign initialSign = this.cells[currentColumn][0].getSign();

        if (initialSign == Sign.EMPTY) {
            return initialSign;
        }

        for (int r = 1; r < dimensions.getNumberOfRows(); r++) {
            if (this.cells[currentColumn][r].getSign() != initialSign) {
                return Sign.EMPTY;
            }
        }
        return initialSign;
    }

    private Sign getRowWinner(int currentRow) {
        Sign initialSign = this.cells[0][currentRow].getSign();

        if (initialSign == Sign.EMPTY) {
            return initialSign;
        }

        for (int c = 1; c < dimensions.getNumberOfColumns(); c++) {
            if (this.cells[c][currentRow].getSign() != initialSign) {
                return Sign.EMPTY;
            }
        }
        return initialSign;
    }


    private Sign getDiagonalWinner(int startRow, int startColumn, int horizontalStep, int verticalStep) {
        Sign initialSign = this.cells[startColumn][startRow].getSign();
        if (initialSign == Sign.EMPTY) {
            return Sign.EMPTY;
        }

        int r = startRow + verticalStep;
        int c = startColumn + horizontalStep;

        while (r < dimensions.getNumberOfRows() && c < dimensions.getNumberOfColumns()) {
            if (this.cells[c][r].getSign() != initialSign) {
                return Sign.EMPTY;
            }
            r += verticalStep;
            c += horizontalStep;
        }

        return initialSign;
    }
}
