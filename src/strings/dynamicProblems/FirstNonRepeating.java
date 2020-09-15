package dynamicProblems;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class FirstNonRepeating {

	public static void main(String[] args) {
		String data = "GeeksforGeeks";
		char[] arr = data.toCharArray();
		Queue<Character> stack = new LinkedList<Character>();
		for (char d : arr) {
			stack.add(d);
		}
		while (!stack.isEmpty()) {
			Character pop = stack.remove();
			if (!stack.contains(pop)) {
				System.out.println(pop);
			}
		}
		
		Integer[] arrVal = {23,87,5,36,90,65};
		Arrays.sort(arrVal,Collections.reverseOrder());
		
		for(Integer in:arrVal){
			System.out.print(in+" ");
		}
	}
	
	
	
}
