package floydwarshall;

public class FloydWarshall {
	static final int INF = 1000000000;

//	static int[][] sample = {
//			{0, 0, 0, 0, 0, 0, 0},
//			{0, 0, 2, 5, 1, INF, INF},
//			{0, 2, 0, 3, 2, INF, INF},
//			{0, 5, 3, 0, 3, 1, 5},
//			{0, 1, 2, 3, 0, 1, INF},
//			{0, INF, INF, 1, 1, 0, 2},
//			{0, INF, INF, 5, INF, 2, 0}
//	};

	static int[][] sample = {
			{0, 0, 0, 0, 0},
			{0, 0, 5, INF, 8},
			{0, 7, 0, 9, INF},
			{0, 2, INF, 0, 4},
			{0, INF, INF, 3, 0}
	};
	static int N = sample.length;

	static void floyd() {
		// time complexity O(N^3)
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < N; j++) {
				for (int k = 1; k < N; k++) {
					if (sample[j][i] + sample[i][k] < sample[j][k]) {
						sample[j][k] = sample[j][i] + sample[i][k];
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("=== START ===");
		showGraph();

		/**
		 * 모든 정점 → 모든 정점으로의 최단거리를 알 수 있는 Floyd-Warshall Algorithm
		 *
		 * 매번 거쳐가는 정점을 기준으로 최단경로 table update
		 */
		System.out.println("\n=== Floyd-Warshall ===");
		floyd();
		showGraph();
	}

	private static void showGraph() {
		for (int[] row : sample) {
			for (int element : row) {
				System.out.print(element + " ");
			}
			System.out.println();
		}
	}
}
