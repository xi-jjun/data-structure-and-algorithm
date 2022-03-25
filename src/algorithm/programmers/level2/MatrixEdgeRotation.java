package algorithm.programmers.level2;

public class MatrixEdgeRotation {
	public static void main(String[] args) {
		int rows = 6;
		int cols = 6;
		int[][] queries = {
				{2, 2, 5, 4},
				{3, 3, 6, 6},
				{5, 1, 6, 3}
		};
		int[] answer = solution(rows, cols, queries);
		for (int i : answer) {
			System.out.println("i = " + i);
		}
	}

	public static int[] solution(int rows, int columns, int[][] queries) {
		int idx = 0;
		int[] answer = new int[queries.length];
		int[][] matrix = createMatrix(rows, columns);
		for (int[] query : queries) {
			int min;
			int x1 = query[0] - 1;
			int y1 = query[1] - 1;
			int x2 = query[2] - 1;
			int y2 = query[3] - 1;

			int leftUp = matrix[x1][y1];
			int leftBottom = matrix[x2][y1];
			min = Math.min(leftBottom, leftUp);
			int rightUp = matrix[x1][y2];
			min = Math.min(min, rightUp);
			int rightBottom = matrix[x2][y2];
			min = Math.min(min, rightBottom);

			// 왼쪽 edge 부터 옮기기
			for (int i = x1; i < x2 - 1; i++) {
				min = Math.min(min, matrix[i + 1][y1]);
				matrix[i][y1] = matrix[i + 1][y1];
			}
			matrix[x2 - 1][y1] = leftBottom;

			// 아래 edge 옮기기
			for (int i = y1; i < y2 - 1; i++) {
				min = Math.min(min, matrix[x2][i + 1]);
				matrix[x2][i] = matrix[x2][i + 1];
			}
			matrix[x2][y2 - 1] = rightBottom;

			// 오른쪽 edge 옮기기
			for (int i = x2; i >= x1 + 2; i--) {
				min = Math.min(min, matrix[i - 1][y2]);
				matrix[i][y2] = matrix[i - 1][y2];
			}
			matrix[x1 + 1][y2] = rightUp;

			// 위쪽 edge 옮기기
			for (int i = y2; i >= y1 + 2; i--) {
				min = Math.min(min, matrix[x1][i - 1]);
				matrix[x1][i] = matrix[x1][i - 1];
			}
			matrix[x1][y1 + 1] = leftUp;

			answer[idx++] = min;
		}
		return answer;
	}

	private static int[][] createMatrix(int rows, int columns) {
		int seq = 1;
		int[][] matrix = new int[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				matrix[i][j] = seq++;
			}
		}
		return matrix;
	}
}
