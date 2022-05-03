package algorithm.baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

// https://maivve.tistory.com/75 참고
// baekJoon 2211 Gold2 네트워크 복구
public class G2211 {
    static final int INF = 100000000;
    static int N, M;
    static int[] distance;
    static List<List<Computer>> graph = new ArrayList<>();
    static Set<String> results = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Computer(end, weight));
            graph.get(end).add(new Computer(start, weight));
        }

        dijkstra();

        bw.write(results.size() + "\n");
        for (String result : results) {
            bw.write(result + "\n");
        }

        bw.flush();
        bw.close();
    }

    private static void dijkstra() {
        distance = new int[N + 1];
        Arrays.fill(distance, INF);
        boolean[] visited = new boolean[N + 1];
        int[] path = new int[N + 1]; // 어차피 1번을 제외한 모든 노드는 하나의 부모를 가지기 때문.
        PriorityQueue<Computer> pq = new PriorityQueue<>();

        distance[1] = 0; // start distance is 0
        pq.offer(new Computer(1, 0)); // start 1,

        while (!pq.isEmpty()) {
            Computer curr = pq.poll();
            int com = curr.to;

            if (visited[com]) continue;

            visited[com] = true;
            for (int i = 0; i < graph.get(com).size(); i++) {
                Computer nextComputer = graph.get(com).get(i);
                int nextCom = nextComputer.to;
                int nextCost = nextComputer.weight;

                if (distance[nextCom] > distance[com] + nextCost) {
                    distance[nextCom] = distance[com] + nextCost;
                    pq.offer(new Computer(nextCom, distance[nextCom]));
                    path[nextCom] = com; // com → nextCom : start → end
                }
            }
        }

        for (int i = 2; i <= N; i++) {
            int end = i;
            while (path[end] != 0) {
                results.add(path[end] + " " + end);
                end = path[end];
            }
        }
    }

    static class Computer implements Comparable<Computer> {
        int to;
        int weight;

        public Computer(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Computer o) {
            return this.weight - o.weight;
        }
    }
}
