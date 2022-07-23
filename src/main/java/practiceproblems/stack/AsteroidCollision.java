package practiceproblems.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/asteroid-collision
 */
public class AsteroidCollision {

    public int[] asteroidCollision(int[] asteroids) {
        if (asteroids.length <= 1) return asteroids;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int asteroid : asteroids) {
            if (asteroid > 0) { // Pushing all +ve asteroid
                stack.push(asteroid);
            } else {
                // Remove all positive asteroid before our current asteroid
                while (!stack.isEmpty() && stack.peek() > 0 && Math.abs(stack.peek()) < Math.abs(asteroid))
                    stack.pop();
                // Checking if the stack is empty or the recent asteroid is negative!
                if (stack.isEmpty() || stack.peek() < 0)
                    stack.push(asteroid);
                    // If recent asteroid <= our asteroid, We broke our outer loop if equal we pop it.
                else if (stack.peek() == Math.abs(asteroid))
                    stack.pop();
            }
        }
        int[] output = new int[stack.size()];
        for (int i = output.length - 1; i >= 0; i--)
            output[i] = stack.pop();

        return output;
    }
}
