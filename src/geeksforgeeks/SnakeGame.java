package geeksforgeeks;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://baihuqian.github.io/2018-08-12-design-snake-game/
 * <p>
 * https://leetcode.com/problems/design-snake-game/
 */
class SnakeGame {

    Queue<Integer> snake;
    boolean[][] board;
    int[][] food;
    int foodIndex;
    int height, width;
    int row, col;
    int score;

    /**
     * Initialize your data structure here.
     *
     * @param width  - screen width
     * @param height - screen height
     * @param food   - A list of food positions
     *               E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0].
     */
    public SnakeGame(int width, int height, int[][] food) {
        this.board = new boolean[height][width];
        this.food = food;
        this.foodIndex = 0;
        this.snake = new LinkedList<>();
        this.snake.offer(0);
        this.board[0][0] = true;

        this.width = width;
        this.height = height;

        this.row = 0;
        this.col = 0;

        this.score = 0;
    }

    /**
     * Moves the snake.
     *
     * @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     *
     * @return The game's score after the move. Return -1 if game over.
     * Game over when snake crosses the screen boundary or bites its body.
     */
    public int move(String direction) {
        if (score == -1) {
            return score;
        }
        if (direction.equals("U")) {
            row--;
        } else if (direction.equals("L")) {
            col--;
        } else if (direction.equals("R")) {
            col++;
        } else if (direction.equals("D")) {
            row++;
        }
        // cross boundary
        if (row < 0 || row >= height || col < 0 || col >= width) {
            score = -1;
            return score;
        }
        // not food
        if (foodIndex == food.length || !(row == food[foodIndex][0] && col == food[foodIndex][1])) {
            int idx = snake.poll();
            board[idx / width][idx % width] = false;
        } else {
            score++;
            foodIndex++;
        }
        if (board[row][col]) { // bite itself
            score = -1;
            return score;
        } else {
            snake.offer(row * width + col);
            board[row][col] = true;
        }
        return score;
    }
}