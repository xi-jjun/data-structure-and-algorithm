package algorithm.baekjoon.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// baekJoon 2589 Gold5 - 보물섬
public class G2589 {
    static final char LAND = 'L';
    static final char SEA = 'W';
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};
    static int N, M, answer = -1;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == LAND) {
                    bfs(i, j);
                }
            }
        }

        System.out.println(answer);
    }

    private static void bfs(int x, int y) {
        int[][] distance = new int[N][M];
        boolean[][] visited = new boolean[N][M];
        visited[x][y] = true;
        Queue<Location> q = new ArrayDeque<>();
        q.offer(new Location(x, y, 0));

        while (!q.isEmpty()) {
            Location currLocation = q.poll();
            int currX = currLocation.x;
            int currY = currLocation.y;
            int time = currLocation.time;

            for (int i = 0; i < 4; i++) {
                int nextX = currX + dx[i];
                int nextY = currY + dy[i];

                if (inRange(nextX, nextY) && !visited[nextX][nextY] && map[nextX][nextY] == LAND) {
                    visited[nextX][nextY] = true;
                    distance[nextX][nextY] = time + 1;
                    q.offer(new Location(nextX, nextY, time + 1));
                }
            }
        }

        answer = Math.max(answer, getMaxDistance(distance));
    }

    private static int getMaxDistance(int[][] distance) {
        int ret = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ret = Math.max(ret, distance[i][j]);
            }
        }
        return ret;
    }

    private static boolean inRange(int x, int y) {
        return !(x < 0 || x >= N || y < 0 || y >= M);
    }

    static class Location {
        int x, y;
        int time;

        public Location(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}
