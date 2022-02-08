package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// baekJoon 17626 silver4 - Four Squares
public class S17626 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[50001];
        int k = 2;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 4; i <= n; i++) {
            if (i == k * k) {
                k++;
                dp[i] = 1;
            } else {
                int min = Integer.MAX_VALUE;
                for (int j = 1; j * j <= i; j++) {
                    min = Math.min(min, dp[j * j] + dp[i - j * j]);
                }
                dp[i] = min;
            }
        }
        System.out.println(dp[n]);
    }
}
