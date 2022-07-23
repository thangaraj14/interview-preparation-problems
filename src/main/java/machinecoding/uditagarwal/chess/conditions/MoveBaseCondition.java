package machinecoding.uditagarwal.chess.conditions;


import machinecoding.uditagarwal.chess.model.Piece;

/**
 * It provides the base condition for a piece to make a move. The piece would only be allowed to move from its current
 * position if the condition fulfills.
 */
public interface MoveBaseCondition {
    boolean isBaseConditionFullfilled(Piece piece);
}
