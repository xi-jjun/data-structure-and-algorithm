package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// baekJoon 2178(다시풀기) silver1 미로탐색 - Study
public class ReS2178 {
    static int N, M;
    static int[][] map;
    static int[][] distance;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static void bfs() {
        Queue<Point> points = new ArrayDeque<>();
        points.offer(new Point(0, 0));

        while (!points.isEmpty()) {
            int x = points.peek().x;
            int y = points.peek().y;
            visited[x][y] = true;
            points.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];

                if (isValid(nextX, nextY)) {
                    distance[nextX][nextY] = distance[x][y] + 1;
                    visited[nextX][nextY] = true; // 이거 없으면 메모리 초과 뜸
//                    System.out.println("points.size() = " + points.size());
                    points.offer(new Point(nextX, nextY));
                }
            }
        }
    }

    private static boolean isValid(int x, int y) {
        return !(x < 0 || x >= N || y < 0 || y >= M) && !visited[x][y] && map[x][y] == 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        distance = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            int idy = 0;
            for (String number : line.split("")) {
                map[i][idy++] = Integer.parseInt(number);
            }
        }

        bfs();
        System.out.println((distance[N - 1][M - 1] + 1));
    }

    static class Point {
        private final int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
