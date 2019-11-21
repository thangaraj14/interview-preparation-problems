package geeksforgeeks;

/*https://leetcode.com/problems/search-in-rotated-sorted-array/*/
public class SearchElementInSortedAndRotatedArray {

    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 7, 0, 1, 2};
        SearchElementInSortedAndRotatedArray search = new SearchElementInSortedAndRotatedArray();
        System.out.println(search.searchInArray(arr, 1));
    }

    // 4, 5, 6, 7, 0, 1, 2
    int searchInArray(int arr[], int key) {

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {

            int mid = (left + right) / 2;
            if (arr[mid] == key)
                return mid;

            if (arr[left] < arr[mid]) {
                if (key > arr[left] && key < arr[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (key > arr[mid] && key < arr[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

}
