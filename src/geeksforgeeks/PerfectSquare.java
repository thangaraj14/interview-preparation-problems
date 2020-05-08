package geeksforgeeks;

<<<<<<< HEAD
class PerfectSquare {
=======
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/perfect-squares/
 */
class PerfectSquare {

>>>>>>> fa44d45e65bd24e807ebda00da7c1fd078295163
    public int numSquares(int n) {
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        q.offer(0);
        visited.add(0);
        int depth = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            depth++;
            while (size > 0) {
                int removed = q.poll();
                for (int i = 1; i * i <= n; i++) {
                    int v = removed + i * i;
                    if (v == n) {
                        return depth;
                    }
                    if (v > n) {
                        break;
                    }
                    if (!visited.contains(v)) {
                        q.offer(v);
                        visited.add(v);
                    }
                }
                size--;
            }
        }
        return depth;
    }
<<<<<<< HEAD
=======

    public static void main(String[] args) {
        PerfectSquare ps = new PerfectSquare();
        System.out.println(ps.numSquares(13));
    }
>>>>>>> fa44d45e65bd24e807ebda00da7c1fd078295163
}