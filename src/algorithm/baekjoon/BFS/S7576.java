package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// baekJoon 7576 silver1 토마토
// https://jdselectron.tistory.com/55 참고.
public class S7576 {
    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static final int NOT_GROWN = 0;
    static final int GROWN = 1;
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};

    static int N, M;
    static int[][] box;
    static int[][] dates;

    static Queue<Point> q = new LinkedList<>();

    static void bfs() {
        while (!q.isEmpty()) {
            int x = q.peek().x;
            int y = q.peek().y;

            q.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];

                if ((nextX >= 0 && nextX < N) && (nextY >= 0 && nextY < M)) {
                    if (box[nextX][nextY] == NOT_GROWN) {
                        dates[nextX][nextY] = dates[x][y] + 1;
                        box[nextX][nextY] = GROWN;
                        q.offer(new Point(nextX, nextY));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] l = br.readLine().split(" ");
        N = Integer.parseInt(l[1]);
        M = Integer.parseInt(l[0]);

        box = new int[N][M];
        dates = new int[N][M];

        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            int index = 0;

            for (int k = 0; k < M; k++) {
                box[i][k] = Integer.parseInt(line[index++]);
                if (box[i][k] == GROWN) q.offer(new Point(i, k));
            }
        }

        bfs();
        int maxElement = -9999;
        for (int i = 0; i < N; i++) {
            for (int k = 0; k < M; k++) {
                if (box[i][k] == NOT_GROWN) {
                    System.out.println("-1");
                    return;
                }
                if (dates[i][k] > maxElement) maxElement = dates[i][k];
            }
        }

        System.out.println(maxElement);
    }
}
