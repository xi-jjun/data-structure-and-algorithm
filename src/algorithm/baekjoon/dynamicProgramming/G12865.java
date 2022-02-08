package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G12865 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken()); // max weight
        int[][] dp = new int[N + 1][K + 1];
        int[] weights = new int[N];
        int[] values = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            weights[i] = Integer.parseInt(st.nextToken()); // weight
            values[i] = Integer.parseInt(st.nextToken()); // value
        }

        for (int i = 0; i < N + 1; i++) {
            for (int w = 0; w < K + 1; w++) {
                if (i == 0 || w == 0) dp[i][w] = 0;
                else if (weights[i - 1] <= w)
                    dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                else dp[i][w] = dp[i - 1][w];
            }
        }

        System.out.println(dp[N][K]);
    }
}
