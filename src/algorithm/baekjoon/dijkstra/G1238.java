package algorithm.baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class G1238 {
    static final int INF = 10000000;
    static int N, M, X;
    static boolean[] visited;
    static int[] dp;
    static int[] goBack;
    static List<List<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        dp = new int[N + 1];
        goBack = new int[N + 1];

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Node(end, time));
        }

        xToAll();

        int result = 0;
        for (int node = 1; node <= N; node++) {
            if (node != X) {
                result = Math.max(result, dijkstra(node) + goBack[node]);
            }
        }

        System.out.println(result);
    }

    private static void xToAll() {
        Arrays.fill(goBack, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>();

        goBack[X] = 0;
        pq.offer(new Node(X, goBack[X]));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int now = curr.to;

            if (visited[now]) continue;

            visited[now] = true;
            for (Node nextNode : graph.get(now)) {
                int next = nextNode.to;
                int time = nextNode.time;

                if (goBack[next] >= goBack[now] + time) {
                    goBack[next] = goBack[now] + time;
                    pq.offer(new Node(next, goBack[next]));
                }
            }
        }
    }

    private static int dijkstra(int start) {
        Arrays.fill(visited, false);
        Arrays.fill(dp, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>();

        dp[start] = 0;
        pq.offer(new Node(start, dp[start]));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int now = curr.to;

            if (visited[now]) continue;

            visited[now] = true;
            for (Node nextNode : graph.get(now)) {
                int next = nextNode.to;
                int time = nextNode.time;

                if (dp[next] >= dp[now] + time) {
                    dp[next] = dp[now] + time;
                    pq.offer(new Node(next, dp[next]));
                }
            }
        }

        return dp[X];
    }

    static class Node implements Comparable<Node> {
        int to;
        int time;

        public Node(int to, int time) {
            this.to = to;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }
}
