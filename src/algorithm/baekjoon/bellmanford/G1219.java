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

// baekJoon 1219 Gold1 - 오민식의 고민
// https://velog.io/@jdja2004/%EB%B0%B1%EC%A4%80-1219%EB%B2%88-%EC%98%A4%EB%AF%BC%EC%8B%9D%EC%9D%98-%EA%B3%A0%EB%AF%BC-C 참고
public class G1219 {
    static final int INF = Integer.MIN_VALUE;
    static int N, E, start, end;
    static int[] profits;
    static long[] dp;
    static boolean[] visited;
    static Queue<Integer> cycleNodes = new ArrayDeque<>();
    static List<List<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int expense = Integer.parseInt(st.nextToken());
            graph.get(s).add(new Node(e, expense));
        }

        profits = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; st.hasMoreTokens(); i++) {
            profits[i] = Integer.parseInt(st.nextToken());
        }


        dp = new long[N];
        Arrays.fill(dp, INF);
        dp[start] = profits[start];

        // bellman ford
        // 여기서 왜 순환구조임을 파악만 하고 고리를 안 끊어내냐? => 모든 edge 에 대하여 단순히 최단경로 table(여기서는 최대이익 table)을
        // 갱신만 하는 것이기에 파악만 하고 있으면 된다.
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            for (int now = 0; now < N; now++) {
                for (Node nextNode : graph.get(now)) {
                    if (dp[now] == INF) continue;
                    int next = nextNode.to;
                    int cost = nextNode.cost;

                    long profit = dp[now] - cost + profits[next];
                    if (dp[next] < profit) {
                        dp[next] = profit;

                        if (i == N - 1) { // 순환이 발생했다.
                            visited[now] = true; // 순환이 발생했고, BFS 에서 다시 탐색할 필요가 없다.
                            cycleNodes.offer(now); // BFS 에서 큐로 돌릴 것이다.
                        }
                    }
                }
            }
        }

        if (dp[end] == INF) {
            System.out.println("gg");
        } else {
            if (bfs()) System.out.println("Gee");
            else System.out.println(dp[end]);
        }
    }

    private static boolean bfs() {
        while (!cycleNodes.isEmpty()) {
            Integer now = cycleNodes.poll();

            for (Node nextNode : graph.get(now)) {
                int next = nextNode.to;
                if (visited[next]) continue;

                visited[next] = true; // cycle 되는 nodes 중에 end 와 관련된 node 가 있다면 true 표시가 될 것임.
                cycleNodes.offer(next);
            }
        }

        return visited[end]; // true : 순환구조 , false : 비순환구조
    }


    static class Node {
        int to, cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
}
