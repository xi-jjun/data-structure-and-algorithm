package algorithm.baekjoon.BFS.re;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// baekJoon 1697 silver1 숨박꼭질
public class S1697 {
    static int N, K;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visited = new boolean[200001];
        int answer = bfs(N);

        System.out.println(answer);
    }

    public static int bfs(int start) {
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.offer(new Pos(start, 0));
        visited[start] = true;

        while (!pq.isEmpty()) {
            Pos curr = pq.poll();
            int now = curr.loc;
            int nowTime = curr.time;

            if (now == K) {
                return nowTime;
            }

            if (inRange(now + 1) && !visited[now + 1]) {
                visited[now + 1] = true;
                pq.offer(new Pos(now + 1, nowTime + 1));
            }

            if (inRange(now - 1) && !visited[now - 1]) {
                visited[now - 1] = true;
                pq.offer(new Pos(now - 1, nowTime + 1));
            }

            if (inRange(now * 2) && !visited[now * 2]) {
                visited[now * 2] = true;
                pq.offer(new Pos(now * 2, nowTime + 1));
            }
        }

        return -1;
    }

    private static boolean inRange(int loc) {
        return !(loc < 0 || loc >= 200001);
    }

    static class Pos implements Comparable<Pos> {
        int loc;
        int time;

        public Pos(int loc, int time) {
            this.loc = loc;
            this.time = time;
        }

        public int compareTo(Pos o) {
            return this.time - o.time;
        }
    }
}
