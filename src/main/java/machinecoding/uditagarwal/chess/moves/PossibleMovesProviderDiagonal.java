package machinecoding.uditagarwal.chess.moves;


import machinecoding.uditagarwal.chess.conditions.MoveBaseCondition;
import machinecoding.uditagarwal.chess.conditions.PieceCellOccupyBlocker;
import machinecoding.uditagarwal.chess.conditions.PieceMoveFurtherCondition;
import machinecoding.uditagarwal.chess.model.Board;
import machinecoding.uditagarwal.chess.model.Cell;
import machinecoding.uditagarwal.chess.model.Piece;
import machinecoding.uditagarwal.chess.model.Player;

import java.util.List;

public class PossibleMovesProviderDiagonal extends PossibleMovesProvider {


    public PossibleMovesProviderDiagonal(int maxSteps, MoveBaseCondition baseCondition,
                                         PieceMoveFurtherCondition moveFurtherCondition, PieceCellOccupyBlocker baseBlocker) {
        super(maxSteps, baseCondition, moveFurtherCondition, baseBlocker);
    }

    @Override
    protected List<Cell> possibleMovesAsPerCurrentType(Piece piece, Board board, List<PieceCellOccupyBlocker> additionalBlockers, Player player) {
        return null;
    }
}
