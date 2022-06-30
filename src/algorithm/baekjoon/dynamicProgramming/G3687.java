package algorithm.baekjoon.dynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// https://github.com/joshua-qa/PS/blob/master/BOJ/3000/3687.java 참고
// baekJoon 3687 Gold2 성냥개비 - Naver 코테 준비위한 문제
public class G3687 {
    static final int[] count = {-1, -1, 1, 7, 4, 2, 0, 8};
    static long[] min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());
        min = new long[101];
        min[2] = 1;
        min[3] = 7;
        min[4] = 4;
        min[5] = 2;
        min[6] = 6;
        min[7] = 8;
        min[8] = 10;

        for (int i = 9; i <= 100; i++) {
            long minValue = Long.MAX_VALUE;
            for (int j = 2; j <= 7; j++) {
                minValue = Math.min(Long.parseLong(min[i - j] + "" + count[j]), minValue);
            }
            min[i] = minValue;
        }

        for (int i = 0; i < tc; i++) {
            int N = Integer.parseInt(br.readLine());

            bw.write(min[N] + " ");
            StringBuilder maxAnswer = new StringBuilder();
            if (N % 2 == 0) {
                for (int j = 0; j < N / 2; j++) {
                    maxAnswer.append("1");
                }
            } else {
                maxAnswer.append("7");
                N = N - 3;
                for (int j = 0; j < N / 2 ; j++) {
                    maxAnswer.append("1");
                }
            }
            bw.write(maxAnswer + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
