package dynamicProgramming;

import java.util.ArrayList;
import java.util.List;

/**
 * Return all non-negative integers of length N such that the absolute difference between every two consecutive digits is K.
 *
 * Note that every number in the answer must not have leading zeros except for the number 0 itself. For example, 01 has one leading zero and is invalid, but 0 is valid.
 *
 * You may return the answer in any order.
 *
 * Input: N = 3, K = 7
 * Output: [181,292,707,818,929]
 * Explanation: Note that 070 is not a valid number, because it has leading zeroes.
 *
 * Input: N = 2, K = 1
 * Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
 */
public class NumberWithSameConsequtiveDifference {

    // the idea is to create a tree with dfs of depth N and in each step(level) we add and subract the K to
    // the last element and check if it falls under the boundray (0> && <10)
    // for N=3 and K=2
    //        1
    //  -K   / \ +K
    //    -2    3
    //         / \
    //        1   5 --> end of tree because N=3

    public int[] numsSameConsecDiff(int N, int K) {
        List<Integer> result= new ArrayList<>();
        if(N==1) result.add(0);

        for(int i=1;i<10;i++){
            dfs(N-1, K, result, i);
        }
        int[] ret = new int[result.size()];
        for(int i = 0;i < ret.length;i++)
            ret[i] = result.get(i);
        return ret;
    }

    public void dfs(int N, int K, List<Integer> result, int num){
        if(N==0) {
            result.add(num);
            return;
        }
        int last_digit= num%10;

        if(last_digit>=K) dfs(N-1,K,result,num*10+last_digit-K);
        if(K>0 && K+last_digit<10) dfs(N-1,K,result,num*10+last_digit+K);
    }


}
