package practiceproblems;

import java.util.HashSet;
import java.util.LinkedList;

public class SnakeGame {
    int[][] food;
    int m, n;
    int headX, headY;
    int eaten;
    private HashSet<String> snake;
    LinkedList<int[]> queue;

    /**
     * Initialize your data structure here.
     *
     * @param width  - screen width
     * @param height - screen height
     * @param food   - A list of food positions
     *               E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0].
     */
    public SnakeGame(int width, int height, int[][] food) {
        this.food = food;
        snake = new HashSet<String>();
        eaten = 0;
        headX = 0;
        headY = 0;
        m = height;
        n = width;
        queue = new LinkedList<int[]>();
        queue.offer(new int[]{0, 0});
        snake.add("0,0");
    }

    /**
     * Moves the snake.
     *
     * @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     * @return The game's score after the move. Return -1 if game over.
     * Game over when snake crosses the screen boundary or bites its body.
     */
    public int move(String direction) {
        if (direction.equals("U")) {
            headX--;
        } else if (direction.equals("L")) {
            headY--;
        } else if (direction.equals("R")) {
            headY++;
        } else if (direction.equals("D")) {
            headX++;
        } else {
            System.out.println("Wrong move");
        }

        if (!isValid(headX, headY)) {
            return -1;
        }

        return process(headX, headY);
    }

    public boolean isValid(int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n)
            return false;
        return true;
    }

    public int process(int x, int y) {
        if (eaten == food.length) {
            snake.remove(queue.peek()[0] + "," + queue.peek()[1]);
            queue.poll();
        } else if (food[eaten][0] == x && food[eaten][1] == y) {
            eaten++;
        } else {
            snake.remove(queue.peek()[0] + "," + queue.peek()[1]);
            queue.poll();
        }

        if (snake.contains(x + "," + y)) {
            return -1;
        }

        snake.add(x + "," + y);
        queue.offer(new int[]{x, y});

        return eaten;
    }
}