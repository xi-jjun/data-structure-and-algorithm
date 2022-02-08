package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// baekJoon 2579 silver3 계단 오르기
// https://sihyungyou.github.io/baekjoon-2579/ 참고
public class S2579 {
    static int N;
    static int[] stairs;
    static int[] score;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        stairs = new int[N + 1];
        score = new int[N + 1];

        if (N <= 2) {
            int sum = 0;
            while (N-- > 0) sum += Integer.parseInt(br.readLine());
            System.out.println(sum);
            return;
        }

        for (int i = 1; i <= N; i++) stairs[i] = Integer.parseInt(br.readLine());

        score[1] = stairs[1];
        score[2] = Math.max(stairs[1] + stairs[2], stairs[2]);
        score[3] = Math.max(stairs[1] + stairs[3], stairs[2] + stairs[3]);

        for (int i = 4; i <= N; i++)
            score[i] = Math.max(score[i - 2] + stairs[i], score[i - 3] + stairs[i - 1] + stairs[i]);

        System.out.println(score[N]);
    }
}
