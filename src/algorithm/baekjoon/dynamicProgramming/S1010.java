package algorithm.baekjoon.dynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// baekJoon 1010 silver5 다리놓기
public class S1010 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long[][] C = new long[31][31];
        for (int i = 0; i < 31; i++) {
            C[i][0] = 1;
            C[i][i] = 1;
        }
        for (int a = 2; a <= 30; a++) {
            for (int b = 1; b < a; b++) {
                C[a][b] = C[a - 1][b - 1] + C[a - 1][b];
            }
        }

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            bw.write(C[M][N] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
