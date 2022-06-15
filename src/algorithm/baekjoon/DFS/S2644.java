package algorithm.baekjoon.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// baekJoon 2644 silver2 촌수계산
public class S2644 {
    static int n;
    static boolean ok = false;
    static int parent, child, distance = 0;
    static boolean[] visited;
    static Node[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        parent = Integer.parseInt(st.nextToken());
        child = Integer.parseInt(st.nextToken());

        nodes = new Node[n + 1];
        for (int i = 1; i < n + 1; i++) {
            nodes[i] = new Node(i);
        }

        visited = new boolean[n + 1];
        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            nodes[p].children.add(nodes[c]);
            nodes[c].children.add(nodes[p]);
        }

        dfs(parent);
        System.out.println(distance);

        br.close();
    }

    private static void dfs(int node) {
        if (node == child || ok) {
            ok = true;
            return;
        }

        distance++;
        visited[node] = true;
        for (Node next : nodes[node].children) {
            if (!visited[next.value]) {
                dfs(next.value);
            }
        }
    }

    static class Node {
        int value;
        List<Node> children = new ArrayList<>();

        public Node(int value) {
            this.value = value;
        }
    }
}
