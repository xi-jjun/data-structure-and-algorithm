package algorithm.baekjoon.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// baekJoon 14496 silver1 - 그대, 그머가 되어
public class S14496 {
    static int start, end;
    static int N, M;
    static List<List<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        int answer = bfs();
        System.out.println(answer);
    }

    private static int bfs() {
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();

        visited[start] = true;
        pq.offer(new Node(start, 0));

        if (start == end) {
            return 0;
        }

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int node = curr.index;
            int cnt = curr.count;

            for (int i = 0; i < graph.get(node).size(); i++) {
                Integer next = graph.get(node).get(i);
                if (!visited[next]) {
                    visited[next] = true;
                    if (next == end) {
                        return cnt + 1;
                    }
                    pq.offer(new Node(next, cnt + 1));
                }
            }
        }
        return -1;
    }

    static class Node implements Comparable<Node> {
        int index;
        int count;

        public Node(int index, int count) {
            this.index = index;
            this.count = count;
        }

        @Override
        public int compareTo(Node o) {
            return this.count - o.count;
        }
    }
}
