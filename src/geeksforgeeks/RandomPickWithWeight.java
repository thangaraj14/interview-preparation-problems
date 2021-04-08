package geeksforgeeks;

import java.util.stream.IntStream;

/**
 * https://leetcode.com/problems/random-pick-with-weight/
 */
class RandomPickWithWeight {
    private int sum;
    private int[] sumArr;

    public RandomPickWithWeight(int[] w) {
        sum = 0;
        sumArr = new int[w.length];
        for (int i = 0; i < w.length; ++i) {
            sum += w[i];
            sumArr[i] = sum;
        }
    }

    public int pickIndex() {
        int idx = (int) (Math.random() * sum);
        return binarySearch(idx + 1); // +1 is because the rand will lie between 0-14
    }

    public int binarySearch(int idx) {
        int left = 0;
        int right = sumArr.length - 1;

        while (left < right) {
            int mid = ((right - left) / 2) + left;
            if (sumArr[mid] < idx) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        RandomPickWithWeight randomPickWithWeight = new RandomPickWithWeight(new int[]{1, 3, 5, 4, 2});
               randomPickWithWeight.pickIndex();

        //IntStream.range(0, 10).forEach(i -> System.out.println((int) (Math.random() * 3)));
    }

}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */