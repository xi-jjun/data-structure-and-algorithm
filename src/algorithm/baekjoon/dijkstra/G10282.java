package algorithm.baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class G10282 {
    static final int INF = Integer.MAX_VALUE;
    static int total;
    static long finishedTime;
    static int n, d, c;
    static List<List<Computer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());
        while (tc-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            for (int i = 0; i < n + 1; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                graph.get(b).add(new Computer(a, s));
            }

            total = 0; // c 는 처음부터 감염되어 있으니깐 1부터 counting
            finishedTime = 0L;

            bfs(c);

            bw.write(total + " " + finishedTime + "\n");
        }

        bw.flush();
        bw.close();
    }

    private static void bfs(int start) {
        boolean[] visited = new boolean[n + 1];
        long[] times = new long[n + 1];
        PriorityQueue<Computer> pq = new PriorityQueue<>();

        Arrays.fill(times, INF);
        times[start] = 0L;
        pq.add(new Computer(start, 0));

        while (!pq.isEmpty()) {
            Computer curr = pq.poll();
            int currNode = curr.number;

            if (visited[currNode]) continue;

            visited[currNode] = true;
            total++;
            for (Computer next : graph.get(currNode)) {
                int nextNode = next.number;
                long infectionTime = next.time;

                if (times[nextNode] >= times[currNode] + infectionTime) {
                    times[nextNode] = times[currNode] + infectionTime;
                    pq.offer(new Computer(nextNode, times[nextNode]));
                }
            }
        }

        for (long time : times) {
            if (time != INF && time > finishedTime) {
                finishedTime = time;
            }
        }
    }

    static class Computer implements Comparable<Computer> {
        int number;
        long time;

        public Computer(int number, long time) {
            this.number = number;
            this.time = time;
        }

        @Override
        public int compareTo(Computer o) {
            return (int) (this.time - o.time);
        }
    }
}
