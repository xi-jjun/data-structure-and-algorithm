package algorithm.baekjoon.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// baekJoon 13305 silver4 주유소
public class S13305 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] distance = new int[N];
		int[] oilPrice = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N; i++) {
			distance[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			oilPrice[i] = Integer.parseInt(st.nextToken());
		}

		long answer = 0L;
		for (int i = 0; i < N; i++) {
			int currOilPrice = oilPrice[i];
			long tempExpense = 0L;

			for (int j = i + 1; j <= N; j++) {
				if (j == N) {
					i = j - 1;
					break;
				}
				if (currOilPrice > oilPrice[j]) {
					tempExpense += (long) currOilPrice * distance[j];
					i = j - 1;
					break;
				} else {
					tempExpense += (long) currOilPrice * distance[j];
				}
			}
			answer += tempExpense;
		}

		System.out.println(answer);
	}
}
