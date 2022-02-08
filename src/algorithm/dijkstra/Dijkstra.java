package dijkstra;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {
	static final int INF = Integer.MAX_VALUE;

	static int[] minDistances;
	static int[][] sample = {
			{0, 0, 0, 0, 0, 0, 0},
			{0, 0, 2, 5, 1, INF, INF},
			{0, 2, 0, 3, 2, INF, INF},
			{0, 5, 3, 0, 3, 1, 5},
			{0, 1, 2, 3, 0, 1, INF},
			{0, INF, INF, 1, 1, 0, 2},
			{0, INF, INF, 5, INF, 2, 0}
	};
	static List<List<Node>> map = new ArrayList<>();
//	static Node[] map;

	public static void main(String[] args) {
		int start = 1;
		int N = 6;
		minDistances = new int[N + 1];

		for (int i = 0; i < N + 1; i++) {
			map.add(new ArrayList<>());
		}

		for (int i = 0; i < N + 1; i++) {
			minDistances[i] = INF;
		}

		map.get(1).add(new Node(2, 2));
		map.get(1).add(new Node(3, 5));
		map.get(1).add(new Node(4, 1));

		map.get(2).add(new Node(1, 2));
		map.get(2).add(new Node(3, 3));
		map.get(2).add(new Node(4, 2));

		map.get(3).add(new Node(1, 5));
		map.get(3).add(new Node(2, 3));
		map.get(3).add(new Node(4, 3));
		map.get(3).add(new Node(5, 1));
		map.get(3).add(new Node(6, 5));

		map.get(4).add(new Node(1, 1));
		map.get(4).add(new Node(2, 2));
		map.get(4).add(new Node(3, 3));
		map.get(4).add(new Node(5, 1));

		map.get(5).add(new Node(3, 1));
		map.get(5).add(new Node(4, 1));
		map.get(5).add(new Node(6, 2));

		map.get(6).add(new Node(3, 5));
		map.get(6).add(new Node(5, 2));

		dijkstra(start);

		// start → 모든 정점으로의 최단 경로 출력
		for (int minDistance : minDistances) {
			System.out.print(minDistance + " ");
		}
	}

	private static void dijkstra(int start) {
		minDistances[start] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(o -> o.distance));
		pq.offer(new Node(start, 0));

		while (!pq.isEmpty()) {
			// start → via
			Node viaNode = pq.poll();
			int via = viaNode.number;
			int viaDistance = viaNode.distance;

			if (minDistances[via] < viaDistance) continue;
			for (int i = 0; i < map.get(via).size(); i++) {
				// via → dest
				Node destNode = map.get(via).get(i);
				int dest = destNode.number;
				int destDistance = destNode.distance;

				// new = start → via → dest
				int newDistance = viaDistance + destDistance;

				if (minDistances[dest] > newDistance) {
					minDistances[dest] = newDistance;
					pq.offer(new Node(dest, newDistance));
				}
			}
		}
	}

	static class Node {
		int number;
		int distance;

		public Node(int number, int distance) {
			this.number = number;
			this.distance = distance;
		}
	}
}
