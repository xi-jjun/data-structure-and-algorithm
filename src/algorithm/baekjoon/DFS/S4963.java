package DFS;

import java.io.*;
import java.util.StringTokenizer;

// baekJoon 4963 silver2 섬 개수
public class S4963 {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static final int ISLAND = 1;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    static boolean dfs(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= M) return false;
        if (map[x][y] != ISLAND) return false;

        if (!visited[x][y]) {
            visited[x][y] = true;

            for (int i = 0; i < dx.length; i++) {
                dfs(x + dx[i], y + dy[i]);
            }
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            if (N + M == 0) break;

            map = new int[N][M];
            visited = new boolean[N][M];

            for (int i = 0; i < N; i++) {
                StringTokenizer st2 = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) map[i][k] = Integer.parseInt(st2.nextToken());
            }

            int result = 0;

            for (int i = 0; i < N; i++) {
                for (int k = 0; k < M; k++) {
                    if (dfs(i, k)) result++;
                }
            }

            bw.write(result + "\n");
        }

        bw.flush();
        bw.close();
    }
}
