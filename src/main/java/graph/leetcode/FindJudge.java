package graph.leetcode;

/**
 * In a town, there are N people labelled from 1 to N.  There is a rumor that one of these people is secretly the town judge.
 *
 * If the town judge exists, then:
 *
 * The town judge trusts nobody.
 * Everybody (except for the town judge) trusts the town judge.
 * There is exactly one person that satisfies properties 1 and 2.
 * You are given trust, an array of pairs trust[i] = [a, b] representing that the person labelled a trusts the person labelled b.
 */
public class FindJudge {
    public int findJudge(int N, int[][] trust) {
        if(trust.length==0 && N==1) return 1;
        int[] inEdge= new int[N+1];
        int[] outEdge= new int[N+1];

        for(int[] val: trust){
            outEdge[val[0]]++;
            inEdge[val[1]]++;
        }

        for(int i=1;i<=N;i++){
            if(outEdge[i]==0 && inEdge[i]==N-1) return i;
        }

        return -1;
    }
}
