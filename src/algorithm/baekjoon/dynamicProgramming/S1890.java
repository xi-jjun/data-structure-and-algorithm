package algorithm.baekjoon.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// baekJoon 1890 silver1 점프
public class S1890 {
    static int N;
    static int[][] map;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        getInput();

        solution();

        System.out.println(dp[N - 1][N - 1]);
    }

    private static void solution() {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (dp[x][y] == 0 || isEnd(x, y)) continue;

                int offset = map[x][y];
                int nextX = x + offset;
                int nextY = y + offset;

                if (nextX < N) {
                    dp[nextX][y] += dp[x][y];
                }

                if (nextY < N) {
                    dp[x][nextY] += dp[x][y];
                }
            }
        }
    }

    private static boolean isEnd(int x, int y) {
        return x == N - 1 && y == N - 1;
    }

    private static void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new long[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; st.hasMoreTokens(); j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp[0][0] = 1;
    }
}
