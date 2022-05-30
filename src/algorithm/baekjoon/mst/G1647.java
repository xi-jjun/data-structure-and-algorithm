package algorithm.baekjoon.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class G1647 {
    static int N, M;
    static int[] parent;
    static PriorityQueue<Edge> edges = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            edges.offer(new Edge(a, b, cost));
        }

        parent = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            parent[i] = i; // i 의 부모는 parent[i]. 자기 자신으로 초기화. 현재 덩어리는 다 흩어져 있기에
        }

        int count = 0;
        int minCost = 0;
        while (count < N - 2) {
            Edge edge = edges.poll();

            if (find(edge.start) != find(edge.end)) {
                minCost += edge.cost;
                count++;
                union(edge.start, edge.end);
            }
        }

        System.out.println(minCost);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            parent[a] = b; // 한쪽의 부모에 이어 붙여서 mst 집합에 추가
        }
    }

    private static int find(int node) {
        if (node == parent[node]) {
            return node;
        }

        return parent[node] = find(parent[node]);
    }

    static class Edge implements Comparable<Edge> {
        int start;
        int end;
        int cost;

        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
}
