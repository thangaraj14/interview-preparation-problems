package dsa;

public class ArrayToBST {

    static int[] arr = { 1, 2, 3, 4, 5, 6, 7 };

    Node sortedArrayToBST(int arr[], int start, int end) {

        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        Node node = new Node(arr[mid]);
        node.prev = sortedArrayToBST(arr, start, mid - 1);
        node.next = sortedArrayToBST(arr, mid + 1, end);

        return node;
    }

    public static void main(String[] args) {
        ArrayToBST arrToBSt = new ArrayToBST();
        Node sortedArrayToBST = arrToBSt.sortedArrayToBST(arr, 0, 6);
        preOrder(sortedArrayToBST);
    }

    private static void preOrder(Node convertArrayToBST) {
        if (null == convertArrayToBST) {
            return;
        }
        preOrder(convertArrayToBST.prev);
        System.out.print(convertArrayToBST.data + " ");
        preOrder(convertArrayToBST.next);
    }
}
