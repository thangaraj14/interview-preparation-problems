package machinecoding.uditagarwal.chess.moves;


import machinecoding.uditagarwal.chess.model.Cell;

/**
 * Given a cell, it will provide next cell which we can reach to.
 */
interface NextCellProvider {
    Cell nextCell(Cell cell);
}
