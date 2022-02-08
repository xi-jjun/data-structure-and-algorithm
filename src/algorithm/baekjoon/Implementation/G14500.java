package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// baekJoon 14500 Gold5 테트로미노
public class G14500 {
    static int N, M;
    static int[][] map;

    static int flat(int startX, int startY) {
        int max = 0;
        int[][] dx = {
                {0, 0, 0, 0},
                {0, 1, 2, 3}
        };
        int[][] dy = {
                {0, 1, 2, 3},
                {0, 0, 0, 0}
        };

        for (int i = 0; i < 2; i++) {
            max = getMax(startX, startY, max, dx, dy, i);
        }

        return max;
    }

    static int box(int startX, int startY) {
        if (startX + 1 >= N || startY + 1 >= M) return -1;
        return map[startX][startY] + map[startX + 1][startY] + map[startX][startY + 1] + map[startX + 1][startY + 1];
    }

    static int gun(int startX, int startY) {
        int max = 0;
        int[][] dx = {
                {0, 1, 2, 0},
                {0, 0, 1, 2},
                {0, 1, 1, 1},
                {0, 0, 0, 1},
                {0, 1, 2, 2},
                {0, 1, 2, 2},
                {0, 0, 0, 1},
                {0, 0, 0, -1}
        };
        int[][] dy = {
                {0, 0, 0, 1},
                {0, 1, 1, 1},
                {0, 0, 1, 2},
                {0, 1, 2, 0},
                {0, 0, 0, -1},
                {0, 0, 0, 1},
                {0, 1, 2, 2},
                {0, 1, 2, 2}
        };

        for (int i = 0; i < 8; i++) {
            max = getMax(startX, startY, max, dx, dy, i);
        }

        return max;
    }

    private static int getMax(int startX, int startY, int max, int[][] dx, int[][] dy, int i) {
        int temp = 0;
        for (int k = 0; k < 4; k++) {
            if (startX + dx[i][k] < 0 || startX + dx[i][k] >= N || startY + dy[i][k] < 0 || startY + dy[i][k] >= M) {
                temp = -1;
                break;
            } else temp += map[startX + dx[i][k]][startY + dy[i][k]];
        }
        max = Math.max(max, temp);
        return max;
    }

    static int zigzag(int startX, int startY) {
        int[][] dx = {
                {0, 1, 1, 2}, // basic 1
                {0, 1, 1, 2}, // 180 flip basic 1
                {0, 0, -1, -1}, // 90 rotate
                {0, 0, 1, 1} // flip
        };
        int[][] dy = {
                {0, 0, 1, 1},
                {0, 0, -1, -1},
                {0, 1, 1, 2},
                {0, 1, 1, 2}
        };

        int max = 0;

        for (int i = 0; i < 4; i++) {
            max = getMax(startX, startY, max, dx, dy, i);
        }

        return max;
    }

    static int oh(int startX, int startY) {
        int max = 0;
        int[][] dx = {
                {0, 0, 0, 1},
                {0, 0, 0, -1},
                {0, 1, 2, 1},
                {0, 1, 2, 1}
        };
        int[][] dy = {
                {0, 1, 2, 1},
                {0, 1, 2, 1},
                {0, 0, 0, -1},
                {0, 0, 0, 1}
        };


        for (int i = 0; i < 4; i++) {
            max = getMax(startX, startY, max, dx, dy, i);
        }

        return max;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int k = 0; k < M; k++) map[i][k] = Integer.parseInt(st.nextToken());
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int k = 0; k < M; k++) {
                int temp = Math.max(zigzag(i, k), oh(i, k));
                int temp2 = Math.max(flat(i, k), box(i, k));
                int temp3 = Math.max(temp, temp2);
                int temp4 = Math.max(temp3, gun(i, k));
                result = Math.max(temp4, result);
            }
        }

        System.out.println(result);
    }
}
