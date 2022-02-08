package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G10026 {
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};
    static int N;
    static char[][] picture;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        picture = new char[N][N];

        for (int i = 0; i < N; i++) {
            int idx = 0;
            String line = br.readLine();
            for (String s : line.split("")) {
                picture[i][idx++] = s.charAt(0);
            }
        }

        int answer1 = 0;
        int answer2 = 0;
        visited = new boolean[N][N];
        answer1 = getAnswer(true); // normal people
        visited = new boolean[N][N];
        answer2 = getAnswer(false);
        System.out.println(answer1 + " " + answer2);
    }

    private static int getAnswer(boolean b) {
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (dfs(i, j, picture[i][j], b)) answer++;
            }
        }
        return answer;
    }

    private static boolean dfs(int x, int y, char STD, boolean mode) {
        if (x < 0 || x >= N || y < 0 || y >= N) return false;

        if (!visited[x][y]) {
            if (mode && STD == picture[x][y]) {
                probeDfs(x, y, STD, true);
                return true;
            } else if (!mode) {
                if (STD == 'B') {
                    if (picture[x][y] == STD) {
                        probeDfs(x, y, STD, false);
                        return true;
                    }
                } else {
                    if (picture[x][y] == 'R' || picture[x][y] == 'G') {
                        probeDfs(x, y, STD, false);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static void probeDfs(int x, int y, char STD, boolean mode) {
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            dfs(x + dx[i], y + dy[i], STD, mode);
        }
    }
}
