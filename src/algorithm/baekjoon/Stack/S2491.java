package algorithm.baekjoon.Stack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// baekJoon 2491 silver3 수열
public class S2491 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		int max = -1;
		int ascend = 0;
		int descend = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		int ex = Integer.parseInt(st.nextToken());
		for (int i = 1; i < N; i++) {
			int now = Integer.parseInt(st.nextToken());
			if (ex <= now) {
				ascend++;
			} else ascend = 0;

			if (ex >= now) {
				descend++;
			} else descend = 0;

			max = Math.max(descend, max);
			max = Math.max(ascend, max);
			ex = now;
		}

		max = Math.max(Math.max(ascend, descend), max);

		bw.write((max + 1) + "");
		bw.flush();
		bw.close();
	}
}
