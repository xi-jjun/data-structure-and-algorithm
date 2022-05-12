package algorithm.baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// baekJoon 16681 Gold2 등산
public class G16681 {
    static final long INF = 10000000000000L;
    static int N, M, D, E;
    static int[] heights;
    static boolean[] visited;
    static List<List<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        heights = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; st.hasMoreTokens(); i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, n));
            graph.get(b).add(new Node(a, n));
        }

        long[] asc = new long[N + 1];
        long[] des = new long[N + 1];

        dijkstra(1, asc);
        dijkstra(N, des);

        long answer = -INF;
        for (int node = 2; node < N; node++) {
            if (!(asc[node] == INF || des[node] == INF)) {
                answer = Math.max(answer, (long) heights[node] * E - (asc[node] + des[node]) * D);
            }
        }

        if (answer == -INF) {
            System.out.println("Impossible");
        } else {
            System.out.println(answer);
        }
    }

    private static void dijkstra(int start, long[] dp) {
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
                long tired = nextNode.tired;

                if (heights[now] < heights[next] && dp[next] >= dp[now] + tired) {
                    dp[next] = dp[now] + tired;
                    pq.offer(new Node(next, dp[next]));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int to;
        long tired;

        public Node(int to, long tired) {
            this.to = to;
            this.tired = tired;
        }

        @Override
        public int compareTo(Node o) {
            return (int) (this.tired - o.tired);
        }
    }
}
