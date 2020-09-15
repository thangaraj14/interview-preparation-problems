package stringProblems;

import java.util.Map;
import java.util.TreeMap;

public class PermutationAndCombination {

	public static void main(String args[]) {
		PermutationAndCombination sp = new PermutationAndCombination();
		sp.permute("ABC".toCharArray());

	}

	private void permute(char[] charArray) {
		Map<Character, Integer> map = new TreeMap<>();
		for (char ch : charArray) {
			map.compute(ch, (key, val) -> {
				if (val == null) {
					return 1;
				} else {
					return val + 1;
				}
			});
		}

		char[] str = new char[charArray.length];
		int[] count = new int[charArray.length];
		int index = 0;
		for (Map.Entry<Character, Integer> ch : map.entrySet()) {
			str[index] = ch.getKey();
			count[index] = ch.getValue();
			index++;
		}

		char[] result = new char[charArray.length];

		permutate(str, count, 0, result);
		System.out.println("++++++++++++++++");
		combination(str, count, 0, new char[charArray.length], 0);
		System.out.println("++++++++++++++++");
		permutationAndCombination(str, count, 0, result);

	}

	private void permutate(char[] str, int[] count, int level, char[] result) {
		if (level == result.length) {
			System.out.println(result);
			return;
		}

		for (int i = 0; i < str.length; i++) {
			if (count[i] != 0) {
				result[level] = str[i];
				count[i]--;
				permutate(str, count, level + 1, result);
				count[i]++;
			}
		}
	}

	private void print(char[] result, int level) {
		for (int i = 0; i < level; i++) {
			System.out.print(result[i] + " ");
		}
		System.out.println();
	}

	private void permutationAndCombination(char[] str, int[] count, int level, char[] result) {
		print(result, level);
		for (int i = 0; i < str.length; i++) {
			if (count[i] != 0) {
				result[level] = str[i];
				count[i]--;
				permutationAndCombination(str, count, level + 1, result);
				count[i]++;
			}
		}
	}

	private void combination(char[] str, int[] count, int level, char[] result, int pos) {
		print(result, level);
		for (int i = pos; i < str.length; i++) {
			if (count[i] != 0) {
				result[level] = str[i];
				count[i]--;
				combination(str, count, level + 1, result, i);
				count[i]++;
			}
		}
	}
}
