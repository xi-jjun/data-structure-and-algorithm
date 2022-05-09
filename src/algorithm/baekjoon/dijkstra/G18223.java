package algorithm.baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// baekJoon 18223 Gold4 - 민준이와 마산 그리고 건우
public class G18223 {
    static final int INF = 500000000;
    static int V, E, P;
    static List<List<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        for (int i = 0; i < V + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, cost));
            graph.get(b).add(new Node(a, cost));
        }

        int total = dijkstra(1, V);
        int startToP = dijkstra(1, P);
        int pToEnd = dijkstra(P, V);

        if (total >= startToP + pToEnd) {
            System.out.println("SAVE HIM");
        } else {
            System.out.println("GOOD BYE");
        }
    }

    private static int dijkstra(int start, int end) {
        boolean[] visited = new boolean[V + 1];
        int[] dp = new int[V + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Arrays.fill(dp, INF);
        dp[start] = 0;
        pq.offer(new Node(start, dp[start]));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int now = curr.to;

            if (visited[now]) continue;

            visited[now] = true;
            for (Node nextNode : graph.get(now)) {
                int next = nextNode.to;
                int cost = nextNode.cost;

                if (dp[next] >= dp[now] + cost) {
                    dp[next] = dp[now] + cost;
                    pq.offer(new Node(next, dp[next]));
                }
            }
        }

        return dp[end];
    }

    static class Node implements Comparable<Node> {
        int to;
        int cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
