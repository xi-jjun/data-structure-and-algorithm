package algorithm.baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// baekJoon 11779 Gold3 - 최소 비용 구하기
public class G11779 {
    static final long INF = 100000000000000L;
    static int n, m;
    static int[] path;
    static long[][] weight;
    static boolean[][] check;
    static List<List<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        weight = new long[n + 1][n + 1];
        check = new boolean[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            if (A == B) continue;

            weight[A][B] = check[A][B] ? Math.min(weight[A][B], cost) : cost;

            if (!check[A][B]) {
                graph.get(A).add(B);
                check[A][B] = true;
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        path = new int[n + 1];
        long answer = dijkstra(start, end);
        bw.write(answer + "\n");

        List<Integer> pathBuffer = new ArrayList<>();
        int now = end;
        while (path[now] != 0) {
            pathBuffer.add(now);
            now = path[now];
        }
        pathBuffer.add(start);

        bw.write(pathBuffer.size() + "\n");
        for (int i = pathBuffer.size() - 1; i >= 0; i--) {
            bw.write(pathBuffer.get(i) + " ");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static long dijkstra(int start, int end) {
        long[] dp = new long[n + 1];
        Arrays.fill(dp, INF);
        dp[start] = 0;

        PriorityQueue<Location> pq = new PriorityQueue<>();

        pq.offer(new Location(start, 0));

        while (!pq.isEmpty()) {
            Location curr = pq.poll();
            int now = curr.to;
            long nowCost = curr.cost;

            if (nowCost > dp[now]) continue;

            for (Integer next : graph.get(now)) {
                if (dp[next] >= dp[now] + weight[now][next]) {
                    dp[next] = dp[now] + weight[now][next];
                    pq.offer(new Location(next, dp[next]));
                    path[next] = now;
                }
            }
        }

        return dp[end];
    }

    static class Location implements Comparable<Location> {
        int to;
        long cost;

        public Location(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Location o) {
            return Long.compare(this.cost, o.cost);
        }
    }
}
