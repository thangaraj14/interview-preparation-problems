package practiceproblems;

/**
 * revise
 */
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
     * this is analogous to prefix sum, the hash at each index i is hash calculated from 0 to 'i'
     *
     * for h2 is hash from (a-c), in order to calculate prefix sum from index 2-4
     *
     * sum(2-4)= sum(4)-sum(2) [sum 0-4 - sum 0-2], likewise we can calculate rolling hash
     *
     * in order to find contains subString for String size M and N, the time complexity would be O(MN)
     *
     * if we find the hash of String M and N separately and somehow find if substring of size N in String M
     * hashes to hash(N) we will have our result in O(M)
     *
     * M= SDESKILLS
     * N= SKILLS
     * Note: p0 or p1= P^number
     *
     * hash of M= [(S*p0)+(D*p1)+(E*p2)+(S*p4)+(K*p5)+(I*p6)+(L*p7)+(L*p8)+(S*p9)] % M
     *
     * in hashing no matter how many times/ at what position a string comes, it has to hash to same value
     *
     * if we have a hash(abcdef)= a*p0+ b*p1+ c*p2+ d*p3+ e*p4+ f*p5
     * and we need hash(cde) we need to do prefix-sum analogy
     * hash(cde)= hash(R)-hash(L-1) R= Right, L=Left
     * but the above equation comes down to [c*p2 + d*p3+ e*p4] this is not going to yield correct
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
       hash(s[L...R]) =(hash[R] - hash[L - 1])%M * (P-1)L%M

       (A * B)  % M =( (A % M) * (B % M)) % M
       X = P^-1= P^M-2% M
      hash(s[L...R]) =(((hash[R] - hash[L - 1])% M *X^L )% M)
     */


	private int prime = 101;

	public int patternSearch(char[] text, char[] pattern) {
		int m = pattern.length;
		int n = text.length;
		long patternHash = createHash(pattern, m - 1);
		long textHash = createHash(text, m - 1);
		for (int i = 1; i <= n - m + 1; i++) {
			if (patternHash == textHash && checkEqual(text, i - 1, i + m - 2, pattern, 0, m - 1)) {
				return i - 1;
			}
			if (i < n - m + 1) {
				textHash = recalculateHash(text, i - 1, i + m - 1, textHash, m);
			}
		}
		return -1;
	}

	private long recalculateHash(char[] str, int oldIndex, int newIndex, long oldHash, int patternLen) {
		long newHash = oldHash - str[oldIndex];
		newHash = newHash / prime;
		newHash += str[newIndex] * Math.pow(prime, patternLen - 1);
		return newHash;
	}

	private long createHash(char[] str, int end) {
		long hash = 0;
		for (int i = 0; i <= end; i++) {
			hash += str[i] * Math.pow(prime, i);
		}
		return hash;
	}

	private boolean checkEqual(char str1[], int start1, int end1, char str2[], int start2, int end2) {
		if (end1 - start1 != end2 - start2) {
			return false;
		}
		while (start1 <= end1 && start2 <= end2) {
			if (str1[start1] != str2[start2]) {
				return false;
			}
			start1++;
			start2++;
		}
		return true;
	}

	public static void main(String args[]) {
		RollingHashRabinKarp rks = new RollingHashRabinKarp();
		System.out.println(rks.patternSearch("TusharRoy".toCharArray(), "sharRoy".toCharArray()));
		System.out.println(rks.patternSearch("TusharRoy".toCharArray(), "Roy".toCharArray()));
		System.out.println(rks.patternSearch("TusharRoy".toCharArray(), "shas".toCharArray()));
		System.out.println(rks.patternSearch("TusharRoy".toCharArray(), "usha".toCharArray()));
		System.out.println(rks.patternSearch("TusharRoy".toCharArray(), "Tus".toCharArray()));
	}
}