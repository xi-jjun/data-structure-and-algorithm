package dynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// baekJoon 2293 Gold5 동전 1
// https://bitwise.tistory.com/504 참고
public class G2293 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] nk = br.readLine().split(" ");
		int n = Integer.parseInt(nk[0]);
		int k = Integer.parseInt(nk[1]);

		int[] coins = new int[n];

		for (int i = 0; i < n; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}

		int[] dp = new int[k + 1];
		dp[0] = 1;
		for (int i = 0; i < coins.length; i++) {
			for (int j = coins[i]; j <= k; j++) {
				dp[j] = dp[j] + dp[j - coins[i]];
			}
		}
		bw.write(dp[k] + "\n");

		bw.flush();
		bw.close();
	}
}
