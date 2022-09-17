package sorting;
/*
  A min heap implementation

  Array Form: [ 5, 7, 6, 10, 15, 17, 12 ]

  Complete Binary Tree Form:
                   5
              /         \
          7               6
      /     \          /     \
    10      15        17      12

  Mappings:
    Parent -> (childIndex - 1) / 2
    Left Child -> 2 * parentIndex + 1
    Right Child -> 2 * parentIndex + 2

  YouTube explanation: https://www.youtube.com/watch?v=g9YK6sftDi0
  Heap Sort explanation: https://www.youtube.com/watch?v=k72DtCnY4MU
*/

import java.util.*;

public class MinHeap {

    public MinHeap() {
        elements = new int[capacity];
    }

    private int capacity = 5;
    private int[] elements;
    private int size;

    public void add(int itemToAdd) {
        ensureExtraCapacity();
        elements[size] = itemToAdd;
        size++;
        siftUp();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty.");
        }

        return elements[0];
    }

    public int remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty.");
        }

        int minItem = elements[0];
        elements[0] = elements[size - 1];
        size--;

        heapifyDown();

        return minItem;
    }


    private void heapifyDown() {

      /*
        We will bubble down the item just swapped to the "top" of the heap
        after a removal operation to restore the heap
      */
        int index = 0;

      /*
        Since a binary heap is a complete binary tree, if we have no left child
        then we have no right child. So we continue to bubble down as long as
        there is a left child.

        A non-existent left child immediately tells us that a right child does
        not exist.
      */
        while (hasLeftChild(index)) {
        /*
          By default, assume that left child is smaller. If a right
          child exists see if it can overtake the left child by
          being smaller
        */
            int smallerChildIndex = getLeftChildIndex(index);

            if (hasRightChild(index) && rightChild(index) < leftChild(index)) {
                smallerChildIndex = getRightChildIndex(index);
            }

        /*
          If the item we are sitting on is < the smaller child then
          nothing needs to happen & sifting down is finished.

          But if the smaller child is smaller than the node we are
          holding, we should swap and continue sifting down.
        */
            if (elements[index] < elements[smallerChildIndex]) {
                break;
            } else {
                swap(index, smallerChildIndex);
            }

            // Move to the node we just swapped down
            index = smallerChildIndex;
        }
    }

    // Bubble up the item we inserted at the "end" of the heap
    private void siftUp() {
      /*
        We will bubble up the item just inserted into to the "bottom"
        of the heap after an insert operation. It will be at the last index
        so index 'size' - 1
      */
        int index = size - 1;

      /*
        While the item has a parent and the item beats its parent in
        smallness, bubble this item up.
      */
        while (hasParent(index) && elements[index] < parent(index)) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    /************************************************
     Helpers to access our array easily, perform
     rudimentary operations, and manipulate capacity
     ************************************************/

    private void swap(int indexOne, int indexTwo) {
        int temp = elements[indexOne];
        elements[indexOne] = elements[indexTwo];
        elements[indexTwo] = temp;
    }

    // If heap is full then double capacity
    private void ensureExtraCapacity() {
        if (size == capacity) {
            elements = Arrays.copyOf(elements, capacity * 2);
            capacity *= 2;
        }
    }

    private int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    private int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    private int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size;
    }

    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < size;
    }

    private boolean hasParent(int index) {
        return index != 0 && getParentIndex(index) >= 0;
    }

    private int leftChild(int index) {
        return elements[getLeftChildIndex(index)];
    }

    private int rightChild(int index) {
        return elements[getRightChildIndex(index)];
    }

    private int parent(int index) {
        return elements[getParentIndex(index)];
    }

    /***********************************************/

    private void printUnderlyingArray() {
        System.out.print("[ ");
        for (int item : elements) {
            System.out.print(item + " ");
        }
        System.out.print("]");
    }

    public static void main(String args[]) {
        MinHeap minHeap = new MinHeap();
        int[] insertItems = new int[]{0, 1, 3, 2, -4, 9, 1, 2};

        for (int insertItem : insertItems) {
            minHeap.add(insertItem);
            System.out.println("Add " + insertItem);
            System.out.println("Min is " + minHeap.peek());

            minHeap.printUnderlyingArray();

            System.out.println("\n");
        }

        System.out.println("\n\n");

        for (int i = 0; i < insertItems.length; i++) {
            System.out.println("Remove " + minHeap.remove());
            System.out.println("Min is " + minHeap.peek());

            minHeap.printUnderlyingArray();

            System.out.println("\n");
        }
    }

}