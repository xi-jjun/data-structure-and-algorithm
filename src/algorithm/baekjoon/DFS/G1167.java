package algorithm.baekjoon.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// baekJoon 1167 Gold2 트리의 지름
public class G1167 {
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

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                int to = Integer.parseInt(st.nextToken());
                if (to == -1) break;
                int weight = Integer.parseInt(st.nextToken());
                graph.get(from).add(new Node(to, weight));
                graph.get(to).add(new Node(from, weight));
            }
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
            int weight = nextNode.weight;
            if (!visited[next]) {
                visited[next] = true;
                dfs(next, acc + weight);
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
