package practiceproblems.design;

/**
 * https://leetcode.com/problems/design-browser-history/
 *
 * tricky linkedlist
 */
public class BrowserHistory {

    Node head;
    Node currentNode;

    public BrowserHistory(String homepage) {
        head = new Node("dummy");

        Node newNode = new Node(homepage);
        head.next = newNode;
        newNode.prev = head;
        currentNode = newNode;
    }

    public void visit(String url) {
        Node newNode = new Node(url);
        head.next = newNode;
        newNode.prev = head;

        currentNode.prev = newNode;
        newNode.next = currentNode;
        currentNode = newNode;
    }

    public String back(int steps) {
        Node curr = currentNode;

        while (steps > 0 && curr.next != null) {
            curr = curr.next;
            steps--;
        }
        currentNode = curr;
        return curr.url;
    }

    public String forward(int steps) {
        Node curr = currentNode;
        while (!curr.prev.url.equals("dummy") && steps > 0) {
            curr = curr.prev;
            steps--;
        }
        currentNode = curr;
        return currentNode.url;

    }

    static class Node {
        String url;
        Node next;
        Node prev;

        public Node(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return prev + " " + url + " " + next;
        }
    }
}

