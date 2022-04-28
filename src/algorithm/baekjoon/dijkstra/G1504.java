package algorithm.baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class G1504 {
    static final int INF = Integer.MAX_VALUE / 2 - 1;
    static int V, E;
    static List<List<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        for (int i = 0; i < V + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Node(end, weight));
            graph.get(end).add(new Node(start, weight));
        }

        st = new StringTokenizer(br.readLine());
        int via1 = Integer.parseInt(st.nextToken());
        int via2 = Integer.parseInt(st.nextToken());

        long t1 = dijkstra(1, via1);
        long t2 = dijkstra(via1, via2);
        long t3 = dijkstra(via2, V);

        long r1 = dijkstra(1, via2);
        long r2 = dijkstra(via2, via1);
        long r3 = dijkstra(via1, V);

        long result1 = t1 + t2 + t3;
        long result2 = r1 + r2 + r3;

        if (result1 < INF || result2 < INF) {
            System.out.println((Math.min(result1, result2)));
        } else {
            System.out.println("-1");
        }
    }

    private static long dijkstra(int start, int end) {
        boolean[] visited = new boolean[V + 1];
        long[] dp = new long[V + 1];
        Arrays.fill(dp, INF);
        dp[start] = 0L;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int current = curr.to;

            if (visited[current]) continue;

            visited[current] = true;
            for (Node nextNode : graph.get(current)) {
                int next = nextNode.to;
                long weight = nextNode.weight;

                if (dp[next] >= (long) (dp[current] + weight)) {
                    dp[next] = dp[current] + weight;
                    pq.offer(new Node(next, dp[next]));
                }
            }
        }

        return dp[end];
    }

    static class Node implements Comparable<Node> {
        int to;
        long weight;

        public Node(int to, long weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return (int) (this.weight - o.weight);
        }
    }
}
