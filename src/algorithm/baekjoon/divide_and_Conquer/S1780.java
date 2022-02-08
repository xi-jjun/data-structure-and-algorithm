package divide_and_Conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1780 {
    static int N;
    static int[][] paper;
    static int[] count = new int[3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        paper = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        check(0, 0, N);
        for (int i : count) {
            System.out.println(i);
        }
    }

    private static void check(int row, int col, int size) {
        boolean ok = isSame(row, col, size);

        if (!ok) {
            int newSize = size / 3;
            check(row, col, newSize);
            check(row, col + newSize, newSize);
            check(row, col + 2 * newSize, newSize);
            check(row + newSize, col, newSize);
            check(row + newSize, col + newSize, newSize);
            check(row + newSize, col + 2 * newSize, newSize);
            check(row + 2 * newSize, col, newSize);
            check(row + 2 * newSize, col + newSize, newSize);
            check(row + 2 * newSize, col + 2 * newSize, newSize);
        } else {
            if (paper[row][col] == -1) count[0]++;
            else count[paper[row][col] + 1]++;
        }
    }

    private static boolean isSame(int row, int col, int size) {
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (paper[row][col] != paper[i][j]) return false;
            }
        }
        return true;
    }
}
