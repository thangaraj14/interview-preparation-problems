package linkedLists;
class FlattenLinkedList 
{ 
    Node head;  
  
    class Node 
    { 
        int data; 
        Node right, down; 
        Node(int data) 
        { 
            this.data = data; 
            right = null; 
            down = null; 
        } 
        
        @Override
        public String toString() {
        	return "" + this.data;
        }
    } 
  
    public Node flatten(Node root){
        if(root==null || root.right==null) return root;
       Node right= flatten(root.right);
       root= merge(root,right);
        return root;
    }

    public Node merge(Node root, Node right){
        Node dummy= new Node(0);

        Node head=dummy;

        while(root.down!=null && right.down!=null){
            if(root.data<right.data){
                head.down= root.down;
                root=root.down;
            }else{
                head.down= right.down;
                right=right.down;
            }
            head=head.down;
        }

        while(root.down!=null){
            head.down= root.down;
            head=head.down; root=root.down;
        }
        while (right.down!=null){
            head.down= right.down;
            head=head.down;
            right=right.down;
        }

        return dummy.down;

    }



    Node push(Node head_ref, int data) 
    { 
        Node new_node = new Node(data); 
        new_node.down = head_ref; 
        head_ref = new_node; 
  
        return head_ref; 
    } 
  
    void printList() 
    { 
        Node temp = head; 
        while (temp != null) 
        { 
            System.out.print(temp.data + " "); 
            temp = temp.down; 
        } 
        System.out.println(); 
    } 
  
    public static void main(String args[]) 
    { 
        flattenList(); 
    }

	protected static void flattenList() {
		FlattenLinkedList L = new FlattenLinkedList(); 
  
        /* Let us create the following linked list 
            5 -> 10 -> 19 -> 28 
            |    |     |     | 
            V    V     V     V 
            7    20    22    35 
            |          |     | 
            V          V     V 
            8          50    40 
            |                | 
            V                V 
            30               45 
        */
  
        L.head = L.push(L.head, 30); 
        L.head = L.push(L.head, 8); 
        L.head = L.push(L.head, 7); 
        L.head = L.push(L.head, 5); 
  
        L.head.right = L.push(L.head.right, 20); 
        L.head.right = L.push(L.head.right, 10); 
  
        L.head.right.right = L.push(L.head.right.right, 50); 
        L.head.right.right = L.push(L.head.right.right, 22); 
        L.head.right.right = L.push(L.head.right.right, 19); 
  
        L.head.right.right.right = L.push(L.head.right.right.right, 45); 
        L.head.right.right.right = L.push(L.head.right.right.right, 40); 
        L.head.right.right.right = L.push(L.head.right.right.right, 35); 
  
        // flatten the list 
        L.head = L.flatten(L.head); 
  
        L.printList();
	} 
}