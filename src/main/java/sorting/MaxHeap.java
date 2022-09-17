package sorting;

public class MaxHeap {

    public static void main(String[] args) {
        int arr[] = {7, 4, 55, 2, 77, 54, 3, 1, 12, 16, 19, 20};
        MaxHeap heap = new MaxHeap();
        heap.sort(arr);

    }

    private void sort(int[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, i, n);

        for (int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");

        System.out.println();
        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, 0, i);
        }

        for (int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");
    }

    private void heapify(int[] arr, int i, int n) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest])
            largest = left;
        if (right < n && arr[right] > arr[largest])
            largest = right;
        if (largest != i) {
            int temp = arr[largest];
            arr[largest] = arr[i];
            arr[i] = temp;

            heapify(arr, largest, n);
        }
    }
}