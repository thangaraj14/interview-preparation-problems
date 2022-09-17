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

import static machinecoding.uditagarwal.chess.moves.VerticalMoveDirection.*;

public class PossibleMovesProviderVertical extends PossibleMovesProvider {
    private VerticalMoveDirection verticalMoveDirection;

    public PossibleMovesProviderVertical(int maxSteps, MoveBaseCondition baseCondition,
                                         PieceMoveFurtherCondition moveFurtherCondition, PieceCellOccupyBlocker baseBlocker,
                                         VerticalMoveDirection verticalMoveDirection) {
        super(maxSteps, baseCondition, moveFurtherCondition, baseBlocker);
        this.verticalMoveDirection = verticalMoveDirection;
    }


    @Override
    protected List<Cell> possibleMovesAsPerCurrentType(Piece piece, Board board, List<PieceCellOccupyBlocker> additionalBlockers, Player player) {
        List<Cell> result = new ArrayList<>();
        if (this.verticalMoveDirection == UP || this.verticalMoveDirection == BOTH) {
            result.addAll(findAllNextMoves(piece, board::getUpCell, board, additionalBlockers, player));
        }
        if (this.verticalMoveDirection == DOWN || this.verticalMoveDirection == BOTH) {
            result.addAll(findAllNextMoves(piece, board::getDownCell, board, additionalBlockers, player));
        }
        return result;
    }
}
