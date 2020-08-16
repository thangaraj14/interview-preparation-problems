package geeksforgeeks;

import java.util.*;

class PerfectSquare {
    // let's solve by dynamic programming approach
    // if we take value 13, the number of perfect squares less than
    // the input at any point is Sqrt(input)=>(sqrt(13)=3, so max it can have answer below)
    // 3,because 4*4 is 16, so perfect squares below 13 are (1*1, 2*2, 3*3)
    //               13         can be broken down into
    //             1/ 4|  \ 9    deducting 1^2 leaves with val 12
    //             /   |   \     deducting 2^2 leaves with val  9
    //          12      9     4   deducting 3^2 leaves with val  4
    //           /|\     /|\    /|
    //           / | \   8 5 0  3 0
    //           11 7  3

    public int numSquaresDp(int n) {
        int[] ns = new int[n+1];
        Arrays.fill(ns , -1);
        ns[0] = 0;
        for(int i=1;i<n+1;i++){
            int min = i; // initial value is i because it can have i perfect squares(1^2 +1^2+...i)
            int sqrt = (int)Math.sqrt(i); // because i can have max of sqrt(i) perfect squares
            for(int j=sqrt;j>0;j--){
                int result = i - j*j;
                // the reason to take min is for i=5 j=sqrt(5)=2
                // when subract 1 we are left with 4
                // when subract 4 we are left with 1 so minimum is 1 and add 1 to it
                min = Math.min(min, ns[result]+1);
            }
            ns[i] = min;
            System.out.println(Arrays.toString(ns));
        }
        return ns[n];
    }

    public int numSquares(int n){
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

    public static void main(String[] args) {
        new PerfectSquare().numSquaresDp(13);
    }
}