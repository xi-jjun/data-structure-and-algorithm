package algorithm.baekjoon.bellmanford;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

// baekJoon 3860 Platinum5 - 할로윈 묘지
public class P3860 {
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};
    static final long INF = 100000000000000L;
    static final int TOMBSTONE = 1;
    static final int GHOST_HOLE = 2;
    static int W, H, G, E;
    static int[][] map;
    static long[][] dp;
    static Map<Location, Location> ghostHoleMapper;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        do {
            StringTokenizer st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            if (W == 0 && H == 0) break;
            map = new int[W][H];

            G = Integer.parseInt(br.readLine());
            for (int i = 0; i < G; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[x][y] = TOMBSTONE;
            }

            ghostHoleMapper = new HashMap<>();
            E = Integer.parseInt(br.readLine());
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int inX = Integer.parseInt(st.nextToken());
                int inY = Integer.parseInt(st.nextToken());

                int outX = Integer.parseInt(st.nextToken());
                int outY = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                map[inX][inY] = GHOST_HOLE; // 귀신 구멍임을 표시

                Location in = new Location(inX, inY);
                Location out = new Location(outX, outY, T);
                ghostHoleMapper.put(in, out);
            }

            boolean cycle = bellmanFord();

            if (cycle) bw.write("Never\n");
            else {
                if (dp[W - 1][H - 1] == INF) bw.write("Impossible\n");
                else bw.write(dp[W - 1][H - 1] + "\n");
            }

        } while (true);

        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean bellmanFord() {
        boolean cycleOn = false;
        dp = new long[W][H];
        for (int i = 0; i < W; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][0] = 0L;
        for (int i = 0; i < W * H; i++) {
            for (int x = 0; x < W; x++) {
                for (int y = 0; y < H; y++) {
                    // current node : (x, y)
                    if (map[x][y] == TOMBSTONE || dp[x][y] == INF || (x == W - 1 && y == H - 1)) continue;

                    // next node : ( nextX, nextY )
                    if (map[x][y] == GHOST_HOLE) {
                        Location nextLoc = ghostHoleMapper.get(new Location(x, y));
                        int nextX = nextLoc.x;
                        int nextY = nextLoc.y;
                        long time = nextLoc.time;

                        if (dp[nextX][nextY] > dp[x][y] + time) {
                            dp[nextX][nextY] = dp[x][y] + time;
                        }
                    } else {
                        for (int d = 0; d < 4; d++) {
                            int nextX = x + dx[d];
                            int nextY = y + dy[d];
                            long time = 1L;

                            if (inRange(nextX, nextY) && map[nextX][nextY] != TOMBSTONE
                                    && dp[nextX][nextY] > dp[x][y] + time) {
                                dp[nextX][nextY] = dp[x][y] + time;
                            }
                        }
                    }
                }
            }

        }


        for (int x = 0; x < W; x++) {
            for (int y = 0; y < H; y++) {
                // current node : (x, y)
                if (map[x][y] == TOMBSTONE || dp[x][y] == INF || (x == W - 1 && y == H - 1)) continue;

                // next node : ( nextX, nextY )
                if (map[x][y] == GHOST_HOLE) {
                    Location nextLoc = ghostHoleMapper.get(new Location(x, y));
                    int nextX = nextLoc.x;
                    int nextY = nextLoc.y;
                    long time = nextLoc.time;

                    if (dp[nextX][nextY] > dp[x][y] + time) {
                        return true;
                    }
                } else {
                    for (int d = 0; d < 4; d++) {
                        int nextX = x + dx[d];
                        int nextY = y + dy[d];
                        long time = 1L;

                        if (inRange(nextX, nextY) && map[nextX][nextY] != TOMBSTONE
                                && dp[nextX][nextY] > dp[x][y] + time) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    private static boolean inRange(int x, int y) {
        return !(x < 0 || x >= W || y < 0 || y >= H);
    }

    static class Location {
        int x;
        int y;
        long time;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Location(int x, int y, long time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Location location = (Location) o;
            return x == location.x && y == location.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
