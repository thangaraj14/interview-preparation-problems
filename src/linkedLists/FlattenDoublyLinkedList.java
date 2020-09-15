package linkedLists;

public class FlattenDoublyLinkedList {

    public Node flatten(Node head) {
        if(head==null) return head;

        Node root=head;

        while(root!=null){

            if(root.child==null) {
                root=root.next;
                continue;
            }else{
                Node temp=root.child;
                while(temp.next!=null){
                    temp=temp.next;
                }
                temp.next= root.next;
                if(root.next!=null) root.next.prev=temp;
                root.next=root.child;
                root.child.prev=root;
                root.child=null;

            }

        }

        return head;

    }
}
