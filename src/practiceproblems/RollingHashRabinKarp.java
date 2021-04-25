package practiceproblems;

public class RollingHashRabinKarp{
    /**
     * the normal way to calculate an hash from a string would be through the following method
     * for String S="abcdef" we chose a prime number which is greater that the no.of chars(26)
     * we choose P=31
     * 
     * total hash(abcdef)= (a*P^0+ b*P^1 + c*P^2 + d*P^3 + e*P^4 + f*P^5) % M;
     * %(mod) is taken to avoid overflow of values
     *  hash at each point is [h0, h1, h2, h3, h4, h5]
     * 
     * this is anlogus to prefix sum, the hash at each index i is hash calculated from 0 to 'i'
     * 
     * for h2 is hash from (a-c), in order to calculate prefix sum from index 2-4
     * 
     * sum(2-4)= sum(4)-sum(2) [sum 0-4 - sum 0-2], likewise we can calulate rolling hash
     * 
     * in order to find contains subString for String size M and N, the time complexity would be O(MN)
     * 
     * if we find the hash of String M and N seperately and somehow find if substring of size N in String M
     * hashes to hash(N) we will have our result in O(M)
     * 
     * M= SDESKILLS
     * N= SKILLS
     * Note: p0 or p1= P^number
     * 
     * hash of M= [(S*p0)+(D*p1)+(E*p2)+(S*p4)+(K*p5)+(I*p6)+(L*p7)+(L*p8)+(S*p9)] % M
     * 
     * in hashing no matter how many times/ at what posistion a string comes, it has to hash to same value
     * 
     * if we have a hash(abcdef)= a*p0+ b*p1+ c*p2+ d*p3+ e*p4+ f*p5
     * and i need hash(cde) i need to do prefix sum analogy
     * hash(cde)= hash(R)-hash(L-1) R= Right, L=Left
     * but the above equation comes down to [c*p2 + d*p3+ e*p4] this is not going to yeild correct
     * result for cde every time if 'cde' occurs at different index then the eq would be [c*p8+d*p9+e*p10]
     * 
     * so what we need is to add one more part to the eq ([c*p2 + d*p3+ e*p4]/ p2) => [c*p0 + d*p1+ e*p2]
     * hash(cde)= hash(R)-hash(L-1)/P^L R= Right, L=Left
     * 
     * Hash[3,6]= (Hash(6)- Hash(2)/ p^3)%M
     * 
     * hash([L...R]) =(hash[R] - hash[L - 1]/PL)% M
       hash(s[L...R]) =(hash[R] - hash[L - 1]) * P^-L % M

       hash(s[L...R]) =(hash[R] - hash[L - 1])*(P^-1)L % M
       hash(s[L...R]) =(hash[R] - hash[L - 1])% M*(P-1)L %M% M

       (A * B)  % M =( (A % M) * (B % M)) % M
       X = P^-1= P^M-2% M
      hash(s[L...R]) =(((hash[R] - hash[L - 1])% M *X^L )% M)
     */


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