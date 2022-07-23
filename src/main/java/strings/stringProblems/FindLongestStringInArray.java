package strings.stringProblems;

import java.util.Arrays;
import java.util.Comparator;

public class FindLongestStringInArray {

	public static void main(String[] args) {
		String arr[] = { "geeks", "for", "geeksfor", "practiceproblems"};
		StringComparator str = new StringComparator();
		Arrays.sort(arr, str);
		System.out.println(Arrays.toString(arr));
	}
}

class StringComparator implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		if (o1.length() > o2.length()) {
			return -1;
		}
		return 0;
	}

}