package floyd_warshall;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// baekJoon 11404 Gold4 플로이드
public class G11404 {
	static final int INF = 10000000;
	static int n, m;
	static int[][] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		graph = new int[n + 1][n + 1];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n; j++) {
//				if (i == j || i == 0 || j == 0) continue;
				graph[i][j] = INF;
			}
		}

		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			if (c < graph[a][b]) {
				graph[a][b] = c;
			}
		}

		floyd();

		printGraph(bw);

		bw.flush();
		bw.close();
	}

	private static void printGraph(BufferedWriter bw) throws IOException {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				bw.write((graph[i][j] == INF ? 0 : graph[i][j]) + " ");
			}
			bw.write("\n");
		}
	}

	private static void floyd() {
		for (int i = 1; i <= n; i++) {
			for (int s = 1; s <= n; s++) {
				for (int e = 1; e <= n; e++) {
					if (s == e) continue;
					if (graph[s][i] + graph[i][e] < graph[s][e]) {
						graph[s][e] = graph[s][i] + graph[i][e];
					}
				}
			}
		}
	}
}
