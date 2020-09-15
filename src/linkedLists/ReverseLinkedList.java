package linkedLists;

public class ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
        if(head==null || head.next==null) return head;
        // for 1->2->3->4->5->null
        // 5 will be returned(5->null) when head is at 4
        // head.next.next means 4's next(5)'s next is 4=> 5->4->null for top recursion
        // when head is 3,  5->4->null is returned as tmp at this point 3's->4 and 5's-> also 4
        // head.next.next means 3's next 4's(5->4->null) next is 3=> 5->4->3->null
        ListNode temp= reverseList(head.next);
        head.next.next= head;
        head.next=null;
        return temp;

    }

    public ListNode reverseList1(ListNode head) {
        if(head==null) return null;
        ListNode root=head;
        ListNode prev=null;
        while(root!=null){
            ListNode temp=root.next;
            root.next=prev;
            prev=root;
            root=temp;
        }
        return prev;

    }

    public static void main(String[] args) {
        ListNode node= new ListNode(1);
        node.next= new ListNode(2);
        node.next.next= new ListNode(3);
        node.next.next.next= new ListNode(4);
        node.next.next.next.next= new ListNode(5);
       new ReverseLinkedList().reverseList(node);
    }
}
