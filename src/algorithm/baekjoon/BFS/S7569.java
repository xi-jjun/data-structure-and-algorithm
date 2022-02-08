package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class S7569 {
    static final int VISITED = 2;
    static final int RIPE_TOMATO = 1;
    static final int TOMATO = 0;
    static final int EMPTY = -1;
    static final int[] dx = {-1, 0, 1, 0, 0, 0};
    static final int[] dy = {0, 1, 0, -1, 0, 0};
    static final int[] dh = {0, 0, 0, 0, 1, -1};
    static int M, N, H;
    static int[][][] tomato;
    static int[][][] date;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        Queue<Point> q = new ArrayDeque<>();

        tomato = new int[H][N][M];
        date = new int[H][N][M];

        getTomato(br, q);
        bfs(q);

        if (!checkRipen()) return;

        int answer = getAnswer();
        System.out.println(answer);
    }

    private static boolean checkRipen() {
        for (int[][] toma : tomato) {
            for (int[] tom : toma) {
                for (int t : tom) {
                    if (t == TOMATO) {
                        System.out.println("-1");
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static int getAnswer() {
        int answer = -999;
        for (int[][] dat : date) {
            for (int[] da : dat) {
                for (int d : da) {
                    answer = Math.max(answer, d);
                }
            }
        }
        return answer;
    }

    private static void bfs(Queue<Point> queue) {
        while (!queue.isEmpty()) {
            int x = queue.peek().x;
            int y = queue.peek().y;
            int h = queue.peek().h;
            queue.poll();

            for (int i = 0; i < 6; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];
                int nextH = h + dh[i];

                if (isValid(nextX, nextY, nextH)) {
                    date[nextH][nextX][nextY] = date[h][x][y] + 1;
                    tomato[nextH][nextX][nextY] = VISITED;
                    queue.offer(new Point(nextX, nextY, nextH));
                }
            }
        }
    }

    private static boolean isValid(int x, int y, int h) {
        return !(x < 0 || x >= N || y < 0 || y >= M || h < 0 || h >= H) && tomato[h][x][y] == TOMATO;
    }

    private static void getTomato(BufferedReader br, Queue<Point> pointQueue) throws IOException {
        StringTokenizer st;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    tomato[i][j][k] = Integer.parseInt(st.nextToken());
                    if (tomato[i][j][k] == 1) {
                        pointQueue.offer(new Point(j, k, i));
                    }
                }
            }
        }
    }

    static class Point {
        private final int x, y, h;

        public Point(int x, int y, int h) {
            this.x = x;
            this.y = y;
            this.h = h;
        }
    }
}
