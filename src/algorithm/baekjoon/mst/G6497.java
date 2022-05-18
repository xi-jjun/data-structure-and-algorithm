package algorithm.baekjoon.mst;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class G6497 {
    static int N, M;
    static int[] parent;
    static List<Edge> edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) break;

            parent = new int[N];
            for (int i = 0; i < N; i++) {
                parent[i] = i; // 부모는 자기 자신으로 초기화
            }

            int total = 0;
            edges = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());

                edges.add(new Edge(a, b, z));
                total += z;
            }
            Collections.sort(edges);

            int minCost = 0;
            for (Edge edge : edges) {
                if (find(edge.a) != find(edge.b)) {
                    minCost += edge.weight;
                    union(edge.a, edge.b);
                }
            }

            bw.write((total - minCost) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void union(int u, int v) {
        u = find(u);
        v = find(v);

        if (u != v) {
            parent[u] = v;
        }
    }

    static int find(int u) {
        if (u == parent[u]) {
            return u;
        }

        return parent[u] = find(parent[u]);
    }

    static class Edge implements Comparable<Edge> {
        int a, b;
        int weight;

        public Edge(int a, int b, int weight) {
            this.a = a;
            this.b = b;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
}
