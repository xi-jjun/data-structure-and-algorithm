package algorithm.baekjoon.DFS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// baekJoon 2583 silver1 영역 구하기
public class S2583 {
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};
    static int N, M, K, size;
    static int[][] map;
    static List<Integer> areas = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());

            paint(startX, startY, endX, endY);
        }

        int count = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                size = 0;
                if (dfs(i, j)) {
                    count++;
                    areas.add(size);
                }
            }
        }

        Collections.sort(areas);

        bw.write(count + "\n");
        for (Integer area : areas) {
            bw.write(area + " ");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean dfs(int x, int y) {
        if (!inRange(x, y) || map[x][y] != 0) {
            return false;
        }

        size++;
        map[x][y] = 1;

        for (int i = 0; i < 4; i++) {
            dfs(x + dx[i], y + dy[i]);
        }

        return true;
    }

    private static boolean inRange(int x, int y) {
        return !(x < 0 || x >= M || y < 0 || y >= N);
    }

    private static void paint(int startX, int startY, int endX, int endY) {
        for (int i = startX; i < endX; i++) {
            for (int j = startY; j < endY; j++) {
                map[i][j] = 1;
            }
        }
    }
}
