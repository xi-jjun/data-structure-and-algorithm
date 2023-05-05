package algorithm.baekjoon.BFS.re;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.ArrayDeque;

public class S1260 {

    static int N, M, V;
    static boolean[] visitedForDfs = new boolean[1001];
    static List<List<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        generateGraph();

        dfs(V);
        System.out.println();
        bfs(V);
    }

    static void dfs(int vertex) {
        visitedForDfs[vertex] = true;
        System.out.print(vertex + " ");

        List<Integer> nextNumbers = graph.get(vertex);
        for (Integer next : nextNumbers) {
            if (!visitedForDfs[next]) {
                dfs(next);
            }
        }
    }

    static void bfs(int start) {
        boolean[] visited = new boolean[N + 1];

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visited[start] = true; // checked

        while (!q.isEmpty()) {
            int now = q.poll();
            System.out.print(now + " ");

            List<Integer> numbers = graph.get(now);
            for (Integer number : numbers) {
                if (!visited[number]) {
                    visited[number] = true;
                    q.offer(number);
                }
            }
        }
    }

    static void generateGraph() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        // 2-D list 이기에 각 element 로 초기 array list 를 생성해놔야 함.
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 주어지는 정점정보에 대해서 각 정점끼리의 관계를 그래프에 정의
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 두 정점 사이에는 다음과 같은 관계가 존재할 수 있음 => 양방향, 단방향
            graph.get(a).add(b); // 정점a와 정점b의 관계를 표시해줌. a->b
            graph.get(b).add(a); // 정점a와 정점b의 관계를 표시해줌. b->a
        }

        // 문제에서 '숫자가 작은 곳부터 방문' 라는 조건이 있었기에 정렬이 필요했음.
        for (int i = 1; i <= N; i++) {
            graph.get(i).sort(Comparator.comparingInt(o -> o));
        }

        br.close();
    }
}
