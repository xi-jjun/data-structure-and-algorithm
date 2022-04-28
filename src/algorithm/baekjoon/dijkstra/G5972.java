package algorithm.baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class G5972 {
    static final int INF = Integer.MAX_VALUE / 2 - 1;
    static int N, M;
    static int[] dp;
//    static boolean[][] connected;
    static Set<int[]> connected = new HashSet<>();
    static List<List<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[N + 1];
//        connected = new boolean[N + 1][N + 1];
        Arrays.fill(dp, INF);

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int fee = Integer.parseInt(st.nextToken());
            if (isNotConnected(start, end)) {
                graph.get(start).add(new Node(end, fee));
                graph.get(end).add(new Node(start, fee));
            } else {
                int dupNodeIndex = getIndexOf(start, end);
                Node dupNode = graph.get(start).get(dupNodeIndex);
                if (fee < dupNode.fee) {
                    graph.get(start).set(dupNodeIndex, new Node(end, fee));
                    int dupNodeIndex2 = getIndexOf(end, start);
                    graph.get(end).set(dupNodeIndex2, new Node(start, fee));
                }
            }
        }

        dijkstra();
        System.out.println(dp[N]);
    }

    private static void dijkstra() {
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.offer(new Node(1, 0));
        dp[1] = 0;

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int currentNode = curr.to;

            if (visited[currentNode]) continue;

            visited[currentNode] = true;
            for (Node nextNode : graph.get(currentNode)) {
                int next = nextNode.to;
                int nextFee = nextNode.fee;

                if (dp[next] >= dp[currentNode] + nextFee) {
                    dp[next] = dp[currentNode] + nextFee;
                    pq.offer(new Node(next, dp[next]));
                }
            }
        }
    }

    private static int getIndexOf(int start, int end) {
        int index = 0;
        for (Node node : graph.get(start)) {
            if (node.to == end) return index;
            index++;
        }
        return index;
    }

    private static boolean isNotConnected(int start, int end) {
        boolean t1 = connected.add(new int[]{start, end});
        boolean t2 = connected.add(new int[]{end, start});
        return t1 && t2;
    }

    static class Node implements Comparable<Node> {
        int to;
        int fee;

        public Node(int to, int fee) {
            this.to = to;
            this.fee = fee;
        }

        @Override
        public int compareTo(Node o) {
            return this.fee - o.fee;
        }
    }
}
