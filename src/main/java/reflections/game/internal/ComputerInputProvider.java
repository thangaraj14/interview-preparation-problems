

package reflections.game.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class ComputerInputProvider implements InputProvider {
    public final BoardDimensions dimensions;
    private final Random random = new Random();

    public ComputerInputProvider(BoardDimensions dimensions) {
        this.dimensions = dimensions;
    }

    @Override
    public BoardLocation provideNextMove(Board board) {
        List<BoardLocation> availableCells = new ArrayList<>();

        for (int r = 0; r < dimensions.getNumberOfRows(); r++) {
            for (int c = 0; c < dimensions.getNumberOfColumns(); c++) {
                if (board.isCellEmpty(r, c)) {
                    availableCells.add(new BoardLocation(r, c));
                }
            }
        }

        int chosenCell = random.nextInt(availableCells.size());

        return availableCells.get(chosenCell);
    }
}
