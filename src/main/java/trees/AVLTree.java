package trees;

public class AVLTree<T extends Comparable<T>> implements Tree<T> {

    private Node<T> root;

    @Override
    public Tree<T> insert(T data) {
        root = insert(data, root);
        return this;
    }

    private Node<T> insert(T data, Node<T> node) {
        if (node == null) {
            return new Node<>(data);
        }
        if (data.compareTo(node.getData()) < 0) {
            node.setLeftChild(insert(data, node.getLeftChild()));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRightChild(insert(data, node.getRightChild()));
        } else {
            return node;
        }
        updateHeight(node);
        return applyRotation(node);
    }

    @Override
    public void delete(T data) {
        root = delete(data, root);
    }

    private Node<T> delete(T data, Node<T> node) {
        if (node == null) {
            return null;
        }
        if (data.compareTo(node.getData()) < 0) {
            node.setLeftChild(delete(data, node.getLeftChild()));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRightChild(delete(data, node.getRightChild()));
        } else {
            // One Child or Leaf Node (no children)
            if (node.getLeftChild() == null) {
                return node.getRightChild();
            } else if (node.getRightChild() == null) {
                return node.getLeftChild();
            }
            // Two Children
            node.setData(getMax(node.getLeftChild()));
            node.setLeftChild(delete(node.getData(), node.getLeftChild()));
        }
        updateHeight(node);
        return applyRotation(node);
    }

    @Override
    public void traverse() {
        traverseInOrder(root);
    }

    private void traverseInOrder(Node<T> node) {
        if (node != null) {
            traverseInOrder(node.getLeftChild());
            System.out.println(node);
            traverseInOrder(node.getRightChild());
        }
    }

    @Override
    public T getMax() {
        if (isEmpty()) {
            return null;
        }
        return getMax(root);
    }

    private T getMax(Node<T> node) {
        if (node.getRightChild() != null) {
            return getMax(node.getRightChild());
        }
        return node.getData();
    }

    @Override
    public T getMin() {
        if (isEmpty()) {
            return null;
        }
        return getMin(root);
    }

    private T getMin(Node<T> node) {
        if (node.getLeftChild() != null) {
            return getMin(node.getLeftChild());
        }
        return node.getData();
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * LL Rotation
     *      It is a type of single rotation that is performed when the tree gets unbalanced,
     *      upon insertion of a node into the left subtree of the left child of the imbalance node i.e., upon Left-Left (LL) insertion.
     *      This imbalance indicates that the tree is heavy on the left side.
     *      Hence, a right rotation is applied, left heaviness imbalance is countered and the tree becomes a balanced tree
     * RR Rotation
     *      It is a type of single rotation that is performed when the tree gets unbalanced,
     *      upon insertion of a node into the right subtree of the right child of the imbalance node i.e., upon Right-Right (RR) insertion.
     *      This imbalance indicates that the tree is heavy on the right side.
     *      Hence, a left rotation is applied, right heaviness imbalance is countered and the tree becomes a balanced tree
     * LR Rotation
     *      It is a type of double rotation that is performed when the tree gets unbalanced,
     *      upon insertion of a node into the right subtree of the left child of the imbalance node i.e., upon Left-Right (LR) insertion.
     *      Apply RR Rotation on the left subtree of the imbalanced node as the left child of the imbalanced node is right-heavy.
     *      This process flips the tree and converts it into a left-skewed tree
     *      This is now the case of LL rotation and by rotating the tree along the edge of the imbalanced node.
     * RL Rotation
     *      It is similar to LR rotation but it is performed when the tree gets unbalanced,
     *      upon insertion of a node into the left subtree of the right child of the imbalance node
     *      Apply LL Rotation on the right subtree of the imbalanced node as the right child of the imbalanced node is left-heavy.
     *      This process flips the tree and converts it into a right-skewed tree.
     *      Perform RR Rotation on the imbalanced node to balance the right-skewed tree.
     *
     *
     * @param node
     * @return
     */
    private Node<T> applyRotation(Node<T> node) {
        // left heavy situation
        if (height(node.getLeftChild()) - height(node.getRightChild()) > 1) {
            if (height(node.getLeftChild().getLeftChild()) - height(node.getLeftChild().getRightChild()) < 0) {
                // LR situation
                node.setLeftChild(rotateLeft(node.getLeftChild()));
            }
            // LL situation
            return rotateRight(node);
        }
        if (height(node.getLeftChild()) - height(node.getRightChild()) < -1) { // right heavy situation
            if (height(node.getRightChild()) > 0) {
                // RL situation
                node.setRightChild(rotateRight(node.getRightChild()));
            }
            // RR situation
            return rotateLeft(node);
        }
        return node;
    }

    private Node<T> rotateRight(Node<T> node) {
        Node<T> leftNode = node.getLeftChild();
        Node<T> centerNode = leftNode.getRightChild();
        leftNode.setRightChild(node);
        node.setLeftChild(centerNode);
        updateHeight(node);
        updateHeight(leftNode);
        return leftNode;
    }

    private Node<T> rotateLeft(Node<T> node) {
        Node<T> rightNode = node.getRightChild();
        Node<T> centerNode = rightNode.getLeftChild();
        rightNode.setLeftChild(node);
        node.setRightChild(centerNode);
        updateHeight(node);
        updateHeight(rightNode);
        return rightNode;
    }

    private void updateHeight(Node<T> node) {
        int maxHeight = Math.max(
                height(node.getLeftChild()),
                height(node.getRightChild())
        );
        node.setHeight(maxHeight + 1);
    }

    private int height(Node<T> node) {
        return node != null ? node.getHeight() : 0;
    }

    public static void main(String[] args) {

        Tree<Integer> avlTree = new AVLTree<>();
        avlTree.insert(10).insert(2).insert(6).insert(8).insert(25).insert(18).insert(35).insert(15).insert(22).insert(42)
                .insert(30).insert(40).insert(12).insert(17).insert(19).insert(24).insert(28).insert(33).insert(38);

        avlTree.traverse();

        System.out.println("Max is: " + avlTree.getMax());
        System.out.println("Min is: " + avlTree.getMin());

        System.out.println("Deleting 42 from Tree");
        avlTree.delete(42);

        System.out.println("New Max is: " + avlTree.getMax());

        avlTree.traverse();

    }


}