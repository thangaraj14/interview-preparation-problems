package linkedLists;


public class ReverseLinkedListBetweenMandN {

    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode fakeHead = new ListNode(-1);
        fakeHead.next = head;
        ListNode prev = fakeHead;
        ListNode curr = fakeHead.next;
        int i = 1;
        while (i < m) { // i travels till m-1 to store prev
            prev = curr;
            curr = curr.next;
            i++;
        }
        ListNode node = prev;
        while (i <= n) { // normal reverse linkedlist
            ListNode tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
            i++;
        }

        //   for 1->2->3->4->5->null, m = 2, n = 4,
        //   after the second while, we got 1<->2<-3<-4 5->null
      //    now cur = 5, pre = 4, node = 1,
     //    so node.next = 2, node.next.next = cur means 2->5
    //  node.next = pre means 1->4, then we get the result 1->4->3->2->5->null.

        node.next.next = curr;
        node.next = prev;
        return fakeHead.next;
    }
}
