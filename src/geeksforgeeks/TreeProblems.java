package geeksforgeeks;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

/**
 * https://www.geeksforgeeks.org/find-deepest-node-binary-tree/
 * https://leetcode.com/discuss/interview-question/376375/amazon-oa-2019-distance-between-nodes-in-bst
 */
public class TreeProblems {

    Node root;

    public static void main(String[] args) {
        TreeProblems tree = new TreeProblems();

        tree.root = new Node(5);
        tree.root.left = new Node(3);
        tree.root.right = new Node(7);
        tree.root.left.left = new Node(2);
        tree.root.left.right = new Node(4);
        tree.root.right.left = new Node(6);
        tree.root.right.right = new Node(10);
        tree.root.right.right.right = new Node(19);
        tree.root.right.right.left = new Node(9);

	/*	System.out.println("Maximum in a tree :" + tree.findMaximum(tree.root));
		System.out.println(tree.searchAnElement(tree.root, 19));
		System.out.println(tree.sizeOfTree(tree.root));
		tree.reverseOrder(tree.root);
		System.out.println("Height of a tree :" + tree.heightOfTree(tree.root));
		tree.levelOrderTraversalData(tree.root);
		tree.deepestNode(tree.root);
		System.out.println(tree.findNumberOfLeaves(tree.root));
		System.out.println(tree.diameterOfTree(tree.root));
		tree.rootToLeafPath(tree.root);
		System.out.println();
		System.out.println(tree.isExistenceOfPathSum(tree.root, 12));
		System.out.println(tree.sumOfElementsInBinaryTree(tree.root, 0));
		tree.printAllAncestors(tree.root, 9);
		System.out.println(tree.printLowestCommonAncestor(tree.root, 4, 10));
		tree.printZigZagTraversal(tree.root);*/
        TreeMap<Integer, Integer> verticalSumOfTree = tree.verticalSumOfTree(tree.root, new TreeMap<Integer, Integer>(),
                0);
        System.out.println();
        for (Map.Entry<Integer, Integer> map : verticalSumOfTree.entrySet()) {
            System.out.print(map.getKey() + ":" + map.getValue() + "|");
        }
		/*tree.printLowestCommonAncestorBST(tree.root, 9, 19);
		System.out.println(tree.isBST(tree.root, Integer.MIN_VALUE, Integer.MAX_VALUE));
		tree.convertBSTToDLL(tree.root);
		System.out.println("---");*/

      /*  System.out.println("Sum Tree :" + tree.toSumTree(tree.root));
        HashMap<Integer, Node> map = new HashMap<>();
        System.out.println(tree.findPair(tree.root, map, 10));*/
    }

    private boolean findPair(Node node, HashMap<Integer, Node> map, int sum) {
        if (node == null) {
            return false;
        }

        findPair(node.left, map, sum);
        map.put(node.data, node);
        int temp = sum - node.data;
        if (map.containsKey(temp)) {
            return true;
        }
        return findPair(node.right, map, sum);
    }

    private int toSumTree(Node node) {
        if (node == null) {
            return 0;
        }

        int old_val = node.data;
        node.data = toSumTree(node.left) + toSumTree(node.right);

        return node.data + old_val;
    }

    Node prev = null;
    Node head;

    private void convertBSTToDLL(Node root) {
        if (root == null) {
            return;
        }
        convertBSTToDLL(root.left);

        if (prev == null) {
            head = root;
        } else {
            root.left = prev;
            prev.right = root;
        }
        prev = root;

        convertBSTToDLL(root.right);
    }

    private boolean isBST(Node root, int min, int max) {
        if (root == null) {
            return true;
        }
        return root.data > min && root.data < max && isBST(root.left, min, root.data) && isBST(root.right, root.data,
                max);

    }

    private void printLowestCommonAncestorBST(Node root, int value1, int value2) {
        if (root == null) {
            return;
        }

        if (value1 < root.data && value2 > root.data) {
            System.out.println(root.data);
            return;
        } else {
            if (value1 < root.data && value2 < root.data) {
                printLowestCommonAncestorBST(root.left, value1, value2);
            } else {
                printLowestCommonAncestorBST(root.right, value1, value2);
            }
        }
    }

    private TreeMap<Integer, Integer> verticalSumOfTree(Node root, TreeMap<Integer, Integer> map, int value) {
        if (root == null) {
            return null;
        }
        map.put(value, map.getOrDefault(value, 0) + root.data);
        if (root.left != null) {
            verticalSumOfTree(root.left, map, value - 1);
        }

        if (root.right != null) {
            verticalSumOfTree(root.right, map, value + 1);
        }

        return map;
    }

