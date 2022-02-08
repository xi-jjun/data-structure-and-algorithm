package DFS;

import java.io.*;

// baekJoon 1012 silver2 유기농 배추
public class S1012 {
    static final int BAECHU = 1;
    static final int VISITED_BAECHU = 2;
    static int[][] farm;
    static int N, M, K;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static boolean dfs(int x, int y) {
        if (x < 0 || y >= M || x >= N || y < 0) return false;

        if (farm[x][y] == BAECHU) {
            farm[x][y] = VISITED_BAECHU;

            dfs(x + dx[0], y + dy[0]);
            dfs(x + dx[1], y + dy[1]);
            dfs(x + dx[2], y + dy[2]);
            dfs(x + dx[3], y + dy[3]);

            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String[] line = br.readLine().split(" ");
            N = Integer.parseInt(line[1]);
            M = Integer.parseInt(line[0]);
            K = Integer.parseInt(line[2]);
            farm = new int[N][M];
            int count = 0;

            for (int k = 0; k < K; k++) {
                String[] coord = br.readLine().split(" ");
                int x = Integer.parseInt(coord[1]);
                int y = Integer.parseInt(coord[0]);
                farm[x][y] = BAECHU;
            }

            for (int a = 0; a < N; a++) {
                for (int b = 0; b < M; b++) if (dfs(a, b)) count++;
            }
            bw.write(count + "\n");
        }

        bw.flush();
        bw.close();
    }
}
