package algorithm.baekjoon.Math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G2225 {
    static final int MOD = 1000000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int K = Integer.parseInt(line[1]);
        long[][] dp = new long[N + 1][K + 1];

        for (int i = 0; i <= N; i++) {
            dp[i][1] = 1L;
        }

        for (int i = 1; i <= K; i++) {
            for (int j = 0; j <= N; j++) {
                for (int k = 0; k <= j; k++) {
                    dp[j][i] += dp[k][i - 1] % MOD;
                }
            }
        }

        System.out.println(dp[N][K] % MOD);
    }
}
