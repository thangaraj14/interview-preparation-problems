package geeksforgeeks;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class LargestPossibleNumber {
    public static void main(String[] args) {
        int nums[] = {10, 68, 97, 9, 21, 12};
        List<String> numbers = Arrays.asList("10", "68", "97", "9", "21", "12");

        Collections.sort(numbers, (a, b) -> (b + a).compareTo(a + b));
        numbers.stream().forEach(System.out::print);
        System.out.println(new LargestPossibleNumber().largestNumber(nums));
    }

    public String largestNumber(int[] nums) {
        String[] arr = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(arr,
                (a, b) -> {
                    return (b + a).compareTo(a + b);
                });

        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            sb.append(s);
        }

        while (sb.charAt(0) == '0' && sb.length() > 1)
            sb.deleteCharAt(0);

        return sb.toString();
    }
}