package strings.stringmatching;

public class RabinKarp {

	public static void main(String[] args) {
		String givenValue = "abaaab";
		String input = "aaa";

//		char[] givenArr = givenValue.toCharArray();
//		char[] inputArr = input.toCharArray();
//
//		int inputHash = createHash(inputArr, inputArr.length);
//		int previousValue = 0;
//		for (int i = 0; i < (givenArr.length - inputArr.length + 1); i++) {
//			previousValue = givenHash(givenArr, i, previousValue);
//			if (inputHash == previousValue && matchString(inputArr, givenArr, i)) {
//				System.out.println("It exists");
//			}
//		}

		rabinKarp(givenValue, input);
	}

	private static int givenHash(char[] givenArr, int i, int previousValue) {

		if (previousValue == 0) {
			return createHash(givenArr, 3);
		}

		int currentValue = previousValue - givenArr[i - 1];
		System.out.println(currentValue + givenArr[i + 2] * 100);
		return currentValue + givenArr[i + 2] * 100;

	}

	private static int createHash(char[] inputArr, int length) {
		int hash = 0;
		double power = 0;
		for (int i = 0; i < length; i++) {
			hash = hash + (int) Math.pow(10, power++) * inputArr[i];
		}
		return hash;
	}

	private static boolean matchString(char[] inputArr, char[] givenArr, int i) {
		int index = 0;
		for (int j = i; j < inputArr.length; j++) {
			if (inputArr[index++] != givenArr[j]) {
				return false;
			}
		}
		return true;
	}

	//the time complexity is O(m + n)
	public static int rabinKarp(String t, String s) {
		if (s.length() > t.length()) {
			return -1; // s is not a substring of t.
		}
		final int BASE = 26;
		int tHash = 0, sHash = 0; // Hash codes for the substring of t and s.
		int powerS = 1; // this will be used to calculate the rolling hash when current window moves out
		for (int i = 0; i < s.length(); i++) {
			powerS = i > 0 ? powerS * BASE : 1;
			tHash = tHash * BASE + t.charAt(i);
			sHash = sHash * BASE + s.charAt(i);
		}
		for (int i = s.length(); i < t.length(); i++) {
// Checks the two substrings are actually equal or not, to protect
// against hash collision.
			if (tHash == sHash ){   //&& t.substring(i - s.length(), i).equals(s)) {
				return i - s.length(); // Found a match.
			}
// Uses rolling hash to compute the new hash code.
			tHash -= t.charAt(i - s.length()) * powerS;
			tHash = tHash * BASE + t.charAt(i);
		}
// Tries to match s and t.substring(t.length() - s.lengthO).
		if (tHash == sHash){ // && t .substring(t.length() - s.length()).equals(s)){
			return t.length() - s.length();
		}
		return -1;
	}
}
