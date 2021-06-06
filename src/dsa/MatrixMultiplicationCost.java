package dsa;

/**
 * http://www.geeksforgeeks.org/dynamic-programming-set-8-matrix-chain-multiplication/
 * https://www.youtube.com/watch?v=vgLJZMUfnsU&t=316s
 */
public class MatrixMultiplicationCost {

    public int findCost(int[] arr) {
        int[][] T = new int[arr.length][arr.length];
        int q;
        for (int l = 2; l < arr.length; l++) {
            for (int i = 0; i < arr.length - l; i++) {
                int j = i + l;
                T[i][j] = 1000000;
                for (int k = i + 1; k < j; k++) {
                    System.out.println(
                            "T[" + i + "][" + j + "] :: T[" + i + "][" + k + "] * T[" + k + "][" + j + "] *" + arr[i]
                                    + "*" + arr[k] + "*" + arr[j]);
                    q = T[i][k] + T[k][j] + arr[i] * arr[k] * arr[j];
                    if (q < T[i][j]) {
                        T[i][j] = q;
                    }
                }
            }
        }
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(T[i][j] + " ");
            }
            System.out.println();
        }

        return T[0][arr.length - 1];
    }

    public static void main(String[] args) {
        MatrixMultiplicationCost mmc = new MatrixMultiplicationCost();
        int[] arr = { 2, 3, 6, 4, 5 };
        int cost = mmc.findCost(arr);
        System.out.print(cost);
    }
}