    private void printZigZagTraversal(Node root) {
        if (root == null) {
            return;
        }

        Deque<Node> deque = new LinkedList<>();
        deque.push(null);
        deque.push(root);
        /*TreeMap<Integer,PriorityQueue<Integer>> treeMap = new TreeMap<>();
        treeMap.put(0,new PriorityQueue<>(Arrays.asList(1)));*/
        while (!deque.isEmpty()) {
            while (deque.peekFirst() != null) {
                Node top = deque.removeFirst();
                System.out.print(top.data + " ");

                if (top.left != null) {
                    deque.addLast(top.left);
                }

                if (top.right != null) {
                    deque.addLast(top.right);
                }
            }

            while (deque.peekLast() != null) {
                Node last = deque.removeLast();
                System.out.print(last.data + " ");

                if (last.right != null) {
                    deque.addFirst(last.right);
                }

                if (last.left != null) {
                    deque.addFirst(last.left);
                }
            }

            if (deque.size() == 1) {
                deque.removeFirst();
            }
        }
    }

    private Node printLowestCommonAncestor(Node root, int val1, int val2) {
        if (root == null) {
            return null;
        }
        if (root.data == val1 || root.data == val2) {
            return root;
        }

        Node left = printLowestCommonAncestor(root.left, val1, val2);
        Node right = printLowestCommonAncestor(root.right, val1, val2);

        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }

    private boolean printAllAncestors(Node root, int data) {
        if (root == null) {
            return false;
        }
        if (root.data == data) {
            return true;
        }

        if (printAllAncestors(root.left, data) || printAllAncestors(root.right, data)) {
            System.out.print(root.data + " ");
            return true;
        }
        return false;
    }

    private Node convertMirror(Node node) {
        if (node == null) {
            return node;
        }

        Node left = convertMirror(node.left);
        Node right = convertMirror(node.right);

        node.left = right;
        node.right = left;

        return node;
    }

    private int sumOfElementsInBinaryTree(Node root, int sum) {
        if (root == null) {
            return 0;
        }

        return sumOfElementsInBinaryTree(root.left, sum + root.data) + sumOfElementsInBinaryTree(root.right,
                sum + root.data) + root.data;
    }

    private boolean isExistenceOfPathSum(Node root, int sum) {
        if (root == null) {
            return sum == 0;
        }

        int subSum = sum - root.data;

        return isExistenceOfPathSum(root.left, subSum) || isExistenceOfPathSum(root.right, subSum);
    }

    private void rootToLeafPath(Node root) {
        int[] arr = new int[100];
        rootToLeafPath(root, arr, 0);
    }

    private void rootToLeafPath(Node root, int[] arr, int length) {
        if (root == null) {
            return;
        }
        arr[length++] = root.data;

        if (root.left == null && root.right == null) {
            printArray(arr, length);
        } else {
            rootToLeafPath(root.left, arr, length);
            rootToLeafPath(root.right, arr, length);
        }
    }

    private void printArray(int[] arr, int length) {
        System.out.println();
        for (int i = 0; i < length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    int result;

    private int diameterOfTree(Node root) {
        if (root == null) {
            return 0;
        }

        int left_height = diameterOfTree(root.left);
        int right_height = diameterOfTree(root.right);

        int max_diameter = left_height + right_height + 1;
        result = Math.max(result, max_diameter);

        return Math.max(left_height, right_height) + 1;
    }

    private int findNumberOfLeaves(Node root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }

        return findNumberOfLeaves(root.left) + findNumberOfLeaves(root.right);
    }

    private void deepestNode(Node root) {
        if (root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(root);
        Node node = null;
        while (!stack.isEmpty()) {
            node = stack.pop();
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        System.out.println(node.data);
    }

    private void levelOrderTraversalData(Node root) {
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            if (node == null) {
                if (!queue.isEmpty()) {
                    queue.add(null);
                }
                System.out.println();
            } else {
                System.out.print(node.data + " ");
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
    }

    private int heightOfTree(Node root) {
        if (root == null) {
            return 0;
        }
        int left = heightOfTree(root.left);
        int right = heightOfTree(root.right);
        return Math.max(left, right) + 1;
    }

    private void reverseOrder(Node root) {
        if (root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        Stack<Node> stack = new Stack<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.remove();

            if (node.right != null) {
                queue.add(node.right);
            }

            if (node.left != null) {
                queue.add(node.left);
            }
            stack.push(node);
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop().data + " ");
        }

    }

    private int sizeOfTree(Node root) {
        if (root == null) {
            return 0;
        }

        return sizeOfTree(root.left) + 1 + sizeOfTree(root.right);
    }

    private boolean searchAnElement(Node root, int key) {
        if (root == null) {
            return false;
        }
        if (root.data == key) {
            return true;
        }
        return searchAnElement(root.left, key) || searchAnElement(root.right, key);
    }

    private int findMaximum(Node root) {
        int max = 0;
        if (root != null) {
            int root_val = root.data;
            int left = findMaximum(root.left);
            int right = findMaximum(root.right);

            max = Math.max(Math.max(left, right), root_val);
        }
        return max;
    }

    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }
}
