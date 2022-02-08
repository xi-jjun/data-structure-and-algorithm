package divide_and_Conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// baekJoon 1992 silver1 쿼드트리
// ( ← 부분 추가하는 법 참고 : https://sss20-02.tistory.com/88
public class S1992 {
    static int N;
    static int[][] video;
    static StringBuilder answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        video = new int[N][N];
        answer = new StringBuilder();

        for (int i = 0; i < N; i++) {
            int idy = 0;
            String[] line = br.readLine().split("");
            for (String l : line) {
                video[i][idy++] = Integer.parseInt(l);
            }
        }

        check(0, 0, N);

        System.out.println(answer);
    }

    private static void check(int row, int col, int size) {
        boolean ok = checkPartition(row, col, size);
        if (!ok) {
            answer.append("(");
            check(row, col, size / 2);
            check(row, col + size / 2, size / 2);
            check(row + size / 2, col, size / 2);
            check(row + size / 2, col + size / 2, size / 2);
            answer.append(")");
        } else {
            answer.append(video[row][col]);
        }
    }

    private static boolean checkPartition(int row, int col, int size) {
        int ex = video[row][col];
        boolean ok = true;
        for (int i = row; i < row+size; i++) {
            for (int j = col; j < col+size; j++) {
                ok = ex == video[i][j];
                if (!ok) return false;
            }
        }
        return true;
    }
}
