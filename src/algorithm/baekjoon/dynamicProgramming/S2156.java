package algorithm.baekjoon.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// beakJoon 2156 silver1 포도주 시식
public class S2156 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] g = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            g[i] = Integer.parseInt(br.readLine());
        }
        if (n == 1) {
            System.out.println(g[1]);
            return;
        }

        int[] dp = new int[n + 1];
        dp[1] = g[1];
        dp[2] = g[1] + g[2];

        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(dp[i - 2] + g[i], dp[i - 3] + g[i - 1] + g[i]);
            dp[i] = Math.max(dp[i - 1], dp[i]);
        }

        System.out.println(dp[n]);

        br.close();
    }
}
