package lld.chess;

public class Rook extends Piece {

    public Rook(boolean b) {
    }

    public Rook() {

    }

    @Override
    public boolean canMove(Board board, Box start, Box end) {
        return false;
    }

}
