package graph.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Input: N = 3, connections = [[1,2,5],[1,3,6],[2,3,1]]
 * Output: 6
 * Explanation:
 * Choosing any 2 edges will connect all cities so we choose the minimum 2.
 */
public class ConnectCities {
    public int solve(int A, ArrayList<ArrayList<Integer>> B) {

        // greedy-ly we looking from low cost roads to minimise the cost
        Collections.sort(B,((a, b)->Integer.compare(a.get(2),b.get(2))));

        UnionSet us= new UnionSet(A);
        int result=0;
        for(List<Integer> row:B){
            if(us.union(row.get(0),row.get(1))) result+=row.get(2);
        }
        return result;
    }

    static class UnionSet{
        int[] parent;
        int [] rank;
        int count;
        public UnionSet(int n){
            this.count=count;
            this.parent= new int[n+1];
            this.rank=new int[n+1];
            for(int i=0;i<=n;i++){
                this.parent[i]=i;
                this.rank[i]=1;
            }
        }

        public int find(int n){
            if(parent[n]==n) return n;
            parent[n]= find(parent[n]);
            return parent[n];
        }

        public boolean union(int x, int y){
            int rootX= find(x);
            int rootY= find(y);
            if(rootX==rootY) return false;

            if(rank[rootX]<rank[rootY]){
                parent[rootX]=rootY;
            }else if(rank[rootX]>rank[rootY]){
                parent[rootY]=rootX;
            }else{
                parent[rootY]=rootX;
                rank[rootX]++;
            }
            count--;
            return true;
        }
    }
}
