package algorithm.baekjoon.BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://steady-coding.tistory.com/43
// 아이디어가 바로 안떠올랐다.
// baekJoon 17136 Gold2 색종이 붙이기
public class G17136 {
    static final int ATTACH = 0;
    static final int DETACH = 1;
    static final int PAPER = 1;
    static final int N = 10;
    static final int[] papers = {-1, 5, 5, 5, 5, 5};
    static int answer = Integer.MAX_VALUE;
    static int[][] map = new int[10][10];

    public static void main(String[] args) throws IOException {
        getInput();

        solution(0, 0, 0);
        System.out.println((answer == Integer.MAX_VALUE ? -1 : answer));
    }

    private static void solution(int x, int y, int count) {
        if (x >= 9 && y > 9) {
            answer = Math.min(answer, count);
            return;
        }

        if (answer <= count) {
            return;
        }

        if (y > 9) {
            solution(x + 1, 0, count);
            return;
        }

        if (map[x][y] == PAPER) {
            for (int size = 5; size >= 1; size--) {
                if (papers[size] > 0 && isAttached(x, y, size)) {
                    papers[size]--;
                    operate(x, y, size, ATTACH);
                    solution(x, y + 1, count + 1);
                    operate(x, y, size, DETACH);
                    papers[size]++;
                }
            }
        } else {
            solution(x, y + 1, count);
        }
    }

    private static void operate(int x, int y, int size, int method) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                map[i][j] = method;
            }
        }
    }

    private static boolean isAttached(int x, int y, int size) {
        if (!inRange(x + size - 1, y + size - 1)) {
            return false;
        }

        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (map[i][j] != PAPER) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean inRange(int x, int y) {
        return !(x < 0 || x >= N || y < 0 || y >= N);
    }

    private static void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; st.hasMoreTokens(); j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
