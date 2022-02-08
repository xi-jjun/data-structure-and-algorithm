package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// baekJoon 1080 silver2 행렬
public class S1080 {
    static int[][] A;
    static int[][] B;
    static int count = 0;

    static void flip(int startX, int startY) {
        count++;
        for (int i = startX; i < startX + 3; i++) {
            for (int k = startY; k < startY + 3; k++) {
                A[i][k] = A[i][k] == 0 ? 1 : 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");

        int N = Integer.parseInt(line[0]);
        int M = Integer.parseInt(line[1]);

        A = new int[N][M];
        B = new int[N][M];

        for (int i = 0; i < N; i++) {
            String elements = br.readLine();
            int idx = 0;

            for (String e : elements.split("")) {
                A[i][idx++] = Integer.parseInt(e);
            }
        }

        for (int i = 0; i < N; i++) {
            String elements = br.readLine();
            int idx = 0;

            for (String e : elements.split("")) {
                B[i][idx++] = Integer.parseInt(e);
            }
        }

        for (int i = 0; i < N - 2; i++) {
            for (int k = 0; k < M - 2; k++) {
                if (A[i][k] != B[i][k]) flip(i, k);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int k = 0; k < M; k++) {
                if (A[i][k] != B[i][k]) {
                    System.out.println("-1");
                    return;
                }
            }
        }
        System.out.println(count);
    }
}
