package geeksforgeeks;

class PerfectSquare {
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
}