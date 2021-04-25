package dsa;

/**
 * https://leetcode.com/problems/split-linked-list-in-parts/
 */
class SplitLinkedList {

    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode cur = root;
        int n = 0;
        while (cur != null) {
            cur = cur.next;
            n++;
        }

        int width = n / k;
        int remainder = n % k;

        ListNode[] result = new ListNode[k];
        cur = root;
        for (int i = 0; i < k; ++i) {
            ListNode head = cur;
            for (int j = 0; j < width + (i < remainder ? 1 : 0) - 1; ++j) {
                if (cur != null) {
                    cur = cur.next;
                }
            }
            if (cur != null) {
                ListNode prev = cur;
                cur = cur.next;
                prev.next = null;
            }
            result[i] = head;
        }
        return result;
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
        ListNode[] listNodes = new SplitLinkedList().splitListToParts(root, 3);
        for (ListNode listNode : listNodes) {
            System.out.println(listNode);
        }
    }
}
