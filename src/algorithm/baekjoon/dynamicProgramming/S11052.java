package algorithm.baekjoon.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// baekJoon 11052 silver1 카드 구매하기
public class S11052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] P = new int[N + 1];
        for (int i = 1; st.hasMoreTokens(); i++) {
            P[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            dp[i] = P[i];
        }

        for (int i = 1; i < N + 1; i++) {
            int count = i / 2;
            for (int j = 1; j <= count; j++) {
                dp[i] = Math.max(dp[i], dp[j] + dp[i - j]);
            }
        }

        System.out.println(dp[N]);
    }
}
