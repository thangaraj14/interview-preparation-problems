package geeksforgeeks;

//https://leetcode.com/problems/split-linked-list-in-parts/
class SplitLinkedList {
    public ListNode[] splitListToParts(ListNode root, int k) {
        if (root == null) return null;
        if (k == 0) return null;

        if (k == 1) {
            ListNode[] node = new ListNode[1];
            node[0] = root;
            return node;
        }


        ListNode[] node = new ListNode[k];
        int length = getRootLength(root);

        if (k > length) {
            ListNode temp = root;
            int index = 0;

            while (temp != null) {
                ListNode result = temp;
                temp = temp.next;
                result.next = null;
                node[index] = result;
                index++;
            }

            for (; index < k; index++) {
                node[index] = null;
            }
            return node;
        }

        int remainder = length % k;
        int quo = length / k;

        for (int i = 0; i < k; i++) {
            int value = remainder > 0 ? quo + 1 : quo;
            remainder--;
            ListNode head = root;
            ListNode prev = null;
            for (int j = 0; j < value && root != null; j++) {
                if (j == value - 1) {
                    prev = root;
                    prev.next = null;
                }
                root = root.next;
            }
            node[i] = head;
        }

        return node;
    }

    public int getRootLength(ListNode root) {
        ListNode temp = root;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    public static void main(String[] args) {
        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        root.next.next = new ListNode(3);
        root.next.next.next = new ListNode(4);
        root.next.next.next.next = new ListNode(5);
        root.next.next.next.next.next = new ListNode(6);
        root.next.next.next.next.next.next = new ListNode(7);
        root.next.next.next.next.next.next.next = new ListNode(8);
        root.next.next.next.next.next.next.next.next = new ListNode(9);
        root.next.next.next.next.next.next.next.next.next = new ListNode(10);
        new SplitLinkedList().splitListToParts(root, 3);
    }
}
