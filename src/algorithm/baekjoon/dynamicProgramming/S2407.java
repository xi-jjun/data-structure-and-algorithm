package dynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

// baekJoon 2407 silver3 조합
public class S2407 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		BigInteger[][] dp = new BigInteger[101][101];
		dp[0][0] = new BigInteger("1");
		dp[1][1] = new BigInteger("1");

		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= i; j++) {
				if (i == j || j == 0) {
					dp[i][j] = new BigInteger("1");
				} else {
					dp[i][j] = dp[i - 1][j - 1].add(dp[i - 1][j]);
				}
			}
		}

		bw.write(dp[n][m] + "");

		bw.flush();
		bw.close();
	}
}
