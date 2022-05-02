package algorithm.baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class G4485 {
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};
    static int N;
    static int[][] dp;
    static int[][] blackMoney;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int index = 1;
        String line = "111";
        while (true) {
            line = br.readLine();
            N = Integer.parseInt(line);
            if (N == 0) break;

            blackMoney = new int[N][N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; st.hasMoreTokens(); j++) {
                    blackMoney[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dijkstra();

            bw.write("Problem " + index + ": " + dp[N - 1][N - 1] + "\n");
            ++index;
        }

        bw.flush();
        bw.close();
    }

    private static void dijkstra() {
        PriorityQueue<Location> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[N][N];
        dp = new int[N][N];
        fillDpWithMaxValue();
        dp[0][0] = blackMoney[0][0];
        pq.offer(new Location(0, 0, dp[0][0]));

        while (!pq.isEmpty()) {
            Location curr = pq.poll();
            int x = curr.x;
            int y = curr.y;

            if (visited[x][y]) continue;

            visited[x][y] = true;
            for (int d = 0; d < 4; d++) {
                int nextX = x + dx[d];
                int nextY = y + dy[d];

                if (inRange(nextX, nextY) && dp[nextX][nextY] >= dp[x][y] + blackMoney[nextX][nextY]) {
                    dp[nextX][nextY] = dp[x][y] + blackMoney[nextX][nextY];
                    pq.offer(new Location(nextX, nextY, dp[nextX][nextY]));
                }
            }
        }
    }

    private static boolean inRange(int x, int y) {
        return !(x < 0 || x >= N || y < 0 || y >= N);
    }

    private static void fillDpWithMaxValue() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    static class Location implements Comparable<Location> {
        int x, y;
        int lose;

        public Location(int x, int y, int lose) {
            this.x = x;
            this.y = y;
            this.lose = lose;
        }

        @Override
        public int compareTo(Location o) {
            return this.lose - o.lose;
        }
    }
}
