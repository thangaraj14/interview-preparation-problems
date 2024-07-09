package bitwise;
// https://leetcode.com/problems/single-number-ii/
// https://leetcode.com/problems/single-number-iii/
public class SingleNumberII {
    public int singleNumber(int[] nums) {
        int ans = 0;

        for (int i = 0; i < 32; ++i) {
            int sum = 0;
            for (final int num : nums) {
                // This step creates a bitmask pos where only the bit at
                // position i is set to the value of sum
                sum += num >> i & 1;
            }
            //Take the modulo of sum by 3: sum %= 3
            // This will clear the ith bit of ans if the corresponding bit of sum is 3.
            // It will leave it as 1 if the corresponding bit of sum is 1.
            sum %= 3;

            //Use the bitwise OR operation with ans and pos: ans |= pos.
            // This sets the corresponding bit in ans to 1
            // if the bit at position i is part of an unbalanced line.
            ans |= sum << i;
        }

        return ans;
    }

    /**
     * High level: find XOR combo of two result. Then find one of them
     * Step 1: XOR all numbers, the result will be res1 ^ res2
     * Step 2: traverse all 32 bit indexes of previous XOR result, once we find there exist 1 on a bit index, break
     * the loop. Because one of the result at least have bit 1 on current bit index
     * Step 3: traverse all numbers in the input array, if we find a number & the bit index we found at step 2 is not 0,
     * then we can use res1 XOR current num to iteratively fill out effective bit in res1
     * (i.e. res1 ^ n1 ^ n1 ^ n2 ^ n2 = res1, if res1, n1 and n2 have bit 1 on the bitIndex
     * Step 4: find another result number by using res1 ^ allNumberXOR (i.e. res1 ^ (res1 ^ res2 ^ ...)) = res2)
     * */

    public int[] singleNumberIII(int[] nums) {
        int res1 = 0;
        int res2 = 0;

        // step 1: find XOR combo of two numbers
        int allNumXOR = 0;
        for (int num : nums) {
            allNumXOR ^= num;
        }

        // step 2: find effective bit index of a number in one of two result numbers
        int bitIndex;
        for (bitIndex = 0; bitIndex < 32; bitIndex++) {
            if ((allNumXOR & (1 << bitIndex)) != 0) {
                break;
            }
        }

        // step 3: find first result
        for (int num : nums) {
            if ((num & (1 << bitIndex)) != 0) {
                // current bitIndex, we only want that single one,
                // and cancel all the rest of numbers by using XOR
                res1 ^= num;
            }else{
                res2 ^= num;
            }
        }
        return new int[]{res1, res2};
    }
}
