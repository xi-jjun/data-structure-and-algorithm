package algorithm.baekjoon.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// DFS + DP 좋은 문제인 듯
public class G1520 {
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};
    static int N, M;
    static int[][] map;
    static int[][] dp;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; st.hasMoreTokens(); j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(dfs(0, 0));

        br.close();
    }

    private static int dfs(int x, int y) {
        if (x == N - 1 && y == M - 1) {
            return 1;
        } else if (dp[x][y] == -1) {
            dp[x][y] = 0;
            for (int d = 0; d < 4; d++) {
                int nextX = x + dx[d];
                int nextY = y + dy[d];

                if (inRange(nextX, nextY) && isDescending(x, y, nextX, nextY)) {
                    dp[x][y] += dfs(nextX, nextY); // (x, y) 를 기준으로 4방향에 대해 기준으로 갈 수 있는지 확인
                }
            }
        }

        return dp[x][y];
    }

    private static boolean isDescending(int lastX, int lastY, int nextX, int nextY) {
        return map[lastX][lastY] > map[nextX][nextY];
    }

    private static boolean inRange(int x, int y) {
        return !(x < 0 || x >= N || y < 0 || y >= M);
    }
}
