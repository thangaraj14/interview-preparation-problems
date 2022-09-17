package practiceproblems;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {

    public int romanToInteger(String s) {
        Map<Character, Integer> map = new HashMap<>();

        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int sum = map.get(s.charAt(s.length() - 1));
        for (int i = s.length() - 2; i >= 0; --i) {
            if (map.get(s.charAt(i)) < map.get(s.charAt(i + 1))) {
                sum -= map.get(s.charAt(i));
            } else {
                sum += map.get(s.charAt(i));
            }
        }
        return sum;

    }

    public String intToRoman(int num) {

        String[] keys = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] values = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keys.length; i++) {

            if (num - values[i] >= 0) {
                while (num - values[i] >= 0) {
                    sb.append(keys[i]);
                    num -= values[i];
                }
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        new RomanToInteger().romanToInteger("LIX");
    }

}