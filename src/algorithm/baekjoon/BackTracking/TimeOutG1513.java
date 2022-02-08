package BackTracking;

import java.io.*;
import java.util.*;
// TimeOut!!
public class TimeOutG1513 {
    static final int[] dx = {0, 1};
    static final int[] dy = {1, 0};
    static int N, M, C;
    static int[][] map;
    static int[] count;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        count = new int[C + 1];

        getFunLocation(br);

        int startCount = map[0][0] != 0 ? 1 : 0;
        int startC = map[0][0] != 0 ? map[0][0] : 0;
        if (!(map[0][0] > map[N - 1][M - 1] && map[N - 1][M - 1] != 0))
            backTracking(0, 0, startCount, startC, 0);

        for (int ans : count) {
            bw.write(ans + " ");
        }

        bw.flush();
        bw.close();
    }

    private static void backTracking(int startX, int startY, int countC, int exMaxSeq, int depth) {
        if (!isValid(startX, startY)) return;
        if (depth > N + M) return;

        if (startX == N - 1 && startY == M - 1) {
            int nowSeq = map[N - 1][M - 1];
            if(countC > C) return;
            if (nowSeq == 0) count[countC]++;
            else if (exMaxSeq < nowSeq) count[countC]++;
            return;
        }
//        System.out.print("(" + (startX + 1) + "," + (startY + 1) + ")" + " ");

        for (int i = 0; i < 2; i++) {
            int nextX = startX + dx[i];
            int nextY = startY + dy[i];

            if (isValid(nextX, nextY)) {
                int nextSeq = map[nextX][nextY];

                if (nextSeq == 0) {
                    visited[startX][startY] = true;
                    backTracking(nextX, nextY, countC, exMaxSeq, depth + 1);
                    visited[startX][startY] = false;
                } else if (exMaxSeq < nextSeq) {
                    visited[startX][startY] = true;
                    backTracking(nextX, nextY, countC + 1, nextSeq, depth + 1);
                    visited[startX][startY] = false;
                }
            }
        }
    }

    private static void getFunLocation(BufferedReader br) throws IOException {
        for (int i = 1; i <= C; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            map[x][y] = i;
        }
    }


    private static boolean isValid(int x, int y) {
        return !(x < 0 || x >= N || y < 0 || y >= M) && !visited[x][y];
    }

    static class Location {
        private final int x, y;
        int nowMaxSeq;

        public Location(int x, int y, int seq) {
            this.x = x;
            this.y = y;
            this.nowMaxSeq = seq;
        }
    }
}
