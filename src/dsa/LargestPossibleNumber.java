package dsa;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://www.geeksforgeeks.org/given-an-array-of-numbers-arrange-the-numbers-to-form-the-biggest-number/
 * <p>
 * 3,30,34,9
 * 1st iteration:
 * 330>303 no swap required
 * 334>343 swap
 * 34,30,3,9
 * 349>934 swap
 * 9,30,3,34
 * 2nd iteration:
 * 303>330 swap required
 * 9,3,30,34
 */
class LargestPossibleNumber {

    public static void main(String[] args) {
        int nums[] = { 10, 68, 97, 9, 21, 12 };
        List<String> numbers = Arrays.asList("10", "68", "97", "9", "21", "12");

        Collections.sort(numbers, (a, b) -> (b + a).compareTo(a + b));
        numbers.stream().forEach(System.out::print);
        System.out.println();
        System.out.println(new LargestPossibleNumber().largestNumber(nums));
    }

    public String largestNumber(int[] nums) {
        String[] arr = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(arr, (a, b) -> (b + a).compareTo(a + b));

        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            sb.append(s);
        }

        while (sb.charAt(0) == '0' && sb.length() > 1)
            sb.deleteCharAt(0);

        return sb.toString();
    }
}