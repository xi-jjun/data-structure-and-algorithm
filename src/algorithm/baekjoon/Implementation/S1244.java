package algorithm.baekjoon.Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// baekJoon 1244 silver3 스위치 켜고 끄기
public class S1244 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		boolean[] S = new boolean[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			S[i] = !st.nextToken().equals("0");
		}

		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			String sex = st.nextToken();
			int number = Integer.parseInt(st.nextToken());

			if (sex.equals("1")) {
				int now = number;
				while (now <= N) {
					S[now] = !S[now];
					now += number;
				}
			} else {
				int left = number;
				int right = number;
				S[number] = !S[number];
				while (--left >= 1 && ++right <= N) {
					if (S[left] == S[right]) {
						S[left] = !S[left];
						S[right] = S[left];
					} else break;
				}
			}
		}

		for (int i = 1; i <= N; i++) {
			if (i % 20 == 0) bw.write((S[i] ? 1 : 0) + "\n");
			else bw.write((S[i] ? 1 : 0) + " ");
		}

		bw.flush();
		bw.close();
	}
}
