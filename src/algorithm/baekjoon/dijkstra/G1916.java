package algorithm.baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// baekJoon Gold5 1916 - 최소 비용 구하기
public class G1916 {
    static final int INF = 100001;
    static int N, M;
    static int[] dp;
    static List<List<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(A).add(new Node(B, weight));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start);
        System.out.println(dp[end]);
    }

    private static void dijkstra(int start) {
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();

        dp[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node currNode = pq.poll();
            int to = currNode.number;

            if (visited[to]) continue;

            visited[to] = true;
            for (Node node : graph.get(to)) {
                int next = node.number;
                if (dp[next] >= dp[to] + node.fee) {
                    dp[next] = dp[to] + node.fee;
                    pq.offer(new Node(next, dp[next]));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int number;
        int fee;

        public Node(int number, int fee) {
            this.number = number;
            this.fee = fee;
        }

        @Override
        public int compareTo(Node o) {
            return this.fee - o.fee;
        }
    }
}
