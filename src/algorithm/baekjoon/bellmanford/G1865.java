package algorithm.baekjoon.bellmanford;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class G1865 {
    static int INF = Integer.MAX_VALUE;
    static int N, M, W;
    static long[] dp;
    static boolean[] visited;
    static List<List<Node>> graph;
    static Queue<Integer> cycles;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        StringBuilder results = new StringBuilder();
        for (int i = 0; i < tc; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            for (int j = 0; j < N + 1; j++) {
                graph.add(new ArrayList<>());
            }

            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());
                graph.get(start).add(new Node(end, time));
                graph.get(end).add(new Node(start, time));
            }

            for (int j = 0; j < W; j++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());
                graph.get(start).add(new Node(end, -time));
            }


            cycles = new ArrayDeque<>();
            dp = new long[N + 1];
            visited = new boolean[N + 1];

            bellmanFord();

            if (bfs()) {
                results.append("YES\n");
            } else {
                results.append("NO\n");
            }
        }

        System.out.println(results);
    }

    private static boolean bfs() {
        while (!cycles.isEmpty()) {
            Integer now = cycles.poll();

            for (Node nextNode : graph.get(now)) {
                int next = nextNode.to;
                if (visited[next]) continue;

                visited[next] = true;
                cycles.offer(next);
            }
        }

        for (boolean visit : visited) {
            if (visit) return true;
        }
        return false;
    }

    private static void bellmanFord() {
        // dp[All] = 0 : 모두 동시에 출발
        for (int i = 0; i < N; i++) {
            for (int now = 1; now < N + 1; now++) {
                for (Node nextNode : graph.get(now)) {
                    if (dp[now] == INF) continue;

                    int next = nextNode.to;
                    long time = nextNode.time;

                    long newTime = dp[now] + time;
                    if (dp[next] > newTime) {
                        dp[next] = newTime;

                        if (i == N - 1) {
                            visited[now] = true;
                            cycles.offer(now);
                        }
                    }
                }
            }
        }
    }

    static class Node {
        int to;
        long time;

        public Node(int to, long time) {
            this.to = to;
            this.time = time;
        }
    }
}
