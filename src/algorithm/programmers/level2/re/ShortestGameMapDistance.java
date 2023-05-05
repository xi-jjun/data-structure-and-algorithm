package algorithm.programmers.level2.re;

import java.util.PriorityQueue;

public class ShortestGameMapDistance {
    static int N, M;
    static final int WALL = 0;
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};
    static boolean[][] visited = new boolean[101][101];

    public int solution(int[][] maps) {
        N = maps.length;
        M = maps[0].length;
        PriorityQueue<Location> pq = new PriorityQueue<>();
        pq.offer(new Location(0, 0, 1));
        visited[0][0] = true;

        while (!pq.isEmpty()) {
            Location location = pq.poll();
            int nowX = location.x;
            int nowY = location.y;
            int nowStep = location.step;

            if (nowX == N - 1 && nowY == M - 1) return nowStep;

            for (int d = 0; d < 4; d++) {
                int nextX = nowX + dx[d];
                int nextY = nowY + dy[d];

                if (isRange(nextX, nextY) && !visited[nextX][nextY] && maps[nextX][nextY] != WALL) {
                    visited[nextX][nextY] = true;
                    pq.offer(new Location(nextX, nextY, nowStep + 1));
                }
            }
        }

        return -1;
    }

    static boolean isRange(int x, int y) {
        return !(x < 0 || x >= N || y < 0 || y >= M);
    }

    static class Location implements Comparable<Location> {
        int x, y;
        int step;

        public Location(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }

        @Override
        public int compareTo(Location o) {
            return this.step - o.step;
        }
    }

    public static void main(String[] args) {

    }
}
