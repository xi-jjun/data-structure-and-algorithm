package algorithm.baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// baekJoon 17396 Gold5 - 백도어
public class G17396 {
    static final Long INF = Long.MAX_VALUE;
    static int N, M;
    static int[] view;
    static Long[] dp;
    static List<List<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        view = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; st.hasMoreTokens(); i++) {
            graph.add(new ArrayList<>());
            view[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            Long t = Long.parseLong(st.nextToken());
            if ((a == N - 1 || b == N - 1) || !(view[a] == 1 || view[b] == 1)) {
                graph.get(a).add(new Node(b, t));
                graph.get(b).add(new Node(a, t));
            }
        }

        dijkstra();
        System.out.println((dp[N - 1].equals(INF) ? -1 : dp[N - 1]));
    }

    private static void dijkstra() {
        boolean[] visited = new boolean[N];
        dp = new Long[N];
        Arrays.fill(dp, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>();

        dp[0] = 0L;
        pq.offer(new Node(0, 0L));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int to = curr.to;

            if (visited[to]) continue;

            visited[to] = true;
            for (Node nextNode : graph.get(to)) {
                int next = nextNode.to;
                Long weight = nextNode.weight;

                if (dp[next] >= dp[to] + weight) {
                    dp[next] = dp[to] + weight;
                    pq.offer(new Node(next, dp[next]));
                }
            }
        }
    }


    static class Node implements Comparable<Node> {
        int to;
        Long weight;

        public Node(int to, Long weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return (int) (this.weight - o.weight);
        }
    }
}
