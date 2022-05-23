package algorithm.baekjoon.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// baekJoon 1939 Gold4 - 중량제한
public class G1939 {
    static int N, M, start, end;
    static List<List<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        int low = 0;
        int high = -1;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            graph.get(A).add(new Node(B, C));
            graph.get(B).add(new Node(A, C));
            high = Math.max(high, C);
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        while (high >= low) {
            int mid = (low + high) / 2;
            if (bfs(mid)) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println(high);
    }

    private static boolean bfs(int cost) {
        boolean[] visited = new boolean[N + 1];
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(start));
        visited[start] = true;

        while (!q.isEmpty()) {
            Node curr = q.poll();
            int now = curr.number;

            if (end == now) {
                return true;
            }

            for (Node nextNode : graph.get(now)) {
                int next = nextNode.number;
                int weight = nextNode.weight;

                if (!visited[next] && cost <= weight) {
                    visited[next] = true;
                    q.offer(new Node(next));
                }
            }
        }

        return false;
    }

    static class Node{
        int number;
        int weight;

        public Node(int number) {
            this.number = number;
        }

        public Node(int number, int weight) {
            this.number = number;
            this.weight = weight;
        }
    }
}
