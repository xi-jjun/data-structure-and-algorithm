package algorithm.swexpert.d3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// sw expert D3 14413 격자판 칠하기
public class P14413 {
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};
    static final char TBD = '?';
    static final char BLACK = '#';
    static final char WHITE = '.';
    static int N, M;
    static boolean fail;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            fail = false;
            map = new char[N][M];

            for (int row = 0; row < N; row++) {
                map[row] = br.readLine().toCharArray();
            }

            boolean noColored = solution();

            if (noColored) {
                map[0][0] = BLACK;
                solution();
            }

            bw.write("#" + tc + " " + (fail ? "impossible" : "possible") + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean solution() {
        boolean noColored = true;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (map[x][y] != TBD) {
                    noColored = false;
                    painting(x, y);
                    if (fail) return false;
                } else if (map[x][y] != TBD && !valid(x, y)) {
                    fail = true;
                    return false;
                }
            }
        }
        return noColored;
    }

    private static boolean valid(int x, int y) {
        for (int d = 0; d < 4; d++) {
            int nextX = x + dx[d];
            int nextY = y + dy[d];

            if (inRange(nextX, nextY)) {
                if (map[x][y] == map[nextX][nextY]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void painting(int x, int y) {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(x, y, map[x][y]));

        while (!q.isEmpty()) {
            Pos curr = q.poll();
            int nowX = curr.x;
            int nowY = curr.y;
            int nowColor = curr.color;

            for (int d = 0; d < 4; d++) {
                int nextX = nowX + dx[d];
                int nextY = nowY + dy[d];

                if (inRange(nextX, nextY)) {
                    if (map[nextX][nextY] == TBD) {
                        map[nextX][nextY] = nowColor == BLACK ? WHITE : BLACK;
                        q.offer(new Pos(nextX, nextY, map[nextX][nextY]));
                    } else if (map[nextX][nextY] == nowColor) {
                        fail = true;
                        return;
                    }
                }
            }
        }
    }

    private static boolean inRange(int x, int y) {
        return !(x < 0 || x >= N || y < 0 || y >= M);
    }

    static class Pos {
        int x, y;
        char color;

        public Pos(int x, int y, char color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }
}
