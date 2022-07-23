package practiceproblems;

/**
 * https://leetcode.com/problems/random-pick-with-weight/
 *
 * tricky
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

    /**
     * Alternate example to understand the solution:
     *
     * So lets relate this problem with a problem of dividing a cake at a party
     * such that the person with highest weight has better chance to pick a slice.(proportional to his weight)
     *
     * Suppose we have people with weight 1, 3, 5, 7, 9 pounds, and they went for a party to a bakery and purchased a cake.
     *
     * They decided that lets add our total weight and buy a cake proportional to it. So their totalWeight came as
     * 1+3+5+7+9 = 25
     * They purchased a 25 pound cake :).
     * They decided that lets cut cake in 1 pound slices(25 slices total) and whoever wants to eat can take a 1 pound slice at a time.
     * The person who will pick a slice will be picked randomly.
     *
     * To find out how to pick randomly and to figure out who will pick first, they first did a cumulative sum of their weights
     *
     * 1->1
     * 3-> 1 + 3 = 4
     * 5-> 4 + 5 = 9
     * 7-> 7 + 9 = 16
     * 9-> 9 + 16 = 25
     *
     * =1,4,9,16,25
     *
     * And then asked the server to pick a random number out of 25 for them. The random number represents a slice.
     * So it can be 17 out of 25 or 6 out of 25.
     *
     * So the slice 1 belongs to 1 pounds person. Which is only 1 slice.
     * Slice between 2-4 belong to 3 pounds person. Which are 3 slices.
     * .
     * .
     * Slice between 17- 25 belong to the 9 pounds person. Which are 9 slices.
     *
     * If we pick a random slice out of 25,
     * there is a higher probability that it will belong to a person with 9 slices (the person with 9 pounds) ,
     * the person who own 7 slices has second highest probability.
     * The person whose weight is 1 pound and has only 1 slice has least probability to pick a slice.
     *
     * And that's what we wanted.
     * We want to let the person with highest weight get a greater chance to pick a slice every time even though they are picked at random.
     *
     * The question can also be asked in reverse
     */
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

    }

}