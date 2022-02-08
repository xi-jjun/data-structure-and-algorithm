package BFS;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// baekJoon 2178 silver1 미로탐색
// https://cocoon1787.tistory.com/115 참고
public class S2178 {

    static int N, M;
    static int[][] map, distance;
    static final int WALL = 0;
    static final int ROAD = 1;
    static final int VISITED = 2;

    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void bfs(int startX, int startY) {
        Queue<Point> q = new LinkedList<>();
        map[startX][startY] = VISITED;
        q.offer(new Point(startX, startY));

        while (!q.isEmpty()) {
            int x = q.peek().x;
            int y = q.peek().y;

            q.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];

                if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < M) {
                    if (map[nextX][nextY] == ROAD) {
                        distance[nextX][nextY] = distance[x][y] + 1;
                        map[nextX][nextY] = VISITED;
                        q.offer(new Point(nextX, nextY));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        distance = new int[N][M];

        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split("");
            for (int k = 0; k < M; k++) map[i][k] = Integer.parseInt(line[k]);
        }

        bfs(0, 0);
        bw.write((distance[N - 1][M - 1] + 1) + "");
        bw.flush();
        bw.close();
    }
}
