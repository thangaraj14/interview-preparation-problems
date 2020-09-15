package leetcode;

/**
 * Input: [[1,2], [1,3], [2,3]]
 * Output: [2,3]
 * Explanation: The given undirected graph will be like this:
 *   1
 *  / \
 * 2 - 3
 *
 * Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
 * Output: [1,4]
 * Explanation: The given undirected graph will be like this:
 * 5 - 1 - 2
 *     |   |
 *     4 - 3
 */
public class RedundantConnection {
    public int[] findRedundantConnection(int[][] edges) {
        DisjointSet set= new DisjointSet(edges.length);

        for(int[]edge: edges){
            if(!set.union(edge[0]-1, edge[1]-1)) return edge;
        }
        return new int[]{-1,-1};
    }

    static class DisjointSet{
        int[] parent;
        int[] rank;

        public DisjointSet(int n){
            this.parent= new int[n];
            this.rank= new int[n];
        }

        public int find(int x){
            if(parent[x]==0) return x;
            return parent[x]=find(parent[x]);
        }

        public boolean union(int x, int y){
            int rootX= find(x);
            int rootY= find(y);

            if(rootX==rootY) return false;

            if(rank[rootX]<rank[rootY]){
                parent[rootX]=rootY;
            }else if(rank[rootY]<rank[rootX]){
                parent[rootY]=rootX;
            }else{
                parent[rootX]=rootY;
                rank[rootY]++;
            }
            return true;
        }
    }
}
