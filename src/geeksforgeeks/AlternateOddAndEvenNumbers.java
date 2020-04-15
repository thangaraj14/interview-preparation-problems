package geeksforgeeks;
// A JAVA program to put positive numbers at even indexes
// (0, 2, 4,..) and negative numbers at odd indexes (1, 3, 
// 5, ..) 

/**
 * https://www.geeksforgeeks.org/rearrange-positive-and-negative-numbers-publish/
 */
class AlternateOddAndEvenNumbers {

    static void rearrange(int arr[], int n) {
        //-1, 2, -3, 4, 5, 6, -7, 8, 9
        int i = -1, temp = 0;
        for (int j = 0; j < n; j++) {
            if (arr[j] < 0) {
                i++;
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int pos = i + 1, neg = 0;

        while (pos < n && neg < pos && arr[neg] < 0) {
            temp = arr[neg];
            arr[neg] = arr[pos];
            arr[pos] = temp;
            pos++;
            neg += 2;
        }
    }

    static void printArray(int arr[], int n) {
        for (int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");
    }

    public static void main(String[] args) {
        int arr[] = { -1, 2, -3, 4, 5, 6, -7, 8, 9 };
        int n = arr.length;
        printArray(arr, n);
        rearrange(arr, n);
        System.out.println("Array after rearranging: ");
        printArray(arr, n);
    }

}
