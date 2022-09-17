package machinecoding.uditagarwal.chess.moves;


import machinecoding.uditagarwal.chess.conditions.MoveBaseCondition;
import machinecoding.uditagarwal.chess.conditions.PieceCellOccupyBlocker;
import machinecoding.uditagarwal.chess.conditions.PieceMoveFurtherCondition;
import machinecoding.uditagarwal.chess.model.Board;
import machinecoding.uditagarwal.chess.model.Cell;
import machinecoding.uditagarwal.chess.model.Piece;
import machinecoding.uditagarwal.chess.model.Player;

import java.util.ArrayList;
import java.util.List;

public class PossibleMovesProviderHorizontal extends PossibleMovesProvider {

    public PossibleMovesProviderHorizontal(int maxSteps, MoveBaseCondition baseCondition,
                                           PieceMoveFurtherCondition moveFurtherCondition, PieceCellOccupyBlocker baseBlocker) {
        super(maxSteps, baseCondition, moveFurtherCondition, baseBlocker);
    }

    protected List<Cell> possibleMovesAsPerCurrentType(Piece piece, final Board board, List<PieceCellOccupyBlocker> additionalBlockers, Player player) {
        List<Cell> result = new ArrayList<>();
        result.addAll(findAllNextMoves(piece, board::getLeftCell, board, additionalBlockers, player));
        result.addAll(findAllNextMoves(piece, board::getRightCell, board, additionalBlockers, player));
        return result;
    }
}
