package dsa;

import java.util.Stack;

import static java.lang.Math.max;

/**
 * https://github.com/neerajjain92/data-structures/tree/master/src/com/company/amazon
 */
// unresolved
public class LongestLeafToLeafPath {

    private static class Path {
        Stack<Integer> stack = new Stack<>();

        public Stack<Integer> getStack() {
            return this.stack;
        }

        public void setStack(Stack<Integer> stack) {
            this.stack = stack;
        }
    }

    static class Node {
        int data;
        Node left;
        Node right;

        Node() {
        }

        Node(int data) {
            this.data = data;
        }
    }

    public static int getHeightWithPrintPath(Node root, Stack<Integer> stack, Path path) {
        if (root == null) {
            return 0;
        }
        Path leftPath = new Path();
        Path rightPath = new Path();

        stack.push(root.data);

        int leftHeight = getHeightWithPrintPath(root.left, stack, leftPath);
        int rightHeight = getHeightWithPrintPath(root.right, stack, rightPath);

        Stack<Integer> leftStack = leftPath.getStack();
        Stack<Integer> rightStack = rightPath.getStack();

        Stack<Integer> maxStack = leftStack.size() > rightStack.size() ? leftStack : rightStack;

        // Push the root to this maxStack
        maxStack.push(root.data);
        path.setStack(maxStack);

        stack.pop();
        return max(leftHeight, rightHeight) + 1;
    }

    public static int getDiameter(Node root, Stack<Integer> stack, Path path) {
        if (root == null) {
            return 0;
        }

        Path leftPath = new Path();
        Path rightPath = new Path();
        int leftHeight = getHeightWithPrintPath(root.left, stack, leftPath);
        int rightHeight = getHeightWithPrintPath(root.right, stack, rightPath);

        Stack<Integer> combinedPath = getCombinedPath(leftPath.getStack(), rightPath.getStack(), root.data);

        path.setStack(combinedPath.size() > path.getStack().size() ? combinedPath : path.getStack());
        return max((leftHeight + rightHeight + 1),
                max(getDiameter(root.left, stack, path), getDiameter(root.right, stack, path)));
    }

    private static Stack<Integer> getCombinedPath(Stack<Integer> stack, Stack<Integer> stack1, int root) {
        Stack<Integer> combinedPath = new Stack<>();

        // Since Path will start from leaf and end at leaf So we have to always insert
        // new value in the bottom
        while (!stack.isEmpty()) {
            pushAtBottom(combinedPath, stack.pop());
        }
        combinedPath.push(root);
        while (!stack1.isEmpty()) {
            combinedPath.push(stack1.pop());
        }
        return combinedPath;
    }

    private static void pushAtBottom(Stack<Integer> stack, Integer value) {
        if (stack.isEmpty()) {
            stack.push(value);
            return;
        }
        Integer temp = stack.pop();
        pushAtBottom(stack, value);
        stack.push(temp);
    }

    public static void main(String[] args) {

        Node root = new Node(2);

        root.left = new Node(7);
        root.right = new Node(5);

        root.left.left = new Node(22);
        root.left.right = new Node(6);

        root.left.right.right = new Node(11);
        root.left.right.right.right = new Node(17);
        root.left.right.right.right.right = new Node(77);
        root.left.right.right.right.right.right = new Node(777);
        root.left.right.right.right.right.right.right = new Node(7777);

        root.left.right.left = new Node(15);
        root.left.right.left.left = new Node(16);
        root.left.right.left.left.left = new Node(66);
        root.left.right.left.left.left.left = new Node(666);
        root.left.right.left.left.left.left.left = new Node(6666);

        root.right.right = new Node(9);

        // [Important] This is the part where we can print the path while calculating
        // the height
        // Path leftPath = new Path();
        // getHeightWithPrintPath(root.left, new Stack<>(), leftPath);
        // Path rightPath = new Path();
        // getHeightWithPrintPath(root.right, new Stack<>(), rightPath);
        // System.out.print(leftPath.stack);
        // System.out.print("," + root.data + ",");
        // System.out.println(rightPath.stack);

        Path longestPath = new Path();
        getDiameter(root, new Stack<>(), longestPath);
        System.out.println(longestPath.stack);
    }
}
