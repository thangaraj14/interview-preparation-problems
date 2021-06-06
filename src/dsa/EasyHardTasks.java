package dsa;

/**
 * https://www.geeksforgeeks.org/dynamic-programming-high-effort-vs-low-effort-tasks-problem/
 */
class EasyHardTasks {

    static int maxTasks(int[] high, int[] low, int n) {

        int[] task = new int[n + 1];

        // If n = 0, no solution exists
        task[0] = 0;

        // high effort task on day one
        task[1] = high[0];

        // for day 2 :  0 + hard task or previous day + easy task
        // for day 3 : day 1 + hard task or previous day + easy task
        for (int i = 2; i <= n; i++) {
            task[i] = Math.max(high[i - 1] + task[i - 2], low[i - 1] + task[i - 1]);
        }
        return task[n];
    }

    public static void main(String[] args) {
        int n = 3;
        int[] high = { 10, 21, 23 };
        //                { 3, 6, 8, 7, 6 };
        int[] low = { 4, 20, 2 };
        //{ 1, 5, 4, 5, 3 };
        System.out.println(maxTasks(high, low, n));
    }
}

