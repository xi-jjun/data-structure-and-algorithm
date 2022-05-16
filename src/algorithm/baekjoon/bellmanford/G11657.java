package algorithm.baekjoon.bellmanford;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// baekJoon 11657 Gold4 - 타임머신
public class G11657 {
    static final long INF = 10000000000000L;
    static int N, M;
    static long[] dp;
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
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            graph.get(A).add(new Node(B, C));
        }

        boolean cycleOn = bellmanFord();

        if (cycleOn) {
            bw.write("-1\n");
        } else {
            for (int dest = 2; dest < N + 1; dest++) {
                bw.write((dp[dest] == INF ? -1 : dp[dest]) + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean bellmanFord() {
        dp = new long[N + 1];
        Arrays.fill(dp, INF);
        dp[1] = 0;

        for (int stage = 0; stage < N + 1; stage++) {
            for (int now = 1; now <= N; now++) {
                for (Node nextNode : graph.get(now)) {
                    if (dp[now] == INF) continue;

                    int next = nextNode.to;
                    long time = nextNode.time;

                    if (dp[next] > dp[now] + time) {
                        dp[next] = dp[now] + time;

                        if (stage == N) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
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
