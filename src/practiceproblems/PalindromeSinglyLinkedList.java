package practiceproblems;

/**
 * https://leetcode.com/problems/palindrome-linked-list/
 */
public class PalindromeSinglyLinkedList {

    class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }

    }

    public static void main(String[] args) {

        PalindromeSinglyLinkedList palindrome = new PalindromeSinglyLinkedList();
        Node head = palindrome.new Node(1);
        head.next = palindrome.new Node(2);
        head.next.next = palindrome.new Node(3);
        head.next.next.next = palindrome.new Node(2);
        head.next.next.next.next = palindrome.new Node(1);

        System.out.println(palindrome.isPalindrome(head));

    }

    public boolean isPalindrome(Node head) {
        Node fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        if (fast != null) { // odd nodes: let right half smaller
            slow = slow.next;
        }
        slow = reverse(slow);
        fast = head;

        while (slow != null) {
            if (fast.data != slow.data) {
                return false;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return true;
    }

    public Node reverse(Node slow) {
        Node prev = null;
        while (slow != null) {
            Node next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }
        return prev;
    }

}
