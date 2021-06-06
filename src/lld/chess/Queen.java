package lld.chess;

public class Queen extends Piece {

    public Queen(boolean white) {

    }

    @Override
    public boolean canMove(Board board, Box start, Box end) {
        return false;
    }

}
