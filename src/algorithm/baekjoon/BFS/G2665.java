package algorithm.baekjoon.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class G2665 {
    static final char WHITE = '1';
    static final char BLACK = '0';
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};
    static int N;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int answer = bfs();
        System.out.println(answer);
    }

    private static int bfs() {
        boolean[][][] visited = new boolean[51][N][N];
        PriorityQueue<Location> pq = new PriorityQueue<>();

        visited[0][0][0] = true;
        pq.offer(new Location(0, 0, 0));

        while (!pq.isEmpty()) {
            Location curr = pq.poll();
            int x = curr.x;
            int y = curr.y;
            int cnt = curr.blackCount;

            if (x == N - 1 && y == N - 1) return cnt;

            for (int i = 0; i < 4; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];

                if (inRange(nextX, nextY) && !visited[cnt][nextX][nextY]) {
                    visited[cnt][nextX][nextY] = true;
                    if (isBlack(nextX, nextY)) {
                        pq.offer(new Location(nextX, nextY, cnt + 1));
                    } else {
                        pq.offer(new Location(nextX, nextY, cnt));
                    }
                }
            }
        }

        return 0;
    }

    private static boolean isBlack(int x, int y) {
        return map[x][y] == BLACK;
    }

    private static boolean inRange(int x, int y) {
        return !(x < 0 || x >= N || y < 0 || y >= N);
    }

    static class Location implements Comparable<Location> {
        int x;
        int y;
        int blackCount;

        public Location(int x, int y, int blackCount) {
            this.x = x;
            this.y = y;
            this.blackCount = blackCount;
        }

        @Override
        public int compareTo(Location o) {
            return this.blackCount - o.blackCount;
        }
    }
}
