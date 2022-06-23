package algorithm.baekjoon.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// baekJoon 1699 silver2 제곱수의 합
public class S1699 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] dp = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            dp[i] = i;
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 1; j * j <= i; j++) {
                if (i == j * j) dp[i] = 1;
                else dp[i] = Math.min(dp[i], dp[i - j * j] + dp[j * j]);
            }
        }

        System.out.println(dp[N]);
    }
}
