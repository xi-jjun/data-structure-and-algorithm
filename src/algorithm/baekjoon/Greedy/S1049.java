package Greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// baekJoon 1049 silver4 기타줄
public class S1049 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int minPackageExpense = 1001;
		int minOneLineExpense = 1001;
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			minPackageExpense = Math.min(minPackageExpense, Integer.parseInt(st.nextToken()));
			minOneLineExpense = Math.min(minOneLineExpense, Integer.parseInt(st.nextToken()));
		}

		int expense = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			if (N - 6 * i < 0) {
				expense = Math.min(i * minPackageExpense, expense);
				break;
			}
			expense = Math.min(i * minPackageExpense + (N - 6 * i) * minOneLineExpense, expense);
		}

		bw.write(expense + "\n");

		bw.flush();
		bw.close();
	}
}
