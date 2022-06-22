package algorithm.baekjoon.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// baekJoon 16234 Gold5 인구이동
public class G16234 {
    static final int NOT_VISITED = 0;
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};
    static int N, L, R;
    static int visitedSeq = 1;
    static int checkSum, checkCount;
    static int[] union;
    static int[][] map;
    static int[][] visited;
    static boolean end;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        visited = new int[N][N];
        union = new int[N * N + 1];
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; st.hasMoreTokens(); j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        while (true) {
            end = true;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    checkCount = checkSum = 0;
                    if (visited[i][j] == NOT_VISITED) {
                        visited[i][j] = visitedSeq;
                        dfs(i, j);
                        union[visitedSeq] = checkSum / checkCount;
                        ++visitedSeq;

                        if (checkCount >= 2) end = false;
                    }
                }
            }

            if (end) break;
            ++answer;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int unionIdx = visited[i][j];
                    int changedPopulation = union[unionIdx];
                    map[i][j] = changedPopulation;
                }
            }

            visitedSeq = 1;
            Arrays.fill(union, 0);
            for (int i = 0; i < N; i++) {
                Arrays.fill(visited[i], 0);
            }
        }

        System.out.println(answer);

        br.close();
    }

    private static void dfs(int x, int y) {
        ++checkCount;
        checkSum += map[x][y];

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (isValid(nextX, nextY) && checkPopulationRange(x, y, nextX, nextY)) {
                visited[nextX][nextY] = visitedSeq;
                dfs(nextX, nextY);
            }
        }
    }

    private static boolean checkPopulationRange(int x, int y, int nextX, int nextY) {
        int nowPopulation = map[x][y];
        int nextPopulation = map[nextX][nextY];
        int diff = Math.abs(nowPopulation - nextPopulation);
        return L <= diff && diff <= R;
    }

    private static boolean isValid(int x, int y) {
        return inRange(x, y) && visited[x][y] == NOT_VISITED;
    }

    private static boolean inRange(int x, int y) {
        return !(x < 0 || x >= N || y < 0 || y >= N);
    }
}
