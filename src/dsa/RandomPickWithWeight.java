package dsa;

import java.util.stream.IntStream;

/**
 * https://leetcode.com/problems/random-pick-with-weight/
 */
class RandomPickWithWeight {
    private int sum;
    private int[] arr;

    public RandomPickWithWeight(int[] w) {
        arr = new int[w.length];
        for (int i = 0; i < w.length; ++i) {
            sum += w[i];
            arr[i] = sum;
        }
    }

    public int pickIndex() {
        int idx = (int) (Math.random() * sum);
        return binarySearch(idx + 1);
    }

    public int binarySearch(int idx) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int mid = ((right - left) / 2) + left;
            if (arr[mid] < idx) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 3 };
        RandomPickWithWeight randomPickWithWeight = new RandomPickWithWeight(arr);

        IntStream.range(0, 4).forEach(e -> System.out.println(arr[randomPickWithWeight.pickIndex()]));
    }

}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */