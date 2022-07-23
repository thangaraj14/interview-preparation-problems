package linkedLists;

//https://leetcode.com/problems/split-linked-list-in-parts/
class SplitLinkedList {

    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] partsOfRoot = new ListNode[k];
        ListNode head = root;
        int len = size(root);
        int minNoOfElements = len / k;
        int extraRoomForElement = len % k;
        ListNode prev = null;
        for (int i = 0; i < k && head != null; i++, extraRoomForElement--) {
            partsOfRoot[i] = head;
            for (int j = 0; j < minNoOfElements + (extraRoomForElement > 0 ? 1 : 0); j++) {
                prev = head;
                head = head.next;
            }
            prev.next = null; // segregating the list
        }
        return partsOfRoot;

    }

    public int size(ListNode root) {
        int len = 0;
        while (root != null) {
            root = root.next;
            len++;
        }
        return len;
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
