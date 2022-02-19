package algorithm.baekjoon.dynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// baekJoon 2559 silver3 수열
public class S2559 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] data = new int[N];
		int[] S = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}

		S[0] = data[0];
		for (int i = 1; i < N; i++) {
			S[i] = S[i - 1] + data[i];
		}

		int max = S[K - 1];
		for (int i = K; i < N; i++) {
			int sumK = S[i] - S[i - K];
			if (max < sumK) max = sumK;
		}

		bw.write(max + "");
		bw.flush();
		bw.close();
	}
}
