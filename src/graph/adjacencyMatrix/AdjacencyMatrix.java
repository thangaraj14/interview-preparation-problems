package adjacencyMatrix;

public class AdjacencyMatrix {

	public static void main(String[] args) {
		AdjacencyMatrix am = new AdjacencyMatrix();

		int[][] arr = new int[5][5];
		am.add(1, 2, arr);
		am.add(1, 5, arr);
		am.add(1, 4, arr);
		am.add(3, 2, arr);
		am.add(5, 2, arr);
		am.add(3, 4, arr);
		am.add(4, 5, arr);

		am.printMatrix(arr);
		am.removeLink(3, 4, arr);
		System.out.println("*******");
		am.printMatrix(arr);

	}

	private void removeLink(int i, int j, int[][] arr) {
		arr[i - 1][j - 1] = 0;
	}

	private void printMatrix(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}

	}

	private void add(int i, int j, int[][] arr) {
		arr[i - 1][j - 1] = 1;
	}

}
