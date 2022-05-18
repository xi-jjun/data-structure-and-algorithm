package algorithm.baekjoon.bellmanford;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

// baekJoon 1738 Gold2 - 골목길
public class G1738 {
    static final long INF = 10000000000000L;
    static int N, M;
    static int[] path;
    static long[] dp;
    static boolean[] visited;
    static Queue<Integer> cycles = new ArrayDeque<>();
    static List<List<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Node(v, w));
        }

        bellmanFord();
        bfs();

        if (visited[N] || dp[N] == -INF) {
            bw.write("-1\n");
        } else {
            int now = N;
            Stack<Integer> paths = new Stack<>();
            paths.push(now);
            while (path[now] != 0) {
                paths.push(path[now]);
                now = path[now];
            }

            while (!paths.isEmpty()) {
                bw.write(paths.pop() + " ");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfs() {
        while (!cycles.isEmpty()) {
            Integer now = cycles.poll();

            for (Node nextNode : graph.get(now)) {
                int next = nextNode.to;

                if (visited[next]) continue;

                visited[next] = true;
                cycles.offer(next);
            }
        }
    }

    private static void bellmanFord() {
        visited = new boolean[N + 1];
        path = new int[N + 1];
        dp = new long[N + 1];
        Arrays.fill(dp, -INF);
        dp[1] = 0;

        for (int stage = 0; stage <= N; stage++) {
            for (int now = 1; now <= N; now++) {
                for (Node nextNode : graph.get(now)) {
                    if (dp[now] == -INF) continue;

                    int next = nextNode.to;
                    long cost = nextNode.cost;

                    if (dp[next] < dp[now] + cost) {
                        dp[next] = dp[now] + cost;
                        path[next] = now;

                        if (stage == N) {
                            cycles.offer(now);
                            visited[now] = true;
                        }
                    }
                }
            }
        }
    }

    static class Node {
        int to;
        long cost;

        public Node(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }
    }
}
