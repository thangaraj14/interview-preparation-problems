package lld.chess;

public class Board {
    Box[][] boxes;

    public Board() {
        boxes = new Box[8][8];
        this.resetBoard();
    }

    public Box getBox(int x, int y) {

        if (x < 0 || x > 7 || y < 0 || y > 7) {
            try {
                throw new Exception("Index out of bound");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return boxes[x][y];
    }

    public void resetBoard() {
        // initialize white pieces
        boxes[0][0] = new Box(0, 0, new Rook(true));
        boxes[0][1] = new Box(0, 1, new Knight(true));
        boxes[0][2] = new Box(0, 2, new Bishop(true));
        boxes[0][3] = new Box(0, 3, new King(true));
        boxes[0][4] = new Box(0, 4, new Queen(true));
        boxes[0][5] = new Box(0, 5, new Bishop(true));
        boxes[0][6] = new Box(0, 6, new Knight(true));
        boxes[0][7] = new Box(0, 7, new Rook(true));

        for (int i = 0; i < 8; i++) {
            boxes[1][i] = new Box(1, i, new Pawn(true));
        }

        // initialize black pieces
        boxes[7][0] = new Box(7, 0, new Rook(false));
        boxes[7][1] = new Box(7, 1, new Knight(false));
        boxes[7][2] = new Box(7, 2, new Bishop(false));
        boxes[7][3] = new Box(7, 3, new King(false));
        boxes[7][4] = new Box(7, 4, new Queen(false));
        boxes[7][5] = new Box(7, 5, new Bishop(false));
        boxes[7][6] = new Box(7, 6, new Knight(false));
        boxes[7][7] = new Box(7, 7, new Rook(false));

        for (int i = 0; i < 8; i++) {
            boxes[6][i] = new Box(6, i, new Pawn(false));
        }

        // initialize remaining boxes without any piece
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                boxes[i][j] = new Box(i, j, null);
            }
        }
    }
}