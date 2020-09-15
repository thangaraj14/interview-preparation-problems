package dynamicProgramming.fibonacci;

/**
 * https://www.youtube.com/watch?v=YcJTyrG3bZs
 * https://www.geeksforgeeks.org/count-possible-decodings-given-digit-sequence/
 *
 */
public class DecodeWays {

	public int numDecodings(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int n = s.length();
		int[] dp = new int[n + 1];
		dp[0] = 1; // initialize the base case means an empty string will have one way to decode
		dp[1] = s.charAt(0) != '0' ? 1 : 0; // means the way to decode a string of size 1
		for (int i = 2; i <= n; i++) {
			int first = Integer.parseInt(s.substring(i - 1, i));
			int second = Integer.parseInt(s.substring(i - 2, i));
			if (first >= 1 && first <= 9) {
				dp[i] += dp[i - 1];
			}
			if (second >= 10 && second <= 26) {
				dp[i] += dp[i - 2];
			}
		}
		return dp[n];
	}
	public int numDecodings1(String s) {
		if(s==null|| s.length()==0) return 0;
		Integer [] cache= new Integer[s.length()+1];
		return helperFn(s,0,cache);
		// cache[s.length()];

	}

	public int helperFn(String s, int index, Integer[] cache){
		if( index>=s.length()) return 1;

		if(cache[index]!=null) return cache[index];
		int total=0;
		if(index+1<=s.length()){
			String temp1= s.substring(index,index+1);
			if(valid(temp1)){
				total+=helperFn(s,index+1,cache);
			}
		}

		if(index+2 <= s.length()){
			String temp2= s.substring(index, index+2);
			if(valid(temp2)){
				total+=helperFn(s,index+2,cache);
			}
		}

		cache[index]=total;
		return cache[index];

	}

	public boolean valid(String s1){
		if(s1.length()==0) return false;
		if(s1.charAt(0)=='0') return false;

		int val=Integer.parseInt(s1);

		return val>=1 && val<=26;
	}
	public static void main(String[] args) {
		DecodeWays decode = new DecodeWays();
		System.out.println(decode.numDecodings("1210"));
	}
}
