package algorithm.baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://programmer-chocho.tistory.com/64 참고
// baekJoon 10473 Gold2 - 인간 대포
public class G10473 {
    static final double INF = Integer.MAX_VALUE;
    static final double SPEED = 5.0;
    static final double CANNON_DISTANCE = 50.0;
    static int n; // 대포 개수
    static ArrayList<Node>[] graph;
    static Coordinate[] coords;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        double x = Double.parseDouble(st.nextToken());
        double y = Double.parseDouble(st.nextToken());
        Coordinate start = new Coordinate(x, y);

        st = new StringTokenizer(br.readLine());
        x = Double.parseDouble(st.nextToken());
        y = Double.parseDouble(st.nextToken());
        Coordinate end = new Coordinate(x, y);

        n = Integer.parseInt(br.readLine());
        coords = new Coordinate[n + 2]; // start + cannons + end 개수 총 N + 2 개이다.
        graph = new ArrayList[n + 2];
        for (int i = 0; i < n + 2; i++) {
            graph[i] = new ArrayList<>();
        }

        // cannons
        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            x = Double.parseDouble(st.nextToken());
            y = Double.parseDouble(st.nextToken());
            coords[i] = new Coordinate(x, y);
        }

        // start, end
        coords[0] = start;
        coords[n + 1] = end;

        // graph 생성하자. double 로는 정확히 어디서 어디까지 가는지 모르니깐 그 정보를 만들어줘야한다.
        // start -> all : 무조건 처음엔 걸어서 대포에 가야함
        for (int node = 1; node < n + 2; node++) {
            double distance = Math.sqrt(
                    Math.pow(coords[0].x - coords[node].x, 2) +
                            Math.pow(coords[0].y - coords[node].y, 2)
            );
            double time = distance / SPEED;
            graph[0].add(new Node(node, time));
        }

        // coords 로 graph 만들기
        for (int i = 1; i < n + 2; i++) {
            for (int j = 0; j < n + 2; j++) {
                double distance = Math.sqrt(
                        Math.pow(coords[i].x - coords[j].x, 2) +
                                Math.pow(coords[i].y - coords[j].y, 2)
                );
                double minTime = Math.min(distance / SPEED, Math.abs(distance - CANNON_DISTANCE) / SPEED + 2);
                graph[i].add(new Node(j, minTime));
            }
        }

        double answer = dijkstra();
        System.out.println((float)answer);
    }

    private static double dijkstra() {
        double[] dp = new double[n + 2];
        Arrays.fill(dp, INF);
        boolean[] visited = new boolean[n + 2];

        PriorityQueue<Node> pq = new PriorityQueue<>();
        dp[0] = 0;
        pq.offer(new Node(0, dp[0]));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int now = curr.dest;

            if (visited[now]) continue;

            visited[now] = true;
            for (Node nextNode : graph[now]) {
                int next = nextNode.dest;
                double nextTime = nextNode.time;

                if (dp[next] > dp[now] + nextTime) {
                    dp[next] = dp[now] + nextTime;
                    pq.offer(new Node(next, dp[next]));
                }
            }
        }

        return dp[n + 1];
    }

    static class Coordinate {
        double x, y;

        public Coordinate(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Node implements Comparable<Node> {
        int dest;
        double time;

        public Node(int dest, double time) {
            this.dest = dest;
            this.time = time;
        }


        @Override
        public int compareTo(Node o) {
            return Double.compare(this.time, o.time);
        }
    }

}
