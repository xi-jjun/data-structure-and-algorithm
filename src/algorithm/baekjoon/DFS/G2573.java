package algorithm.baekjoon.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// baekJoon 2573 Gold4 - 빙산
public class G2573 {
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};
    static final int SEA = 0;
    static int N, M;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; st.hasMoreTokens(); j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = solve();
        System.out.println(answer);
    }

    private static int solve() {
        int time = 0;

        while (true) {
            int flag = 0;
            resetVisitedArray();

            for (int x = 0; x < N; x++) {
                for (int y = 0; y < M; y++) {
                    if (map[x][y] != SEA && !visited[x][y]) {
                        if (flag == 0) {
                            dfs(x, y);
                            ++flag;
                        } else {
                            return time;
                        }
                    }
                }
            }

            if (flag == 1) {
                meltIce();
            } else if (flag == 0) {
                return 0;
            }
            time++;
        }
    }

    private static void meltIce() {
        int[][] ret = new int[N][M];

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (map[x][y] != SEA) {
                    int aroundSeaCount = countSea(x, y);
                    ret[x][y] = Math.max(0, map[x][y] - aroundSeaCount);
                }
            }
        }

        map = ret;
    }

    private static int countSea(int x, int y) {
        int ret = 0;
        for (int d = 0; d < 4; d++) {
            int nextX = x + dx[d];
            int nextY = y + dy[d];

            if (inRange(nextX, nextY) && map[nextX][nextY] == SEA) {
                ++ret;
            }
        }

        return ret;
    }

    private static void dfs(int x, int y) {
        visited[x][y] = true;

        for (int d = 0; d < 4; d++) {
            int nextX = x + dx[d];
            int nextY = y + dy[d];

            if (inRange(nextX, nextY) && !visited[nextX][nextY] && map[nextX][nextY] != SEA) {
                dfs(nextX, nextY);
            }
        }
    }

    private static boolean inRange(int x, int y) {
        return !(x < 0 || x >= N || y < 0 || y >= M);
    }

    private static void resetVisitedArray() {
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }
    }
}
