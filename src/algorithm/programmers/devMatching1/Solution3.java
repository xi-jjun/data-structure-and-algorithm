package algorithm.programmers.devMatching1;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Solution3 {
	static boolean[] isVisited;
	static boolean[] needNodes;
	static Set<Edge> pair = new HashSet<>();
	static List<List<Integer>> graph = new ArrayList<>();

	public static void main(String[] args) {
		int n = 8;
		int[][] edges = {{0, 1}, {1, 2}, {2, 3}, {4, 0}, {5, 1}, {6, 1}, {7, 2}, {7, 3}, {4, 5}, {5, 6}, {6, 7}};
		int k = 4;
		int a = 0;
		int b = 3;

		int answer = solution(n, edges, k, a, b);
		System.out.println("answer = " + answer);
	}

	public static void bfs(int startNodeNumber, int endNodeNumber, int limit) {
		Queue<Node> q = new ArrayDeque<>();
		q.offer(new Node(startNodeNumber, 0, new ArrayList<>()));

		while (!q.isEmpty()) {
			Node curr = q.poll();
			int currNode = curr.nodeNumber;
			int currCount = curr.count;
			List<Integer> currNodes = new ArrayList<>(curr.nodes);
			if (currNode >= limit) continue;

			for (int i = 0; i < graph.get(currNode).size(); i++) {
				int nextNodeNumber = graph.get(currNode).get(i);

				if (nextNodeNumber == endNodeNumber) {
					// node 구간 표시
					List<Integer> checkableNodes = curr.nodes;
					for (Integer checkableNode : checkableNodes) {
						needNodes[checkableNode] = true;
					}
					for (int j = 0; j < checkableNodes.size() - 1; j++) {
						pair.add(new Edge(checkableNodes.get(j), checkableNodes.get(j + 1)));
					}
					System.out.println("need : " + checkableNodes);
					System.out.println(currCount);
					continue;
				}

				if (!isVisited[nextNodeNumber]) {
					q.offer(new Node(nextNodeNumber, currCount + 1, currNodes));
				}
			}
		}
	}

	/**
	 * 통과는 못했지만 끝나고 쓰는 글
	 * 원래 하려고 했던 의도 : 처음 출발지점 노드에서 목적지점까지 dfs 탐색하여
	 * 						k 이내로 갈 수 있는 모든 경로에 대해 visited 표시
	 * 중간에 dfs 가 한줄을 탐색하면 끝나버리기에 bfs 로 바꿈.
	 * 여기서 꼬인 듯 하다...
	 * @param n
	 * @param edges
	 * @param k
	 * @param a
	 * @param b
	 * @return
	 */
	public static int solution(int n, int[][] edges, int k, int a, int b) {
		int answer = -1;
		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < edges.length; i++) {
			int start = edges[i][0];
			int end = edges[i][1];

			graph.get(start).add(end);
			graph.get(end).add(start);
		}

		isVisited = new boolean[n];
		needNodes = new boolean[n];
		bfs(a, b, k);

		int idx = 0;
		List<Integer> ans = new ArrayList<>();
		for (boolean needNode : needNodes) {
			if (!needNode) ans.add(idx);
			idx++;
		}

		return answer;
	}

	static class Edge {
		int start;
		int end;

		public Edge(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	static class Node {
		int nodeNumber;
		int count;
		List<Integer> nodes = new ArrayList<>();

		public Node(int nodeNumber, int count, List<Integer> nodes) {
			this.nodeNumber = nodeNumber;
			this.count = count;
			this.nodes = nodes;
			this.nodes.add(nodeNumber);
		}
	}
}
