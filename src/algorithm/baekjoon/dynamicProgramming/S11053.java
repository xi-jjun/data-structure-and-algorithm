package dynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// baekJoon 11053 silver2 가장 긴 증가하는 부분 수열
public class S11053 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		int[] data = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}

		int[] dp = new int[N];
		int answer = 1;
		dp[0] = 1;

		for (int i = 1; i < N; i++) {
			int max = 0;
			int focusData = data[i];
			for (int j = 0; j < i; j++) {
				if (data[j] < focusData) {
					max = Math.max(dp[j], max);
				}
			}

			dp[i] = max + 1;
			answer = Math.max(dp[i], answer);
		}

		bw.write(answer + "\n");

		bw.flush();
		bw.close();
	}
}
