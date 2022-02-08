package BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// baekJoon 9663 Gold5 N-Queen
public class G9663 {
    static int N;
    static int[] chess;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        chess = new int[N];

        backTracking(0);

        System.out.println(answer);
    }

    private static void backTracking(int depth) {
        if (depth == N) {
            answer++;
            return;
        }

        for (int i = 0; i < N; i++) {
            chess[depth] = i;
            if (isPermitted(depth)) {
                backTracking(depth + 1);
            }
        }
    }

    private static boolean isPermitted(int col) {
        for (int i = 0; i < col; i++) {
            if (chess[i] == chess[col]) return false;
            if (Math.abs(col - i) == Math.abs(chess[col] - chess[i])) return false;
        }
        return true;
    }
}
