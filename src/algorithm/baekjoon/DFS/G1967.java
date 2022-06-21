package algorithm.baekjoon.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://lmcoa15.tistory.com/56
// baekJoon 1967 Gold4 트리의 지름
public class G1967 {
    static int N;
    static int maxPoint, maxValue;
    static boolean[] visited;
    static List<List<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        visited = new boolean[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(parent).add(new Node(child, weight));
            graph.get(child).add(new Node(parent, weight));
        }

        visited[1] = true;
        dfs(1, 0);

        visited = new boolean[N + 1];
        visited[maxPoint] = true;
        dfs(maxPoint, 0);

        System.out.println(maxValue);
        br.close();
    }

    private static void dfs(int now, int acc) {
        if (maxValue < acc) {
            maxValue = acc;
            maxPoint = now;
        }

        for (Node nextNode : graph.get(now)) {
            int next = nextNode.to;

            if (!visited[next]) {
                visited[next] = true;
                dfs(next, acc + nextNode.weight);
            }
        }
    }


    static class Node {
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
