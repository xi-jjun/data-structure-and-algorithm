package algorithm.baekjoon.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// dp + dfs 한번 더 봐야할 유형. 중요
// https://jaesungbong.tistory.com/17
// baekJoon 1937 Gold3 욕심쟁이 판다
public class G1937 {
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};
    static int N, answer;
    static int[][] map;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        getInput();

        solution();

        System.out.println(answer);
    }

    private static void solution() {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                answer = Math.max(answer, dfs(x, y));
            }
        }
    }

    private static int dfs(int x, int y) {
        if (dp[x][y] == 0) {
            dp[x][y] = 1; // 탐색 시작하면 1칸은 이동한 것이기에
            for (int d = 0; d < 4; d++) {
                int nextX = x + dx[d];
                int nextY = y + dy[d];

                if (inRange(nextX, nextY) && map[x][y] < map[nextX][nextY]) {
                    dp[x][y] = Math.max(dp[x][y], dfs(nextX, nextY) + 1);
                }
            }
        }

        return dp[x][y];
    }

    private static boolean inRange(int x, int y) {
        return !(x < 0 || x >= N || y < 0 || y >= N);
    }

    private static void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; st.hasMoreTokens(); j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
