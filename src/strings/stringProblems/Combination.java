package stringProblems;

import java.util.*;

public class Combination {

	public void combination(char input[]) {
		Map<Character, Integer> countMap = new TreeMap<>();
		for (char ch : input) {
			countMap.compute(ch, (key, val) -> {
				if (val == null) {
					return 1;
				} else {
					return val + 1;
				}
			});
		}
		char str[] = new char[countMap.size()];
		int count[] = new int[countMap.size()];
		int index = 0;
		for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
			str[index] = entry.getKey();
			count[index] = entry.getValue();
			index++;
		}
		char[] output = new char[input.length];
		combination(str, count, 0, output, 0);
	}

	private void combination(char[] str, int[] count, int level, char[] result, int pos) {
		print(result, level);
		for (int i = pos; i < str.length; i++) {
			if (count[i] != 0) {
				result[level] = str[i];
				count[i]--;
				combination(str, count, level + 1, result, pos);
				count[i]++;
			}
		}
	}

	private void print(char result[], int pos) {
		for (int i = 0; i < pos; i++) {
			System.out.print(result[i] + " ");
		}
		System.out.println();
	}

	public static void main(String args[]) {
		Combination c = new Combination();
		c.combination("abc".toCharArray());
	}

}