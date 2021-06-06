package dsa;

public class MinimumStack {
    
    public static void main(String[] args) {
        MinStack s = new MinStack();
        s.push(3);
        s.push(5);
        s.getMin();
        s.push(2);
        s.push(1);
        s.getMin();
        s.pop();
        s.getMin();
        s.pop();
    }
}

class MinStack {

    private Node head;

    public void push(int x) {
        if (head == null) {
            head = new Node(x, x, null);
        } else {
            head = new Node(x, Math.min(x, head.minSoFar), head);
        }
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.minSoFar;
    }

    private class Node {
        int val;
        int minSoFar;
        Node next;

        private Node(int val, int minSoFar, Node next) {
            this.val = val;
            this.minSoFar = minSoFar;
            this.next = next;
        }
    }
}