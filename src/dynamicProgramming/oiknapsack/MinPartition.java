package dynamicProgramming.oiknapsack;

import java.util.HashMap;
import java.util.Map;

class MinPartition
{

	public static int minPartition(int[] S, int n, int S1, int S2,
								Map<String, Integer> lookup)
	{
		if (n < 0) {
			return Math.abs(S1 - S2);
		}

		// construct a unique map key from dynamic elements of the input
		// Note that can uniquely identify the subproblem with n & S1 only,
		// as S2 is nothing but S - S1 where S is sum of all elements
		String key = n + "|" + S1;

		// if sub-problem is seen for the first time, solve it and
		// store its result in a map
		if (!lookup.containsKey(key))
		{
			// Case 1. include current item in the subset S1 and recurse
			// for remaining items (n - 1)
			int inc = minPartition(S, n - 1, S1 + S[n], S2, lookup);

			// Case 2. exclude current item from subset S1 and recurse for
			// remaining items (n - 1)
			int exc = minPartition(S, n - 1, S1, S2 + S[n], lookup);

			lookup.put(key, Integer.min(inc, exc));
		}

		return lookup.get(key);
	}

	public static void main(String[] args)
	{
		int[] S = { 10, 20, 15, 5, 25 };
		Map<String, Integer> lookup = new HashMap<>();
		System.out.println("The minimum difference is "
				+ minPartition(S, S.length - 1, 0, 0, lookup));
	}
}