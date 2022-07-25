package algorithm.baekjoon.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/board/view/30941
// baekJoon 6087 Gold3 레이저 통신
public class G6087 {
    static int startX, startY;
    static final char WALL = '*';
    static final char RECEIVER = 'C';
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};
    static int N, M, answer = Integer.MAX_VALUE;
    static char[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        getInput();

        solution();
        System.out.println(answer);
    }

    private static void solution() {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (map[x][y] == RECEIVER) {
                    startX = x;
                    startY = y;
                    bfs(x, y);
                    return;
                }
            }
        }
    }

    private static boolean notStart(int x, int y) {
        return !(x == startX && y == startY);
    }

    private static void bfs(int x, int y) {
        Queue<Pos> pq = new ArrayDeque<>();
        pq.offer(new Pos(x, y, -1, 0));
        visited[x][y] = true;
        int[][] count = new int[N][M];
        resetCountArray(count);

        count[x][y] = 0;

        while (!pq.isEmpty()) {
            Pos curr = pq.poll();
            int nowX = curr.x;
            int nowY = curr.y;
            int switchedCnt = curr.switchedCnt;
            int nowDirection = curr.lastDirection;

            if (map[nowX][nowY] == RECEIVER && notStart(nowX, nowY)) {
                answer = Math.min(answer, switchedCnt);
                continue;
            }

            for (int d = 0; d < 4; d++) {
                int nextX = nowX + dx[d];
                int nextY = nowY + dy[d];

                if (goBack(nowDirection, d)) {
                    continue;
                }

                if (inRange(nextX, nextY) && map[nextX][nextY] != WALL) {
                    int nextSwitchedCount = getNextSwitchedCount(switchedCnt, nowDirection, d);

                    /**
                     * 이 부분에 대하여 생각을 못해서 틀렸음
                     * visited 가 기준이 아니라 '더 적게 방향전환' 한 케이스에게 갈 수 있게 해줘야 했음
                     */
                    if (count[nextX][nextY] >= nextSwitchedCount) {
                        count[nextX][nextY] = nextSwitchedCount;
                        pq.offer(new Pos(nextX, nextY, d, nextSwitchedCount));
                    }
                }
            }

        }
    }

    private static void resetCountArray(int[][] count) {
        for (int i = 0; i < N; i++) {
            Arrays.fill(count[i], 10000000);
        }
    }

    private static boolean goBack(int now, int next) {
        return (now == 0 && next == 2) || (now == 1 && next == 3) || (now == 2 && next == 0) || (now == 3 && next == 1);
    }

    private static int getNextSwitchedCount(int switchedCnt, int nowDirection, int nextDirection) {
        if (nowDirection == -1) {
            return 0;
        } else if (nowDirection == nextDirection) {
            return switchedCnt;
        } else {
            return switchedCnt + 1;
        }
    }

    private static boolean inRange(int x, int y) {
        return !(x < 0 || x >= N || y < 0 || y >= M);
    }

    private static void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
    }

    static class Pos {
        int x, y;
        int lastDirection;
        int switchedCnt;

        public Pos(int x, int y, int lastDirection, int switchedCnt) {
            this.x = x;
            this.y = y;
            this.lastDirection = lastDirection;
            this.switchedCnt = switchedCnt;
        }
    }
}
