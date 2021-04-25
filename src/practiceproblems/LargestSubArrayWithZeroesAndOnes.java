package practiceproblems;

import java.util.HashMap;

/*https://www.geeksforgeeks.org/largest-subarray-with-equal-number-of-0s-and-1s/
    Given an array containing only 0s and 1s, find the largest subarray which contains equal no of 0s and 1s.
     Expected time complexity is O(n).
*/
class LargestSubArrayWithZeroesAndOnes { 
    /**
    * The concept of taking cumulative sum, taking 0’s as -1 will help us in optimising the approach.
    * While taking the cumulative sum, there are two cases when there can be a sub-array with equal number of 0’s and 1’s
    * When cumulative sum=0, which signifies that sub-array from index (0) till present index has equal number of 0’s and 1’s.
    *
    * When we encounter a cumulative sum value which we have already encountered before,
    * which means that sub-array from the previous index+1 till the present index has equal number of 0’s and 1’s as they give a cumulative sum of 0 .
    * @param arr
    * @param n
    * @return
    */
   int maxLen(int arr[], int n) {

       HashMap<Integer, Integer> map = new HashMap<>();

       int sum = 0;
       int maxLength = 0;
       int endingIndex = -1;

       for (int i = 0; i < n; i++) {
           arr[i] = (arr[i] == 0) ? -1 : 1;
       }

       for (int i = 0; i < n; i++) {
           sum += arr[i];

           if (sum == 0) {  // To handle sum=0 at last index
               maxLength = i + 1;
               endingIndex = i;
           }
           // If this sum is seen before,
           // then update max_len if required
           if (map.containsKey(sum)) {
               if (maxLength < i - map.get(sum)) {
                   maxLength = i - map.get(sum);
                   endingIndex = i;
               }
           } else
               map.put(sum, i);
       }

       for (int i = 0; i < n; i++) {
           arr[i] = (arr[i] == -1) ? 0 : 1;
       }

       int start = endingIndex - maxLength + 1;
       System.out.println(start + " to " + endingIndex);

       return maxLength;
   }

   public static void main(String[] args) {
    LargestSubArrayWithZeroesAndOnes sub = new LargestSubArrayWithZeroesAndOnes();
       int arr[] = {0, 0, 0, 1, 0, 1, 1};
       int n = arr.length;

       sub.maxLen(arr, n);
   }
}