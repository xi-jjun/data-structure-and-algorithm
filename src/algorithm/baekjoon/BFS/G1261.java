package algorithm.baekjoon.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// baekJoon 1261 Gold4 - 알고스팟
public class G1261 {
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};
    static int N, M;
    static char[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int answer = bfs();
        System.out.println(answer);
    }

    private static int bfs() {
        visited = new boolean[N][M];
        visited[0][0] = true;
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.offer(new Point(0, 0, 0));

        while (!pq.isEmpty()) {
            Point curr = pq.poll();
            int nowX = curr.x;
            int nowY = curr.y;
            int nowBrokenCount = curr.count;

            for (int d = 0; d < 4; d++) {
                int nextX = nowX + dx[d];
                int nextY = nowY + dy[d];

                if (valid(nextX, nextY)) {
                    if (nextX == N - 1 && nextY == M - 1) {
                        return nowBrokenCount;
                    }
                    visited[nextX][nextY] = true;
                    pq.offer(new Point(nextX, nextY, nowBrokenCount + (map[nextX][nextY] - '0')));
                }
            }
        }
        return 0;
    }

    private static boolean valid(int x, int y) {
        return inRange(x, y) && !visited[x][y];
    }

    private static boolean inRange(int x, int y) {
        return !(x < 0 || x >= N || y < 0 || y >= M);
    }

    static class Point implements Comparable<Point> {
        int x, y;
        int count;

        public Point(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        @Override
        public int compareTo(Point o) {
            return this.count - o.count;
        }
    }
}
