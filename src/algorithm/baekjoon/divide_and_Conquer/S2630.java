package divide_and_Conquer;

import java.io.*;
import java.util.StringTokenizer;

// baekJoon 2630 silver3 색종이 만들기
// https://st-lab.tistory.com/227 참고...
public class S2630 {
    static int[] answer = new int[2];
    static int N;
    static int[][] paper;

    static boolean checkColor(int startX, int startY, int size) {
        int color = paper[startX][startY];

        for (int i = startX; i < startX + size; i++) {
            for (int j = startY; j < startY + size; j++) {
                if (paper[i][j] != color) return false;
            }
        }

        return true;
    }

    static void partition(int startX, int startY, int size) {
        if (checkColor(startX, startY, size)) {
            answer[paper[startX][startY]]++;
            return;
        }

        int s = size / 2;
        partition(startX, startY, s);
        partition(startX, startY + s, s);
        partition(startX + s, startY, s);
        partition(startX + s, startY + s, s);

    }

    // N은 2, 4, 8, 16, 32, 64, 128 중 하나
    // 나누는 경우로 케이스 만들자
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        paper = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        partition(0, 0, N);

        bw.write(answer[0] + "\n" + answer[1]);
        bw.flush();
        bw.close();
    }
}
