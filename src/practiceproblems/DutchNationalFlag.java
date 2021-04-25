package practiceproblems;

class DutchNationalFlag {
    public void sortColors(int[] arr) {
        if (arr.length == 0) {
            return;
        }
        int pivot = 1;
        int i = 0;
        int j = arr.length - 1;
        int zeroPos = 0;
        while (i <= j) {
            if (arr[i] > pivot) {
                swap(arr, i, j);
                j--;
            } else if (arr[i] == pivot) {
                i++;
            } else {
                swap(arr, zeroPos, i);
                zeroPos++;
                i++;
            }
        }
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }
}