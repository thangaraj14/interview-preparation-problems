package linkedLists;


public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if(k==0 || head==null) return head;

        ListNode tempHead= head;
        int length=1;
        while(tempHead.next!=null){
            length++;
            tempHead=tempHead.next;
        }
        tempHead.next=head;
        k%=length;
        for(int i=0;i<length-k;i++){
            tempHead=tempHead.next;
            head=head.next;
        }
        tempHead.next=null;
        return head;
    }
}

 class ListNode {
    int val;
    public ListNode next;
    ListNode(int x) { val = x; }
}