package algorithm.baekjoon.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

// https://jaemin8852.tistory.com/163
// baekJoon 2294 Gold5 동전 2
public class G2294 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] coins = new int[N];

        for (int i = 0; i < N; i++) {
            int coin = Integer.parseInt(br.readLine());
            coins[i] = coin;
        }

        final int INF = 10001;
        int[] dp = new int[K + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int i = 0; i < N; i++) { // coins[i] 원을 사용해서
            for (int j = coins[i]; j <= K; j++) { // j 원을 만들어보자
                dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
            }
        }

        System.out.println((dp[K] == INF ? -1 : dp[K]));
    }
}
