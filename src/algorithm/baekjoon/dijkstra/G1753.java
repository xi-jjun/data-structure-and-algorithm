package dijkstra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// baekJoon 1753 Gold5 최단 경로
// https://sskl660.tistory.com/59 참고
public class G1753 {
	static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int V, E, K;
	static int u, v, w;
	static int[] distance;
	static ArrayList<ArrayList<Node>> graph = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		init();

		PriorityQueue<Node> q = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));

		q.offer(new Node(K, 0));
		distance[K] = 0;

		while (!q.isEmpty()) {
			Node currentNode = q.poll();
			if (distance[currentNode.dest] < currentNode.cost) {
				continue;
			}

			for (int i = 0; i < graph.get(currentNode.dest).size(); i++) {
				Node nextNode = graph.get(currentNode.dest).get(i);
				if (distance[nextNode.dest] > currentNode.cost + nextNode.cost) {
					distance[nextNode.dest] = currentNode.cost + nextNode.cost;
					q.offer(new Node(nextNode.dest, distance[nextNode.dest]));
				}
			}
		}

		printResult();

		bw.flush();
		bw.close();
	}

	private static void printResult() throws IOException {
		for (int i = 1; i <= V; i++) {
			bw.write((distance[i] == Integer.MAX_VALUE ? "INF" : distance[i]) + "\n");
		}
	}

	private static void init() throws IOException {
		String[] info = br.readLine().split(" ");
		V = Integer.parseInt(info[0]);
		E = Integer.parseInt(info[1]);
		K = Integer.parseInt(br.readLine());

		distance = new int[V + 1];

		for (int i = 0; i < V + 1; i++) {
			graph.add(new ArrayList<>());
		}

		makeGraph();
	}

	private static void makeGraph() throws IOException {
		for (int i = 0; i < E; i++) {
			String[] line = br.readLine().split(" ");
			u = Integer.parseInt(line[0]);
			v = Integer.parseInt(line[1]);
			w = Integer.parseInt(line[2]);
			graph.get(u).add(new Node(v, w));
		}

		Arrays.fill(distance, Integer.MAX_VALUE);
	}

	static class Node {
		final int dest;
		private int cost;

		public Node(int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}
	}
}
