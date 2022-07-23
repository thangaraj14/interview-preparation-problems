package combinationsandpermutations;

/**
 * https://leetcode.com/problems/beautiful-arrangement
 */
public class BeautifulArrangements {
    public int countArrangement(int n) {
        boolean[] visited = new boolean[n + 1];
        return count(visited, n, n);
    }

    private int count(boolean[] visited, int index, int n) {
        if (index == 0) {
            return 1;
        }
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if ((index % i == 0 || i % index == 0) && !visited[i]) {
                visited[i] = true;
                count += count(visited, index - 1, n);
                visited[i] = false;
            }
        }
        return count;
    }
}
