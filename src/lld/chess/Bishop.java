package lld.chess;

public class Bishop extends Piece {

    public Bishop(boolean b) {
    }

    @Override
    public boolean canMove(Board board, Box start, Box end) {
        return false;
    }

}
