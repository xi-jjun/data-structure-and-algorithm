package DFS;

import java.io.*;
import java.util.StringTokenizer;

// baekJoon 2468 silver1
public class S2468 {
    static int[][] map;
    static boolean[][] visited;
    static int N;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static boolean dfs(int startX, int startY, int limit) {
        if (startX < 0 || startX >= N || startY < 0 || startY >= N) return false;

        if (map[startX][startY] > limit && !visited[startX][startY]) {
            visited[startX][startY] = true;

            dfs(startX + dx[0], startY + dy[0], limit);
            dfs(startX + dx[1], startY + dy[1], limit);
            dfs(startX + dx[2], startY + dy[2], limit);
            dfs(startX + dx[3], startY + dy[3], limit);

            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        int min = 101;
        int max = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int k = 0; k < N; k++) {
                int element = Integer.parseInt(st.nextToken());
                map[i][k] = element;
                max = Math.max(element, max);
                min = Math.min(element, min);
            }
        }

        int result = -1;
        for (int i = min - 1; i <= max; i++) {
            int count = 0;

            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    if (dfs(x, y, i)) count++;
                }
            }

            visited = new boolean[N][N];

            result = Math.max(result, count);
        }

        bw.write(result + "\n");

        bw.flush();
        bw.close();
    }
}
