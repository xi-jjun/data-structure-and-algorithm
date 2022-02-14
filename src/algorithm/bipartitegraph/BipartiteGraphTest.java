package algorithm.bipartitegraph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// baekJoon 1707 Gold4 이분 그래프
public class BipartiteGraphTest {
	static int V, E;
	static boolean ok;
	static final boolean RED = false;
	static boolean[] isVisited;
	static Map<Integer, Vertex> visitedVertices = new HashMap<>();
	static List<List<Integer>> graph = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int K = Integer.parseInt(br.readLine());

		for (int i = 0; i < K; i++) {
			String[] line = br.readLine().split(" ");
			V = Integer.parseInt(line[0]);
			E = Integer.parseInt(line[1]);
			visitedVertices = new HashMap<>();
			graph = new ArrayList<>();
			isVisited = new boolean[V + 1];
			ok = true;

			for (int j = 0; j < V + 1; j++) {
				graph.add(new ArrayList<>());
			}

			for (int j = 0; j < E; j++) {
				line = br.readLine().split(" ");
				int V1 = Integer.parseInt(line[0]);
				int V2 = Integer.parseInt(line[1]);

				graph.get(V1).add(V2);
				graph.get(V2).add(V1);
			}

			int idx = 1;
			while (!checkSearched() || idx <= V) {
				bfs(idx++);
			}

			if (ok) {
				bw.write("YES\n");
			} else {
				bw.write("NO\n");
			}
		}

		bw.flush();
		bw.close();
	}

	private static boolean checkSearched() {
		for (int i = 1; i < isVisited.length; i++) {
			if (!isVisited[i]) return false;
		}
		return true;
	}

	/**
	 * logic
	 *
	 * 1. source Vertex 에 RED color 할당
	 * 2. source Vertex 와 이웃한 Vertex 들에게 BLUE color 할당
	 * 3. source Vertex 와 이웃한 Vertex 의 이웃한 Vertex 들에게 RED color 할당...
	 * 4. 모든 Vertex 에 대해 색칠이 완료되도록 1~3 반복
	 * 5. 만약 4번을 하는 도중에 이웃한 Vertex 의 색이 같다면, 해당 그래프는 이분 그래프다 아니다. 바로 종료
	 * @param start
	 */
	private static void bfs(int start) {
		if (isVisited[start]) return;

		Queue<Vertex> q = new ArrayDeque<>();
		Vertex startVertex = new Vertex(start, RED);
		q.offer(startVertex);
		isVisited[start] = true;
		visitedVertices.put(start, startVertex);

		while (!q.isEmpty()) {
			Vertex currV = q.poll();
			int currNo = currV.no;
			boolean currColor = currV.color;
			isVisited[currNo] = true;

			for (int i = 0; i < graph.get(currNo).size(); i++) {
				int nextNo = graph.get(currNo).get(i);

				if (!isVisited[nextNo]) {
					isVisited[nextNo] = true;
					Vertex nextVertex = new Vertex(nextNo, !currColor);
					visitedVertices.put(nextNo, nextVertex);
					q.offer(nextVertex);
				} else if (visitedVertices.get(nextNo).color == currColor) {
					ok = false;
					return;
				}
			}
		}
	}

	static class Vertex {
		int no;
		boolean color;

		public Vertex(int no, boolean color) {
			this.no = no;
			this.color = color;
		}
	}
}

