package graph.disjoints;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankTransformMatrix {
    static class Position {
        int index;         // the index is just the index in a list of positions, it is needed for union-find
        int row;
        int column;

        Position(int index, int row, int column) {
            this.index = index;
            this.row = row;
            this.column = column;
        }
    }

    public int[][] matrixRankTransform(int[][] matrix) {

        int[][] ans = new int[matrix.length][matrix[0].length];
        int[] maxRankInCol = new int[matrix[0].length];
        int[] maxRankInRow = new int[matrix.length];
        Map<Integer, List<Position>> map = new HashMap<>();
        List<Integer> uniqueNumbers = new ArrayList<>();

        // populate map and the list of unique numbers
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++) {
                if (!map.containsKey(matrix[i][j])) uniqueNumbers.add(matrix[i][j]);
                map.putIfAbsent(matrix[i][j], new ArrayList<>());
                map.get(matrix[i][j]).add(new Position(map.get(matrix[i][j]).size(), i, j));
            }

        // sort the unique numbers to iterate from the smallest to the largest

        Collections.sort(uniqueNumbers);

        // For every unique number, we split the positions with this number into groups of non-adjacent ones using union-find.
        // Then for each group set the rank of the elements in the group to be (the largest rank among all the rows and columns
        // that contain elements of that group) + 1.
        // Then update max ranks of those rows and columns to be the new rank.

        for (int n : uniqueNumbers) {
            List<Position> positions = map.get(n);
            UnionFind uf = new UnionFind(positions.size());

            // union all the positions that have the same row or column
            // for this I sorted by row/column and took union of adjacent positions

            positions.sort(Comparator.comparingInt(p -> p.row));
            for (int i = 0; i < positions.size() - 1; i++)
                if (positions.get(i).row == positions.get(i + 1).row)
                    uf.union(positions.get(i).index, positions.get(i + 1).index);

            positions.sort(Comparator.comparingInt(p -> p.column));
            for (int i = 0; i < positions.size() - 1; i++)
                if (positions.get(i).column == positions.get(i + 1).column)
                    uf.union(positions.get(i).index, positions.get(i + 1).index);

            // for every group (positions with the same parent), make a separate entry in a map

            HashMap<Integer, List<Position>> posMap = new HashMap<>();
            for (Position position : positions) {
                int parent = uf.find(position.index);
                if (!posMap.containsKey(parent)) posMap.put(parent, new ArrayList<>());
                posMap.get(parent).add(position);
            }

            // for every list in our map (which are the groups), find the rank and update the ranks of rows/columns

            for (List<Position> list : posMap.values()) {

                // find max rank among rows/columns that contain elements of this group

                int max = 0;
                for (Position pos : list) {
                    max = Math.max(maxRankInRow[pos.row], max);
                    max = Math.max(maxRankInCol[pos.column], max);
                }

                // update maximums to the new rank

                int rank = max + 1;
                for (Position pos : list) {
                    ans[pos.row][pos.column] = rank;
                    maxRankInRow[pos.row] = rank;
                    maxRankInCol[pos.column] = rank;
                }
            }
        }

        return ans;
    }

    /***
     /* The rest is Union-Find class (rank + path compression)
     ***/

    private static class UnionFind {
        private int[] parent;
        private int[] rank;

        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        int find(int p) {
            if (p == parent[p]) return p;
            parent[p] = find(parent[p]);
            return parent[p];
        }

        void union(int p, int q) {
            int rootP = find(p), rootQ = find(q);
            if (rootP == rootQ) return;
            if (rank[rootP] < rank[rootQ])
                parent[rootP] = rootQ;
            else if (rank[rootP] > rank[rootQ])
                parent[rootQ] = rootP;
            else {
                parent[rootQ] = rootP;
                rank[rootP]++;
            }
        }
    }

    public static void main(String[] args) {
        new RankTransformMatrix().matrixRankTransform(new int[][]{  {20, -21, 14},
                                                                    {-19, 4, 19},
                                                                    {22, -47, 24},
                                                                    {-19, 4, 19}});
                                                     //new int[][]{{7,3,6},{1,4,5},{9,8,2}};
    }

}


