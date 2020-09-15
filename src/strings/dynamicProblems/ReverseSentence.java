package dynamicProblems;

import java.util.StringTokenizer;

public class ReverseSentence {

	public static void main(String[] args) {
		String sentence = "This is a Career Monk String";
		StringTokenizer st = new StringTokenizer(sentence);
		String reverse = "";
		while (st.hasMoreTokens()) {
			reverse = st.nextToken() + " " + reverse;
		}
		System.out.println(reverse);
	}

}