package sorting;

public class InsertionSort {

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6};
        System.out.println(System.currentTimeMillis());

        for (int i = 1; i < arr.length; i++) {
            int index = i;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[index] < arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[index];
                    arr[index] = temp;
                    index--;
                }
            }
        }
        System.out.println(System.currentTimeMillis());
    }

}
