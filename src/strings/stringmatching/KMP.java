package strings.stringmatching;

import java.util.Arrays;

public class KMP {

	public static void main(String[] args) {
		String given = "abxabcabcabyasd";
		String pattern = "abcaby";
		int[] arr = formPrefixAndSuffixArray(pattern);
		System.out.println(Arrays.toString(arr));
		System.out.println(kmp(arr, given.toCharArray(), pattern.toCharArray()));
	}

	private static boolean kmp(int[] arr, char[] charArray, char[] cs) {

		int j = 0;
		int i = 0;
		for (; j < cs.length && i < charArray.length;) {
			if (cs[j] == charArray[i]) {
				j++;
				i++;
			} else {
				if (j != 0) {
					j = arr[j - 1];
				} else {
					i++;
				}
			}
		}

		if (j == arr.length) {
			return true;
		}

		return false;
	}

	private static int[] formPrefixAndSuffixArray(String pattern) {

		char[] arr = pattern.toCharArray();
		int value = 0;
		int[] valueArr = new int[arr.length];
		valueArr[0] = 0;
		for (int i = 1; i < arr.length;) {
			if (arr[value] == arr[i]) {
				valueArr[i] = ++value;
				i++;
			} else {
				if (value != 0) {
					value = valueArr[value - 1];
				} else {
					valueArr[i] = 0;
					i++;
				}
			}
		}
		return valueArr;
	}

}
