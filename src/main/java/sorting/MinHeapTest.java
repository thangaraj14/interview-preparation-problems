package sorting;

import java.util.NoSuchElementException;

public class MinHeapTest {

    int[] elements;
    int capacity;
    int currentIndex;

    public MinHeapTest(int capacity) {
        this.capacity = capacity;
        this.elements = new int[capacity];
    }

    public void add(int value) {
        currentIndex++;
        if (currentIndex > capacity) {
            currentIndex--;
            return;
        }

        elements[currentIndex] = value;

        verifyParent(currentIndex);
    }

    public int peek() {
        if (currentIndex < 0 || currentIndex > capacity) return Integer.MIN_VALUE;
        return elements[0];
    }

    public int remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty.");
        }
        int result = elements[0];
        currentIndex--;
        elements[0] = elements[currentIndex];

        verifyChild();
        return result;
    }

    private boolean isEmpty() {
        return currentIndex < 0;
    }

    public void verifyParent(int currentIndex) {
        int index = currentIndex;

        while (hasParent(index) && elements[index] < elements[getParentIndex(index)]) {
            swap(index, getParentIndex(index));
            index = getParentIndex(index);
        }
    }

    private void swap(int index, int parentIndex) {
        int temp = elements[index];
        elements[index] = elements[parentIndex];
        elements[parentIndex] = temp;
    }

    private boolean hasParent(int index) {
        return index != 0 || getParentIndex(index) >= 0;
    }

    private int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    public void verifyChild() {
        int index = 0;

        while (hasLeftChildren(index)) {

            int minIndex = getLeftChildIndex(index);
            if (hasRightChildren(index) && elements[getRightChildIndex(index)] < elements[minIndex]) {
                minIndex = getRightChildIndex(index);
            }

            if (elements[minIndex] < elements[index]) {
                swap(index, minIndex);
            } else {
                break;
            }
            index = minIndex;
        }
    }

    private boolean hasRightChildren(int index) {
        return getRightChildIndex(index) < capacity;
    }

    private int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    private boolean hasLeftChildren(int index) {
        return getLeftChildIndex(index) < capacity;
    }

    private int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

}
