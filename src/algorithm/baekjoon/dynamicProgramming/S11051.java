package dynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// baekJoon 11051 silver1 이항 계수 2
public class S11051 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] dp = new int[N + 1][N + 1];
		dp[0][0] = 1;
		dp[1][0] = 1;
		dp[1][1] = 1;

		for (int i = 2; i <= N; i++) {
			for (int j = 0; j <= i; j++) {
				if (j == 0 || j == i) {
					dp[i][j] = 1;
				} else {
					dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % 10007;
				}
			}
		}

		bw.write((dp[N][K] % 10007) + "\n");

		bw.flush();
		bw.close();
	}
}
