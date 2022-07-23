package graph.leetcode;

// similar to number of Islands
public class FriendCircles {
    public int findCircleNum(int[][] M) {
        UnionFind uf= new UnionFind(M.length);
        for(int i=0;i<M.length;i++){
            for(int j=0;j<M[0].length;j++){
                if(M[i][j]==1){
                    uf.union(i,j);
                }
            }
        }
        return uf.getCount();
    }

    static class UnionFind{
        int[] parent;
        int [] rank;
        int count;

        public UnionFind(int n){
            parent= new int[n];
            rank= new int[n];
            count=n;
            for(int i=0; i<n;i++){
                parent[i]=i;
            }
        }

        public int find(int n){
            if(parent[n]==n) return n;
            parent[n]=find(parent[n]);
            return parent[n];
        }

        public void union(int x, int y){
            int rootX= find(x);
            int rootY= find(y);

            if(rootX==rootY) return;

            if(rank[rootX]>rank[rootY]){
                parent[rootY]=rootX;
            }else if(rank[rootY]>rank[rootX]){
                parent[rootX]=rootY;
            }else{
                parent[rootY]=rootX;
                rank[rootX]++;
            }
            count--;
        }

        public int getCount(){
            return count;
        }
    }
}
