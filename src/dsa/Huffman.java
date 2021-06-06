package dsa;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Huffman {

    public static void printCode(HuffmanNode root, StringBuilder sb) {

        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null && Character.isLetter(root.c)) {
            System.out.println(root.c + ":" + sb.toString());
            sb.deleteCharAt(sb.length() - 1);
            return;
        }
        printCode(root.left, sb.append("0"));
        printCode(root.right, sb.append("1"));
    }

    public static void main(String[] args) {

        int n = 6;
        char[] charArray = { 'a', 'b', 'c', 'd', 'e', 'f' };
        int[] charFreq = { 5, 9, 12, 13, 16, 45 };

        PriorityQueue<HuffmanNode> q = new PriorityQueue<>(n, Comparator.comparing(HuffmanNode::getData));

        for (int i = 0; i < n; i++) {

            HuffmanNode hn = new HuffmanNode();

            hn.c = charArray[i];
            hn.data = charFreq[i];

            q.add(hn);
        }

        HuffmanNode root = null;

        while (q.size() > 1) {

            HuffmanNode left = q.poll();
            HuffmanNode right = q.poll();

            HuffmanNode newNode = new HuffmanNode();

            newNode.data = left.data + right.data;
            newNode.c = '-';

            newNode.left = left;
            newNode.right = right;

            root = newNode;
            q.add(newNode);
        }

        printCode(root, new StringBuilder());
    }

    static class HuffmanNode {

        int data;
        char c;

        HuffmanNode left;
        HuffmanNode right;

        public int getData() {
            return data;
        }

        @Override
        public String toString() {
            return "{" + "c=" + c + '}';
        }
    }
}