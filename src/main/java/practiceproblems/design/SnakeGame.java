package practiceproblems.design;

import java.util.LinkedList;

/**
 * tricky Linkedlist
 */
public class SnakeGame {

    // used to verify boundary conditions
    int width;
    int height;

    // food position
    int[][] food;
    int score;

    // body of snake
    LinkedList<Node> snake;

    public SnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.food = food;

        snake = new LinkedList<>();
        snake.add(new Node(0, 0));
    }


    public int move(String direction) {
        Node head = snake.peekFirst();
        Node nxt = new Node(head.x, head.y);

        switch (direction) {
            case "U":
                nxt.x--;
                break;
            case "D":
                nxt.x++;
                break;
            case "L":
                nxt.y--;
                break;
            case "R":
                nxt.y++;
        }

        // boundary check
        if (nxt.x < 0 || nxt.x >= height || nxt.y < 0 || nxt.y >= width)
            return -1;

        // body check -> check size()-1 as the last node will be cleared(when snake moves) if moved to next block
        for (int i = 0; i < snake.size() - 1; i++) {
            if (nxt.isEqual(snake.get(i))) {
                return -1;
            }
        }

        // is food? Eat!
        if (score < food.length && nxt.x == food[score][0] && nxt.y == food[score][1]) {
            score++;
            snake.addFirst(nxt);
        } else {
            snake.addFirst(nxt);
            snake.removeLast();
        }

        return score;
    }


    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean isEqual(Node node) {
            return this.x == node.x && this.y == node.y;
        }
    }

}