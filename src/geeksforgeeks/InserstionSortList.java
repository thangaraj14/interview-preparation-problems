package geeksforgeeks;

public class InserstionSortList {
    public ListNode insertionSortList(ListNode head) {
        if(head==null) return head;
        ListNode dummy= new ListNode(-1);
        ListNode root=head;
        
        while(root!=null){
            ListNode currNext=root.next;
            ListNode temp=dummy;
            
            while(temp.next!=null && temp.next.val<root.val){
                temp=temp.next;
            }
            
            ListNode tempNext=temp.next;
            temp.next=root;
            root.next=tempNext;
            root=currNext;
                
        }
        
        return dummy.next;
    }
}