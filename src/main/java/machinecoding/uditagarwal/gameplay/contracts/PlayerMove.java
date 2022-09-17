package machinecoding.uditagarwal.gameplay.contracts;


import lombok.Getter;
import machinecoding.uditagarwal.chess.model.Cell;
import machinecoding.uditagarwal.chess.model.Piece;

@Getter
public class PlayerMove {

    Piece piece;
    Cell toCell;
}
