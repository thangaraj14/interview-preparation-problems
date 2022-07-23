package machinecoding.uditagarwal.chess.conditions;


import machinecoding.uditagarwal.chess.model.Board;
import machinecoding.uditagarwal.chess.model.Cell;
import machinecoding.uditagarwal.chess.model.Piece;

/**
 * Condition interface to tell whether a piece can further move from a cell.
 */
public interface PieceMoveFurtherCondition {

    boolean canPieceMoveFurtherFromCell(Piece piece, Cell cell, Board board);
}
