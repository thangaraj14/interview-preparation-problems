package geeksforgeeks;

class MinStepsToConvertXtoY {
    private static int minOperations(int src, int target) {

        Set<GFG> visited = new HashSet<>(1000);
        LinkedList<GFG> queue = new LinkedList<GFG>();

        GFG node = new GFG(src, 0);

        queue.offer(node);
        visited.add(node);

        while (!queue.isEmpty()) {
            GFG temp = queue.poll();
            visited.add(temp);

            if (temp.val == target) {
                return temp.steps;
            }

            int mul = temp.val * 2;
            int sub = temp.val - 1;

            // given constraints
            if (mul > 0 && mul < 1000) {
                GFG nodeMul = new GFG(mul, temp.steps + 1);
                queue.offer(nodeMul);
            }
            if (sub > 0 && sub < 1000) {
                GFG nodeSub = new GFG(sub, temp.steps + 1);
                queue.offer(nodeSub);
            }
        }
        return -1;
    }
}