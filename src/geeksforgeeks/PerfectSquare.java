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

    public int numSquaresDp(int n) {
        int[] ns = new int[n+1];
        Arrays.fill(ns , Integer.MAX_VALUE);
        ns[0] = 0;
        for(int i=1;i<n+1;i++){
            int min = Integer.MAX_VALUE;
            int sqrt = (int)Math.sqrt(i);
            for(int j=sqrt;j>0;j--){
                int result = i - j*j;
                min = Math.min(min, ns[result]+1);
            }       
            ns[i] = min;
        }
        return ns[n];
    }
}